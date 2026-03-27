package com.example.fastmart;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class CartFragment extends Fragment
        implements CartItemRecyclerAdapter.setOnClickListener  {
    Context context;
    MyApplication app;
    CartItemRecyclerAdapter adapter;
    TextView shippingValueField;
    TextView totalPriceField;
    MaterialButton checkoutBtn;
    LinearLayout layoutCartCheckout;
    final int SMS_PERMISSION_REQ_CODE = 1;
    float shippingCost;
    float totalPrice;

    public CartFragment() {
        shippingCost = 0.00f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = requireContext();
        app = (MyApplication) context.getApplicationContext();
        shippingValueField = view.findViewById(R.id.shipping_in_cart);
        checkoutBtn = view.findViewById(R.id.cart_checkout_btn);

        String shippingCostStr = "$ " + shippingCost;
        shippingValueField.setText(shippingCostStr);

        totalPriceField = view.findViewById(R.id.total_price_in_cart);
        String totalPriceStr = "$ " + totalPrice;
        totalPriceField.setText(totalPriceStr);


        layoutCartCheckout = view.findViewById(R.id.cart_checkout_section);

        RecyclerView recyclerView = view.findViewById(R.id.cart_section_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));

        adapter = new CartItemRecyclerAdapter(context, app.cart, this);

        recyclerView.setAdapter(adapter);

        checkoutBtn.setOnClickListener(v -> {
            onCheckoutHandler();
        });
    }
    public void addToCart(Item item) {
        int id = item.getId();
        Item itemToBeCarted = app.cart.stream().filter(cartItem -> cartItem.getId() == id)
                        .findFirst()
                        .orElse(null);

        if (itemToBeCarted != null) {
            itemToBeCarted.incItemsSelected();
            adapter.notifyItemChanged(app.cart.indexOf(itemToBeCarted));
        }
        else {
            app.cart.add(item);
            adapter.notifyItemInserted(app.cart.size());
        }
    }

    @Override
    public void calculateTotal() {
        totalPrice = 0.00f;
        ArrayList<Item> cart = app.cart;
        for (var item: cart) {
            if (item.getDiscountedPrice() != 0.00f) {
                totalPrice += item.getDiscountedPrice() * item.getItemsSelected();
            }
            else {
                totalPrice += item.getPrice() * item.getItemsSelected();
            }
        }
        String totalPriceStr = "$ " + totalPrice;
        totalPriceField.setText(totalPriceStr);
    }

    @Override
    public void notifyDeleteItem() {
        if (app.cart.isEmpty()) {
            layoutCartCheckout.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
        if (app.cart.isEmpty()) {
            layoutCartCheckout.setVisibility(View.INVISIBLE);
        }
        else {
            layoutCartCheckout.setVisibility(View.VISIBLE);
        }
    }

    private void onCheckoutHandler() {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.SEND_SMS) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(),
                    new String[] {Manifest.permission.SEND_SMS}, SMS_PERMISSION_REQ_CODE);
        }
        else {
            SmsManager smsManager = SmsManager.getDefault();
            SharedPreferences sPref = context.getSharedPreferences(KeyUtils.userFileKey, Context.MODE_PRIVATE);

            if (sPref.getBoolean(KeyUtils.isLoggedInKey, false)) {
                String appPhNo = app.appPhNo;
                String userPhNO = app.phNo;
                String userName = app.name;

                if(!(userName.isEmpty() || userPhNO.isEmpty())) {

                    StringBuilder itemsDetailBuilder = new StringBuilder();
                    itemsDetailBuilder.append("You Bought:\n");
                    ArrayList<Item> cart = app.cart;

                    for (var item : cart) {
                        itemsDetailBuilder.append("    ")
                                .append(item.getItemsSelected())
                                .append(" ")
                                .append(item.getName())
                                .append(" in ")
                                .append(item.getPrice() * item.getItemsSelected())
                                .append("\n");
                    }
                    itemsDetailBuilder.append("Total Price is: ").append(totalPrice)
                            .append(" Includes ").append(shippingCost).append(" Shipping Cost.");

                    String userMessage = itemsDetailBuilder.toString();
                    String appMessage = userName + itemsDetailBuilder.substring(3);
                    smsManager.sendTextMessage(userPhNO, null, userMessage, null, null);
                    smsManager.sendTextMessage(appPhNo, null, appMessage, null, null);


                    int size = app.cart.size();
                    app.cart.clear();
                    adapter.notifyItemRangeRemoved(0, size);
                    layoutCartCheckout.setVisibility(View.INVISIBLE);
                }
                else {
                    Toast.makeText(context, "Complete your details First", Toast.LENGTH_LONG).show();
                }
            }
            else {
                Toast.makeText(context, "Login First", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(context, LoginSignupActivity.class);
                startActivity(intent);
            }
        }
    }
}
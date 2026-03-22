package com.example.fastmart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class GridListAdapter extends BaseAdapter {
    private final Context context;
    private final ArrayList<Item> rawItems;

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public Object getItem(int i) {
        return rawItems.get(i);
    }

    @Override
    public int getCount() {
        return rawItems.size();
    }

    public GridListAdapter(Context context, ArrayList<Item> rawItems)
    {
        this.context = context;
        this.rawItems = rawItems;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View cardView = view;

        if (view == null)
        {
            LayoutInflater inflater = LayoutInflater.from(context);
            cardView = inflater.inflate(R.layout.item_card, viewGroup, false);
        }

        ImageView itemImage = cardView.findViewById(R.id.item_image);
        TextView itemPrice = cardView.findViewById(R.id.item_price);
        TextView itemName = cardView.findViewById(R.id.item_name);
        TextView itemModel = cardView.findViewById(R.id.item_model);
        TextView itemColor = cardView.findViewById(R.id.item_color);

        Item item = rawItems.get(i);

        itemImage.setImageResource(item.getImageID());
        itemPrice.setText(item.getPrice());
        itemName.setText(item.getName());
        itemModel.setText(item.getModel());
        itemColor.setText(item.getColor());

        return cardView;
    }
}

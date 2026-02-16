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
    private Context context;
    private ArrayList<DataFile.ItemCard> rawItems;

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

    public GridListAdapter(Context context, ArrayList<DataFile.ItemCard> rawItems)
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

        DataFile.ItemCard item = rawItems.get(i);

        itemImage.setImageResource(item.imageID);
        itemPrice.setText(item.price);
        itemName.setText(item.name);
        itemModel.setText(item.model);
        itemColor.setText(item.color);

        return cardView;
    }
}

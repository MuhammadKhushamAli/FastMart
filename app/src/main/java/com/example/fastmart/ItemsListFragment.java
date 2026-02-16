package com.example.fastmart;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ItemsListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ItemsListFragment extends ListFragment {

    public interface onItemClicked {
        public void onItemClickListener(int index);
    }

    onItemClicked parentActivity;

    public ItemsListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        parentActivity = (onItemClicked) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_items_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArrayList<DataFile.ItemCard> rawCardsList = DataFile.getItems();

        ListView list = getListView();
        GridListAdapter adapter = new GridListAdapter(requireContext(), rawCardsList);
        list.setAdapter(adapter);
    }
}
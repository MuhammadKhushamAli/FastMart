package com.example.fastmart;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class HomeFragment extends Fragment implements ItemsListAdapter.setOnClickListener {
    ItemsListAdapter itemsListAdapter;
    MyApplication app;

    public HomeFragment() {
    }

    public interface setOnclickListener {
        public void addFav(int position);
        public void removeFav(int position);
    }
    setOnclickListener parentActivity;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        parentActivity = (setOnclickListener) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Context context = requireContext();
        app = (MyApplication) context.getApplicationContext();



        TextView homeWelcomeTextField = view.findViewById(R.id.welcomeText);
        String greetingMessage = "Hello! " + app.name;
        homeWelcomeTextField.setText(greetingMessage);

        getChildFragmentManager()
                .beginTransaction()
                .replace(R.id.dodFragment, new DealOfDayFragment())
                .commit();

        itemsListAdapter = new ItemsListAdapter(context, app.items, this);
        RecyclerView recyclerView = view.findViewById(R.id.homeItemsRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 2));

        recyclerView.setAdapter(itemsListAdapter);

    }

    @Override
    public void favAdd(int position) {
        parentActivity.addFav(position);
    }

    @Override
    public void favRemove(int position) {
        parentActivity.removeFav(position);
    }

    public void notifyChange(Item item) {
        int position = app.items.indexOf(item);
        itemsListAdapter.notifyItemChanged(position);
    }
}
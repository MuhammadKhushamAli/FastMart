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

public class HomeFragment extends Fragment {

    public HomeFragment() {
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
        MyApplication app = (MyApplication) context.getApplicationContext();



        TextView homeWelcomeTextField = view.findViewById(R.id.welcomeText);
        String greetingMessage = "Hello! " + app.name;
        homeWelcomeTextField.setText(greetingMessage);

        getChildFragmentManager()
                .beginTransaction()
                .replace(R.id.dodFragment, new DealOfDayFragment())
                .commit();

        ItemsListAdapter itemsListAdapter = new ItemsListAdapter(context, app.items);
        RecyclerView recyclerView = view.findViewById(R.id.homeItemsRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 2));

        recyclerView.setAdapter(itemsListAdapter);

    }
}
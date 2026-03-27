package com.example.fastmart;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class BrowseFragment extends ListFragment {

    MyApplication app;
    SharedPreferences sharedPreferences;

    setOnClickListener parentActivity;

    public BrowseFragment() {

    }

    public interface setOnClickListener {
        public void goBack();
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        parentActivity = (setOnClickListener) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_browse, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Context context = requireContext();
        TextView clearAllText = view.findViewById(R.id.clear_all_text);
        ImageButton goBackButton = view.findViewById(R.id.browse_go_back);

        sharedPreferences = context.getSharedPreferences(KeyUtils.userFileKey, Context.MODE_PRIVATE);
        app = (MyApplication) context.getApplicationContext();

        app.previousSearch = new ArrayList<>(
                sharedPreferences.getStringSet(KeyUtils.prevSearchHistoryPrefKey + app.email,
                        new LinkedHashSet<>()));

        androidx.appcompat.widget.SearchView searchView = view.findViewById(R.id.search_search_bar);


        ArrayAdapter<String> adapter = new ArrayAdapter<>(context,
                android.R.layout.simple_dropdown_item_1line,
                app.previousSearch);
        setListAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String prevSearch = app.previousSearch.stream().filter(search -> search.equals(query))
                                .findFirst()
                                .orElse(null);
                if(prevSearch == null) {
                    app.previousSearch.add(query);
                    adapter.notifyDataSetChanged();
                }
                searchResultsInItems(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        clearAllText.setOnClickListener(v -> {
            app.previousSearch.clear();
            adapter.notifyDataSetChanged();
            sharedPreferences.edit().remove(KeyUtils.prevSearchHistoryPrefKey + app.email).apply();
        });

        goBackButton.setOnClickListener(v -> {
            parentActivity.goBack();
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        sharedPreferences.edit().putStringSet(KeyUtils.prevSearchHistoryPrefKey + app.email, new LinkedHashSet<>(app.previousSearch))
                .apply();
    }

    private void searchResultsInItems(String query) {
        ArrayList<Item> items = app.items;
        query = query.toLowerCase();
        Context context = requireContext();

        for (var item : items) {
            if (item.getName().toLowerCase().contains(query) ||
                    item.getColor().toLowerCase().contains(query) ||
                    item.getModel().toLowerCase().contains(query) ||
                    item.getDescription().toLowerCase().contains(query) ||
                    item.getCategory().toLowerCase().contains(query)
            ) {

                Toast.makeText(context, item.getName() + " Found", Toast.LENGTH_LONG).show();
                return;
            }
        }
        Toast.makeText(context, "No Item Found", Toast.LENGTH_LONG).show();
    }
}
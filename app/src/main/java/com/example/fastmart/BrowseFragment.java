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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class BrowseFragment extends ListFragment {

    MyApplication app;
    SharedPreferences sharedPreferences;

    public BrowseFragment() {
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
        sharedPreferences = context.getSharedPreferences(KeyUtils.userFileKey, Context.MODE_PRIVATE);
        app = (MyApplication) context.getApplicationContext();

        app.previousSearch = new ArrayList<>(
                sharedPreferences.getStringSet(KeyUtils.prevSearchHistoryPrefKey + app.email,
                        new LinkedHashSet<>()));

        androidx.appcompat.widget.SearchView searchView = view.findViewById(R.id.search_search_bar);

        TextView clearAllText = view.findViewById(R.id.clear_all_text);

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

    }

    @Override
    public void onPause() {
        super.onPause();
        sharedPreferences.edit().putStringSet(KeyUtils.prevSearchHistoryPrefKey + app.email, new LinkedHashSet<>(app.previousSearch))
                .apply();
    }
}
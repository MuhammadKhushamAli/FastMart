package com.example.fastmart;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class BuyFragment extends Fragment implements View.OnClickListener {
    private static final String ARG_MSG = "message";
    private String paramMsg;
    OnClickListener parentActivity;
    interface OnClickListener {
        public void onClick();
    }


    public BuyFragment() {
        // Required empty public constructor
    }

    public static BuyFragment newInstance(String paramMsg) {
        BuyFragment fragment = new BuyFragment();
        Bundle args = new Bundle();
        args.putString(ARG_MSG, paramMsg);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        parentActivity = (OnClickListener) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            paramMsg = getArguments().getString(ARG_MSG);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_buy_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView message = view.findViewById(R.id.bought_item_message);
        message.setText(paramMsg);
        view.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        parentActivity.onClick();
    }
}
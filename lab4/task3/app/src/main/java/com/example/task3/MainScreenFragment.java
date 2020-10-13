package com.example.task3;

import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MainScreenFragment extends Fragment {

    Button franceButton, spainButton, greeceButton;

    public static MainScreenFragment newInstance() {
        MainScreenFragment fragment = new MainScreenFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_homepage, container, false);
        super.onCreate(savedInstanceState);

        franceButton = view.findViewById(R.id.france);
        spainButton = view.findViewById(R.id.spain);
        greeceButton = view.findViewById(R.id.greece);

        franceButton.setOnClickListener(this.getListener(1));
        spainButton.setOnClickListener(this.getListener(2));
        greeceButton.setOnClickListener(this.getListener(3));

        return view;
    }

    private View.OnClickListener getListener(final int index) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getContext()).pager.setCurrentItem(index);
            }
        };
    }
}

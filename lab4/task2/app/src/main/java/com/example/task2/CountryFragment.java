package com.example.task2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import android.app.Fragment;

import com.bumptech.glide.Glide;

public class CountryFragment extends Fragment {

    private Country country;

    ImageView photoView;
    TextView nameView;
    TextView descriptionView;

    public static CountryFragment newInstance(Country country) {
        CountryFragment fragment = new CountryFragment();
        Bundle args = new Bundle();
        args.putSerializable(Country.INTENT_KEY, country);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
            country = (Country) getArguments().getSerializable(Country.INTENT_KEY);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_country, container, false);

        super.onCreate(savedInstanceState);

        photoView = view.findViewById(R.id.photo);
        nameView = view.findViewById(R.id.name);
        descriptionView = view.findViewById(R.id.description);

        nameView.setText(this.country.name);
        descriptionView.setText(this.country.description);
        Glide.with(this).load(this.country.photoUrl).into(photoView);
        return view;
    }
}



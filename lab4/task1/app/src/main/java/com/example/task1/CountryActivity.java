package com.example.task1;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class CountryActivity extends AppCompatActivity {

    ImageView photoView;
    TextView nameView;
    TextView descriptionView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);

        photoView = findViewById(R.id.photo);
        nameView = findViewById(R.id.name);
        descriptionView = findViewById(R.id.description);

        Country country = (Country)getIntent().getExtras().get(Country.INTENT_KEY);

        nameView.setText(country.name);
        descriptionView.setText(country.description);
        Glide.with(this).load(country.photoUrl).into(photoView);
    }
}

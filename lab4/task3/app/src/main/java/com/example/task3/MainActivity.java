package com.example.task3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Country[] countries = new Country[] { new Country("France", "France, officially the French Republic, is a country consisting of metropolitan France in Western Europe and several overseas regions and territories.", "https://cdn.britannica.com/23/185823-004-5AC19A71/Flag-Reunion-French-dependency.jpg"),
        new Country("Spain", "Spain, officially the Kingdom of Spain, is a country in Southwestern Europe with some pockets of territory across the Strait of Gibraltar and the Atlantic Ocean.", "https://ugmk.com/upload/medialibrary/886/spain_state_hi.jpg"),
        new Country("Greece", "Greece, officially the Hellenic Republic, known also as Hellas, is a country located in Southeast Europe. Its population is approximately 10.7 million as of 2018.", "https://www.techperevod.com/wp-content/uploads/2018/04/gr.png") };

        CountriesPagerAdapter adapter = new CountriesPagerAdapter(getSupportFragmentManager(), countries);
        pager = findViewById(R.id.container);
        pager.setAdapter(adapter);
        pager.setCurrentItem(0);
    }
}
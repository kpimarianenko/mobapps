package com.example.task2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentTransaction;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button franceButton, spainButton, greeceButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        franceButton = findViewById(R.id.france);
        spainButton = findViewById(R.id.spain);
        greeceButton = findViewById(R.id.greece);

        Country france = new Country("France", "France, officially the French Republic, is a country consisting of metropolitan France in Western Europe and several overseas regions and territories.", "https://cdn.britannica.com/23/185823-004-5AC19A71/Flag-Reunion-French-dependency.jpg");
        Country spain = new Country("Spain", "Spain, officially the Kingdom of Spain, is a country in Southwestern Europe with some pockets of territory across the Strait of Gibraltar and the Atlantic Ocean.", "https://ugmk.com/upload/medialibrary/886/spain_state_hi.jpg");
        Country greece = new Country("Greece", "Greece, officially the Hellenic Republic, known also as Hellas, is a country located in Southeast Europe. Its population is approximately 10.7 million as of 2018.", "https://www.techperevod.com/wp-content/uploads/2018/04/gr.png");

        franceButton.setOnClickListener(this.getListenerAndSetNewLayout(france));
        spainButton.setOnClickListener(this.getListenerAndSetNewLayout(spain));
        greeceButton.setOnClickListener(this.getListenerAndSetNewLayout(greece));
    }

    private View.OnClickListener getListenerAndSetNewLayout(final Country country) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                CountryFragment fragment = CountryFragment.newInstance(country);
                ft.replace(R.id.container, fragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        };
    }


}
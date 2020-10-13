package com.example.task2;

import java.io.Serializable;

public class Country implements Serializable {
    String name;
    String description;
    String photoUrl;

    public static final String INTENT_KEY = "Country";

    public Country(String name, String description, String photoUrl) {
        this.name = name;
        this.description = description;
        this.photoUrl = photoUrl;
    }
}

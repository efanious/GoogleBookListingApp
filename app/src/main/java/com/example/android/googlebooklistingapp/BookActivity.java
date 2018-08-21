package com.example.android.googlebooklistingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class BookActivity extends AppCompatActivity {

    public static final String LOG_TAG = BookActivity.class.getName();

    /** URL for books data from the Googlebooks dataset */
    private static final String GOOGLEBOOKS_REQUEST_URL =
            "https://www.googleapis.com/books/v1/volumes?q=cakes&maxResults=2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
    }
}

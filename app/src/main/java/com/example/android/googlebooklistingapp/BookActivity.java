package com.example.android.googlebooklistingapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {

    /** Adapter for the list of books */
    private BooksAdapter mAdapter;

    public static final String LOG_TAG = BookActivity.class.getName();
    private ProgressBar mLoadingProgress;
    private ListView bookListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        mLoadingProgress = (ProgressBar) findViewById(R.id.progressBar);

        try {
            URL bookUrl = ApiUtil.buildUrl("Nigeria");
            new BooksQueryTask().execute(bookUrl);
        }
        catch (Exception e) {
            Log.d("error", e.getMessage());
        }

//        // Find a reference to the {@link ListView} in the layout
//
//
//        // Create a new adapter that takes an empty list of books as input
//        mAdapter = new BooksAdapter(this, new ArrayList<Book>());
//
//        // Set the adapter on the {@link ListView}
//        // so the list can be populated in the user interface
//        bookListView.setAdapter(mAdapter);

    }

    public class BooksQueryTask extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {
            URL searchURL = urls[0];
            String result = null;
            try {
                result = ApiUtil.getJson(searchURL);
            }
            catch (IOException e) {
                Log.e("Error", e.getMessage());
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            mLoadingProgress.setVisibility(View.INVISIBLE);
            ArrayList<Book> books = ApiUtil.getBooksFromJson(result);

            mAdapter = new BooksAdapter(this,books) ;
            bookListView = (ListView) findViewById(R.id.list);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingProgress.setVisibility(View.VISIBLE);
        }
    }
}


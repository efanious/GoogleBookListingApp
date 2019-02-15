package com.example.android.googlebooklistingapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class BookActivity extends AppCompatActivity {

    private BookRecyclerAdapter mAdapter;
    public static final String LOG_TAG = BookActivity.class.getName();

    /** URL for books data from the Googlebooks dataset */
    private static final String GOOGLEBOOKS_REQUEST_URL =
            "https://www.googleapis.com/books/v1/volumes?q=cars++&maxResults=12";

    private RecyclerView mBookRecyclerView;
    private ProgressBar loadingBar;
    private TextView mErrorMessageDisplay;
    private List<Book> booksLists;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        mBookRecyclerView = (RecyclerView) findViewById(R.id.books_list);
        loadingBar = (ProgressBar)findViewById(R.id.loading);

        /* This TextView is used to display errors and will be hidden if there are no errors */
        mErrorMessageDisplay = (TextView) findViewById(R.id.tv_error_message_display);

        booksLists = new ArrayList<>();

        LinearLayoutManager userLayoutManager = new LinearLayoutManager(this);

        mBookRecyclerView.setLayoutManager(userLayoutManager);
        mBookRecyclerView.setHasFixedSize(true);


        mAdapter = new BookRecyclerAdapter(booksLists);
        mBookRecyclerView.setAdapter(mAdapter);

        loadUrlData();


    }

    private void loadUrlData() {
        new FetchDataTask().execute(GOOGLEBOOKS_REQUEST_URL);
    }

    public class FetchDataTask extends AsyncTask<String, Void, List<Book>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loadingBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected List<Book> doInBackground(String... params) {
            /* If there's no zip code, there's nothing to look up. */
            if (params.length == 0) {
                return null;
            }


            String url = params[0];

            if (url == null) {
                return null;
            }

            // Perform the network request, parse the response, and extract a list of users.
            List<Book> users = BookUtils.fetchBookData(url);
            return users;

        }

        @Override
        protected void onPostExecute(List<Book> bookData) {
            loadingBar.setVisibility(View.INVISIBLE);

            if (bookData != null) {
                mAdapter.setUserData(bookData);
            } else {
                showErrorMessage();
            }
        }
    }

    private void showErrorMessage() {
        mBookRecyclerView.setVisibility(View.INVISIBLE);
        /* Then, show the error */
        mErrorMessageDisplay.setVisibility(View.VISIBLE);
    }



}

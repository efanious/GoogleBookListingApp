package com.example.android.googlebooklistingapp;


import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class BookActivity extends AppCompatActivity {

    private BookRecyclerAdapter mAdapter;
    public static final String LOG_TAG = BookActivity.class.getName();

//    /** URL for books data from the Googlebooks dataset */
//    private static final String GOOGLEBOOKS_REQUEST_URL =
//            "https://www.googleapis.com/books/v1/volumes?q=cars++&maxResults=12";

    private RecyclerView mBookRecyclerView;
    private ProgressBar loadingBar;
    private TextView mErrorMessageDisplay;
    private List<Book> booksLists;

    private String queryRequestUrl;
    private SearchView mSearchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        setupViewsAndLoadData();


    }



    private void buildUserUrl(String query) {
        if (query == null)
            queryRequestUrl = null;
        else{
            Uri.Builder builder = new Uri.Builder();
            String userQuery = query;
            builder.scheme("https")
                    .authority("www.googleapis.com")
                    .appendPath("books")
                    .appendPath("v1")
                    .appendPath("volumes")
                    .appendQueryParameter("q", userQuery)
                    .appendQueryParameter("maxResults", "12");

            String myUrl = builder.build().toString();

            queryRequestUrl = myUrl;
        }

    }


    private void loadUrlData() {
        new FetchDataTask().execute(queryRequestUrl);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        // Adds a search dialog to the app
        MenuItem searchItem = menu.findItem(R.id.action_search);
        mSearchView = (SearchView) searchItem.getActionView();

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mBookRecyclerView.setVisibility(View.VISIBLE);
                mErrorMessageDisplay.setVisibility(View.INVISIBLE);
                buildUserUrl(query);
                setupViewsAndLoadData();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;
    }

    private void setupViewsAndLoadData() {

        mBookRecyclerView = (RecyclerView) findViewById(R.id.books_list);
        loadingBar = (ProgressBar)findViewById(R.id.loading);


        /* This TextView is used to display errors and will be hidden if there are no errors */
        mErrorMessageDisplay = (TextView) findViewById(R.id.tv_error_message_display);

        booksLists = new ArrayList<>();

        LinearLayoutManager userLayoutManager = new LinearLayoutManager(this);

        mBookRecyclerView.setLayoutManager(userLayoutManager);
        mBookRecyclerView.setHasFixedSize(true);

        mBookRecyclerView.addItemDecoration(new DividerItemDecoration
                (mBookRecyclerView.getContext(),DividerItemDecoration.VERTICAL));

        mAdapter = new BookRecyclerAdapter(booksLists);
        mBookRecyclerView.setAdapter(mAdapter);


        loadUrlData();
    }


}

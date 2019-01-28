package com.example.android.googlebooklistingapp;

import android.app.SearchManager;
import android.content.Intent;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class BookActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<List<Book>>{

    /** Adapter for the list of books */

    private BookAdapter mAdapter;

    public static final String LOG_TAG = BookActivity.class.getName();

    /** URL for books data from the Googlebooks dataset */
    private static final String GOOGLEBOOKS_REQUEST_URL =
            "https://www.googleapis.com/books/v1/volumes?q=cars++&maxResults=12";

    private static final int BOOK_LOADER_ID = 1;


    private String searchQuery;

    private String newRequestUrl =
            "https://www.googleapis.com/books/v1/volumes?q=cars++&maxResults=12";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        // Get the intent, verify the action and get the query
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            doMySearch(query);
        }


        // Find a reference to the {@link ListView} in the layout
        ListView bookListView = (ListView) findViewById(R.id.list);

        View emptyView = findViewById(R.id.empty_view);
        bookListView.setEmptyView(emptyView);

        // Create a new adapter that takes an empty list of books as input
        mAdapter = new BookAdapter(this, new ArrayList<Book>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        bookListView.setAdapter(mAdapter);


//        if (searchQuery == "" || searchQuery == null){
//
//        }else{
//            getSupportLoaderManager().initLoader(BOOK_LOADER_ID, null, this);
//        }

        getSupportLoaderManager().initLoader(BOOK_LOADER_ID, null, this);



    }

    // Still struggling to make this thing work

    private void doMySearch(String query) {

        newRequestUrl =
                "https://www.googleapis.com/books/v1/volumes?q=shoes++&maxResults=12";

        // Find a reference to the {@link ListView} in the layout
        ListView bookListView = (ListView) findViewById(R.id.list);

        View emptyView = findViewById(R.id.empty_view);
        bookListView.setEmptyView(emptyView);

        // Create a new adapter that takes an empty list of books as input
        mAdapter = new BookAdapter(this, new ArrayList<Book>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        bookListView.setAdapter(mAdapter);

        getSupportLoaderManager().initLoader(2, null, this);




    }

    @Override
    public Loader<List<Book>> onCreateLoader(int id, Bundle args) {

        return new BookLoader(BookActivity.this,GOOGLEBOOKS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> data) {

        // Clear the adapter of previous earthquake data
        mAdapter.clear();

        // If there is a valid list of {@link Book}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (data != null && !data.isEmpty()) {

            mAdapter.addAll(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
        // Loader reset, so we can clear out our existing data.
        mAdapter.clear();

    }

    // Menu icons are inflated just as they were with actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                onSearchRequested();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }



}

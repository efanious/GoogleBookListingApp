package com.example.android.googlebooklistingapp;

public class Book {

    /** Title of the book */
    private String mTitle;

    public Book(String title){
        mTitle = title;
    }

    /**
     * Returns the title of the book
     */
    public String getTitle() {
        return mTitle;
    }
}

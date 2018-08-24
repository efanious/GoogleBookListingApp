package com.example.android.googlebooklistingapp;

public class Book {

    /** Title of the book */
    private String mTitle;

    /** Authors of the book */
    private String mAuthor;

    public Book(String title,String author){
        mTitle = title;
        mAuthor = author;
    }

    /**
     * Returns the title of the book
     */
    public String getTitle() {
        return mTitle;
    }

    /**
     * Returns the authors of the book
     */
    public String getAuthor() {
        return mAuthor;
    }
}

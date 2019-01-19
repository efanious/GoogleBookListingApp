package com.example.android.googlebooklistingapp;

public class Book {

    /** Title of the book */
    private String mTitle;

    /** Authors of the book */
    private String mAuthor;

    /** Thumbnail for the book image */
    private String mThumbnail;

    public Book(String title,String author, String thumbnail){
        mTitle = title;
        mAuthor = author;
        mThumbnail = thumbnail;
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


    public String getThumbnail() {
        return mThumbnail;
    }
}

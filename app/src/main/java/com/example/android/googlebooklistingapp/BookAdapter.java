package com.example.android.googlebooklistingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


public class BookAdapter extends ArrayAdapter {

    public BookAdapter(Context context, List<Book> books) {
        super(context, 0, books);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.book_list_item, parent, false);
        }

        // Find the book at the given position in the list of books
        Book currentBook = (Book) getItem(position);

        // Find the TextView with ID title
        TextView titleView = (TextView) listItemView.findViewById(R.id.title);

        // Display the title of the current book in that TextView
        titleView.setText(currentBook.getTitle());

        // Find the TextView with ID author
        TextView authorView = (TextView) listItemView.findViewById(R.id.author);

        // Display the authors of the current book in that TextView
        authorView.setText(currentBook.getAuthor());




        return listItemView;

    }
}

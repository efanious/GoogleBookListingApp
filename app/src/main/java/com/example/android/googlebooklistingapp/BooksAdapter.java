package com.example.android.googlebooklistingapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.BookViewHolder> {

    ArrayList<Book> books;

    public BooksAdapter(ArrayList<Book> books) {
        this.books = books;
    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.book_list_item, parent, false);
        return new BookViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {
        Book book = books.get(position);
        holder.bind(book);

    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public class BookViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        TextView tvAuthor;
        TextView tvPublisher;
        TextView tvDate;


        public BookViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvAuthor = (TextView) itemView.findViewById(R.id.tvAuthor);
            tvPublisher = (TextView) itemView.findViewById(R.id.tvPublisher);
            tvDate = (TextView) itemView.findViewById(R.id.tvPublishedDate);

        }

        public void bind (Book book) {
            tvTitle.setText(book.title);
            String authors = "";
            int i = 0;
            for (String author: book.authors) {
                authors += author;
                i++;
                if (i < book.authors.length) {
                    authors += ", ";
                }
            }
            tvAuthor.setText(authors);
            tvDate.setText(book.publishedDate);
            tvPublisher.setText(book.publisher);
        }
    }

//    public BooksAdapter(BookListActivity.BooksQueryTask context, ArrayList<Book> books) {
//        super(context, 0, books);
//    }
//
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        View listItemView = convertView;
//
//        if (listItemView == null) {
//            listItemView = LayoutInflater.from(getContext()).inflate(
//                    R.layout.book_list_item, parent, false);
//        }
//
//        // Find the book at the given position in the list of books
//        Book currentBook = (Book) getItem(position);
//
//        // Find the TextView with ID title
//        TextView titleView = (TextView) listItemView.findViewById(R.id.title);
//
//        // Display the title of the current book in that TextView
//        titleView.setText(currentBook.title);
//
//
//        return listItemView;
//
//    }
}

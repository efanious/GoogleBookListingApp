package com.example.android.googlebooklistingapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.List;

class BookRecyclerAdapter extends RecyclerView.Adapter<BookRecyclerAdapter.ViewHolder> {

    private List<Book> mBooks;


    public BookRecyclerAdapter(List<Book> books) {
        this.mBooks = books;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_list_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Book currentBook = mBooks.get(position);
        holder.mTitle.setText(currentBook.getTitle());
        holder.mAuthor.setText(currentBook.getAuthor());
        Picasso.get().load(currentBook.getThumbnail()).into(holder.mThumbnail);

    }

    @Override
    public int getItemCount() {
        return mBooks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public final TextView mTitle, mAuthor;
        public final ImageView mThumbnail;

        public ViewHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.title);
            mAuthor = (TextView) itemView.findViewById(R.id.author);
            mThumbnail = (ImageView) itemView.findViewById(R.id.bookImage);

        }
    }


    public void setUserData(List<Book> books) {
        mBooks = books;
        notifyDataSetChanged();
    }
}

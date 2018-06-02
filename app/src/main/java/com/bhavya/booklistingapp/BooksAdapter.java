package com.bhavya.booklistingapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Bhavya Arora on 1/10/2018.
 */

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.MyViewHolder> {

    private Context mContext;
    private List<book> bookList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, author;
        public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            author = (TextView) view.findViewById(R.id.count);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
        }

    }


    public BooksAdapter(Context mContext, List<book> bookList) {
        this.mContext = mContext;
        this.bookList = bookList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_card, parent, false);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int itemPosition = parent.indexOfChild(view);
                book returnedBook = bookList.get(itemPosition);
                String infoUrl = returnedBook.getInfoUrl();
                //Intent intent = new Intent(Intent.ACTION_VIEW);
                //intent.setData(Uri.parse(infoUrl));

                Intent intent = new Intent(mContext,bookListView.class);
                intent.putExtra("infoUrl", infoUrl);

                //browser chooser
                //Intent.createChooser(intent, "Choose Broser");
                mContext.startActivity(intent);
            }
        });

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        book returnedBook = bookList.get(position);
        holder.title.setText(returnedBook.getTitle());
        holder.author.setText(returnedBook.getAuthor());

        // loading Book cover using Glide library
        Glide.with(mContext).load(returnedBook.getImageUrl()).into(holder.thumbnail);
    }


    @Override
    public int getItemCount() {
        return bookList.size();
    }
}
package com.bhavya.booklistingapp;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by Bhavya Arora on 1/10/2018.
 */

public class booksLoader extends AsyncTaskLoader<List<book>> {
    String url;

    public booksLoader(Context context, String url) {
        super(context);
        if(url == null){
            return;
        }
        this.url = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<book> loadInBackground() {
        if(url == null) {
            return null;
        }
        List<book> arrayList = QueryUtils.fetchBooksData(url);
        return arrayList;
    }
}

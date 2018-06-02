package com.bhavya.booklistingapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Bhavya Arora on 1/10/2018.
 */

//Implemented Parcelable to serialize the book Object so that it can pass over.
public class book implements Parcelable{
    private String title;
    private String author;
    private String infoUrl;
    private String imageUrl;
    private String publisher;

    public book(String title, String author, String infoUrl, String imageUrl, String publisher){
        this.title = title;
        this.author = author;
        this.infoUrl = infoUrl;
        this.imageUrl = imageUrl;
        this.publisher = publisher;
    }

    private book(Parcel in) {
        title = in.readString();
        author = in.readString();
        infoUrl = in.readString();
        imageUrl = in.readString();
        publisher = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<book> CREATOR = new Creator<book>() {
        @Override
        public book createFromParcel(Parcel in) {
            return new book(in);
        }

        @Override
        public book[] newArray(int size) {
            return new book[size];
        }
    };


    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getInfoUrl() {
        return infoUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getPublisher() {
        return publisher;
    }


    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(author);
        parcel.writeString(infoUrl);
        parcel.writeString(imageUrl);
        parcel.writeString(publisher);
    }
}

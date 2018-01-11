package com.bhavya.booklistingapp;

/**
 * Created by Bhavya Arora on 1/10/2018.
 */

public class book {
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
}

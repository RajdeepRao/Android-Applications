package com.example.rajdeeprao.inclass05;

/**
 * Created by rajdeeprao on 2/13/17.
 */

public class Articles {
    String title, description, pubDate, imageURL;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @Override
    public String toString() {
        return  "Title :" + title + '\n'+ '\n' + '\n' +
                " Description :" + description + '\n'+'\n' +
                " PubDate :" + pubDate + '\n'
                ;
    }
}

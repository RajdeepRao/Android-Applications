package com.example.rajdeep.http_json;

/*
* Assignment# InClass04
* File Name: MainActivity.java
* Yateen Kedare |  Rajdeep Rao
* */
public class NewsArticle {
    String urlToImage, title, author,description,publishedAt;
    NewsArticle(String urlToImage, String title, String author,String description, String publishedAt){
        this.urlToImage = urlToImage;
        this.title = title;
        this.author = author;
        this.description = description;
        this.publishedAt = publishedAt;
    }
    NewsArticle(){}

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    @Override
    public String toString() {
        return
                title + "\nAuthor:" + author + '\'' +
                        "\nPublishedAt:" + publishedAt + '\'' +
                        " \n\n Description:\n'" + description ;
    }
}
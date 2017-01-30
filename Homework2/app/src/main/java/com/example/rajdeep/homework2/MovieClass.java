package com.example.rajdeep.homework2;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Rajdeep on 1/26/2017.
 */

public class MovieClass implements Parcelable,Comparable<MovieClass> {
    private String name, description, genre, imdb, year, rating;

    protected MovieClass(Parcel in) {
        name = in.readString();
        description = in.readString();
        genre = in.readString();
        imdb = in.readString();
        year = in.readString();
        rating = in.readString();
    }

    public static final Creator<MovieClass> CREATOR = new Creator<MovieClass>() {
        @Override
        public MovieClass createFromParcel(Parcel in) {
            return new MovieClass(in);
        }

        @Override
        public MovieClass[] newArray(int size) {
            return new MovieClass[size];
        }
    };

    @Override
    public String toString() {
        return "MovieClass{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", genre='" + genre + '\'' +
                ", imdb='" + imdb + '\'' +
                ", year='" + year + '\'' +
                ", rating=" + rating +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getImdb() {
        return imdb;
    }

    public void setImdb(String imdb) {
        this.imdb = imdb;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public MovieClass(String name, String description, String genre, String imdb, String year, String rating) {

        this.name = name;
        this.description = description;
        this.genre = genre;
        this.imdb = imdb;
        this.year = year;
        this.rating = rating;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(genre);
        dest.writeString(imdb);
        dest.writeString(year);
        dest.writeString(rating);
    }


    public int compareTo(MovieClass o) {
        if(Integer.parseInt(this.year) <Integer.parseInt(o.getYear())){
            return -1;
        }
        else if(Integer.parseInt(this.year) >Integer.parseInt(o.getYear())){
            return 1;
        }
        else
        return 0;
    }

}

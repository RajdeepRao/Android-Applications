package com.example.rajdeep.homework2;

import android.content.Intent;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class DisplayListRatingActivity extends AppCompatActivity {

    ArrayList<MovieClass> movieList=new ArrayList<MovieClass>();
    int pos=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_list_year);


        movieList=getIntent().getExtras().getParcelableArrayList(MainActivity.MOVIE_ARRAY_VALUE);
        Collections.sort(movieList, new Comparator<MovieClass>() {
            @Override
            public int compare(MovieClass o1, MovieClass o2) {
                if(Integer.parseInt(o1.getRating())<Integer.parseInt(o2.getRating()))
                    return -1;
                else if(Integer.parseInt(o1.getRating())>Integer.parseInt(o2.getRating()))
                    return 1;
                else
                    return 0;
            }
        });
        if(movieList.isEmpty()){
            Toast.makeText(this, "Empty List",Toast.LENGTH_LONG).show();
            finish();
        }

        else{
        final TextView title=(TextView) findViewById(R.id.titleValue);
        title.setText(movieList.get(pos).getName());

        final TextView description=(TextView) findViewById(R.id.descriptionValue);
        description.setText(movieList.get(pos).getDescription());

        final TextView genre=(TextView) findViewById(R.id.genreValue);
        genre.setText(movieList.get(pos).getGenre());

        final TextView rating=(TextView) findViewById(R.id.ratingValue);
        rating.setText(movieList.get(pos).getRating()+"/5");

        final TextView year=(TextView) findViewById(R.id.yearValue);
        year.setText(movieList.get(pos).getYear());

        final TextView imdb=(TextView) findViewById(R.id.imdbVal);
        imdb.setText(movieList.get(pos).getImdb());



        findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pos<movieList.size()-1){
                    pos++;
                    title.setText(movieList.get(pos).getName());
                    description.setText(movieList.get(pos).getDescription());
                    year.setText(movieList.get(pos).getYear());
                    imdb.setText(movieList.get(pos).getImdb());
                    rating.setText(movieList.get(pos).getRating()+"/5");
                    genre.setText(movieList.get(pos).getGenre());
                }
                else
                {
                    pos=movieList.size()-1;
                    Toast.makeText(DisplayListRatingActivity.this,"End of your movies",Toast.LENGTH_LONG).show();
                }


            }
        });

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pos>0){
                    pos--;
                    title.setText(movieList.get(pos).getName());
                    description.setText(movieList.get(pos).getDescription());
                    year.setText(movieList.get(pos).getYear());
                    imdb.setText(movieList.get(pos).getImdb());
                    rating.setText(movieList.get(pos).getRating()+"/5");
                    genre.setText(movieList.get(pos).getGenre());
                }
                else
                {
                    pos=0;
                    Toast.makeText(DisplayListRatingActivity.this,"End of your movies",Toast.LENGTH_LONG).show();
                }


            }

        });
        findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pos=0;
                title.setText(movieList.get(pos).getName());
                description.setText(movieList.get(pos).getDescription());
                year.setText(movieList.get(pos).getYear());
                imdb.setText(movieList.get(pos).getImdb());
                rating.setText(movieList.get(pos).getRating()+"/5");
                genre.setText(movieList.get(pos).getGenre());
            }
        });

        findViewById(R.id.last).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pos=movieList.size()-1;
                title.setText(movieList.get(pos).getName());
                description.setText(movieList.get(pos).getDescription());
                year.setText(movieList.get(pos).getYear());
                imdb.setText(movieList.get(pos).getImdb());
                rating.setText(movieList.get(pos).getRating()+"/5");
                genre.setText(movieList.get(pos).getGenre());
            }
        });

        findViewById(R.id.finish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    }
}

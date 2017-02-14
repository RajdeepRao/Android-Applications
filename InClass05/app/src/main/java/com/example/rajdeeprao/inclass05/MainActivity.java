package com.example.rajdeeprao.inclass05;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements GetNews.IData{
    TextView loading;
    ProgressBar progressBar;
    int count;
    ArrayList<Articles> articleList;
    LinearLayout linearLayout;
    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.drawable.cnn);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);;
        progressBar= (ProgressBar) findViewById(R.id.progressBar);
        loading= (TextView) findViewById(R.id.loading);
        articleList=new ArrayList<Articles>();
        Button first= (Button) findViewById(R.id.first);
        Button previous= (Button) findViewById(R.id.previous);
        final Button next= (Button) findViewById(R.id.next);
        linearLayout= (LinearLayout) findViewById(R.id.scrollViewLinearLayout);
        Button last= (Button) findViewById(R.id.last);
        iv= (ImageView) findViewById(R.id.imageView);
        first.setBackgroundResource(R.drawable.first);
        previous.setBackgroundResource(R.drawable.previous);
        next.setBackgroundResource(R.drawable.next);
        last.setBackgroundResource(R.drawable.last);

        ImageView iv= (ImageView) findViewById(R.id.imageView);


        Button getNews= (Button) findViewById(R.id.getNews);
        getNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new GetNews(MainActivity.this).execute("http://rss.cnn.com/rss/cnn_tech.rss");
                progressBar.setVisibility(View.VISIBLE);
                loading.setVisibility(View.VISIBLE);

            }
        });

        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count=0;
                displayArticles();
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count!=0){
                    count-=1;
                    displayArticles();
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count<articleList.size()-1){
                    count+=1;
                    displayArticles();
                }
            }
        });

        last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count=articleList.size()-1;
                displayArticles();
            }
        });
        final Button finish= (Button) findViewById(R.id.finish);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void displayArticles(){
        //new GetImage(MainActivity.this).execute(newsArticleArray.get(newsVarible).getUrlToImage());
        linearLayout.removeAllViews();
        TextView tv=new TextView(MainActivity.this);
        Log.d("TEST",articleList.get(count).toString());
        Log.d("TEST",Integer.toString(count));
        tv.setText(articleList.get(count).toString());
        if(articleList.get(count).getImageURL()!=null){
            Picasso.with(this)
                    .load(articleList.get(count).getImageURL())
                    .into(iv, new Callback() {
                        @Override
                        public void onSuccess() {

                        }

                        @Override
                        public void onError() {

                        }
                    });
        }

        else{
            iv= (ImageView) findViewById(R.id.imageView);
            iv.setImageBitmap(null);
            progressBar.setVisibility(View.GONE);
            loading.setVisibility(View.GONE);
        }

        linearLayout.addView(tv);
    }

    @Override
    public void resultValues(ArrayList<Articles> articles) throws IOException, XmlPullParserException {
        articleList=articles;
        Log.d("Received",Integer.toString(articles.size()));
        progressBar.setVisibility(View.GONE);
        loading.setVisibility(View.GONE);
        for(int i=0;i<articleList.size();i++){
            Log.d("Received",articleList.get(i).toString());
        }
        count=0;
        displayArticles();
    }

    static ArrayList<Articles> parseResults(InputStream s) throws XmlPullParserException, IOException {
        Log.d("Received","Entered");

        XmlPullParser parser= XmlPullParserFactory.newInstance().newPullParser();
        parser.setInput(s,"UTF-8");
        Articles article=null;
        ArrayList<Articles> articleList=new ArrayList<Articles>();
        int event=parser.getEventType();

        while(event!=XmlPullParser.END_DOCUMENT){
            switch(event){
                case XmlPullParser.START_TAG:
                    if(parser.getName().equals("item")){
                        article=new Articles();
                        Log.d("Received","EnteredITEM");
                    }
                    else if(parser.getName().equals("title")){
                        if(article!=null){
                            article.setTitle(parser.nextText().trim());
                        }
                    }
                    else if(parser.getName().equals("pubDate")){
                        if(article!=null){
                            article.setPubDate(parser.nextText().trim());
                            Date d=new Date(parser.nextText().trim());
                            SimpleDateFormat date=new SimpleDateFormat("yyyy-MM-dd");
                            date.format(d);
                        }

                    }
                    else if(parser.getName().equals("description")){
                        if(article!=null){
                            article.setDescription(parser.nextText().trim());
                        }

                    }
                    else if(parser.getName().equals("media:content")){
                        if(article!=null){
                            if(parser.getAttributeValue(null,"height")==parser.getAttributeValue(null,"width"))
                            article.setImageURL(parser.getAttributeValue(null,"url"));
                        }

                    }

                    break;
                case XmlPullParser.END_TAG:
                    if(parser.getName().equals("item")){
                        Log.d("Received",article.toString());
                        articleList.add(article);
                        article=null;
                    }

                    break;

                default:
                    break;
            }
            event=parser.next();
        }
        Log.d("Received",Integer.toString(articleList.size()));

        return articleList;
    }
}

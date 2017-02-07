package com.example.rajdeep.http_json;
/*
* Assignment# InClass04
* File Name: MainActivity.java
* Yateen Kedare |  Rajdeep Rao
* */
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements GetNewsArticles.IData,GetImage.IData {

    String[] newsChannels;
    int spinnerPosition=0,newsVarible=0;
    String result;
    ArrayList<NewsArticle> newsArticleArray;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newsChannels= getResources().getStringArray(R.array.news_array);
        tv = (TextView) findViewById(R.id.tvArticles);
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerPosition = position;
                Log.d("Spinner :: ", ""+position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        final Button btnGetNews = (Button) findViewById(R.id.button);
        btnGetNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isConnectedOnline()){}
                else{
                    Toast.makeText(MainActivity.this,"Disconnected",Toast.LENGTH_LONG).show();
                }

                String websiteURL = "https://newsapi.org/v1/articles?apiKey=f8377b2e86a241ccb80da95bbab9d378&source="+newsChannels[spinnerPosition];
                Log.d("Button :: ", ""+newsChannels[spinnerPosition]+"");
                new GetNewsArticles(MainActivity.this).execute(websiteURL);
                newsVarible=0;

            }
        });

        ImageView first = (ImageView) findViewById(R.id.btnFirst);
        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newsVarible=0;
                displayNews();
            }
        });
        ImageView prev = (ImageView) findViewById(R.id.btnPrev);
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(newsVarible!=0){
                    newsVarible-=1;
                    displayNews();
                }
            }
        });
        ImageView next = (ImageView) findViewById(R.id.btnNext);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(newsVarible<newsArticleArray.size()-1){
                    newsVarible+=1;
                    displayNews();
                }
            }
        });
        ImageView last = (ImageView) findViewById(R.id.btnLast);
        last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newsVarible=newsArticleArray.size()-1;
                displayNews();
            }
        });
        final Button finish = (Button) findViewById(R.id.btnFinish);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        
    }
    private boolean isConnectedOnline(){
        ConnectivityManager cm=(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=cm.getActiveNetworkInfo();
        if(networkInfo!=null && networkInfo.isConnected())
            return true;
        else
            return false;
    }

    @Override
    public void resultValues(String result) {
        this.result=result;
        Log.d("RESULT",result);
        parseJSON(result);
    }

    public ArrayList<NewsArticle> parseJSON(String result){
        newsArticleArray=new ArrayList<NewsArticle>();
        try {
            JSONObject root=new JSONObject(result);
            JSONArray articles=root.getJSONArray("articles");

            for(int i=0;i<articles.length();i++){
                JSONObject articleObject=articles.getJSONObject(i);
                NewsArticle newsArticle=new NewsArticle();
                newsArticle.setAuthor(articleObject.getString("author"));
                newsArticle.setDescription(articleObject.getString("description"));
                newsArticle.setPublishedAt(articleObject.getString("publishedAt"));
                newsArticle.setTitle(articleObject.getString("title"));
                newsArticle.setUrlToImage(articleObject.getString("urlToImage"));

                newsArticleArray.add(newsArticle);
                Log.d("ArrayListNewsArticles",newsArticle.toString());
            }
            displayNews();
            return newsArticleArray;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void displayNews(){
        new GetImage(MainActivity.this).execute(newsArticleArray.get(newsVarible).getUrlToImage());
        tv.setText(newsArticleArray.get(newsVarible).toString());
    }


    @Override
    public void ReturnImage(Bitmap bitmap) {
        ImageView iv= (ImageView) findViewById(R.id.imageView);
        iv.setImageBitmap(bitmap);
    }


    /*
    private class GetImageHTTP extends AsyncTask<String,Void,Bitmap>{

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            ImageView iv=(ImageView) findViewById(R.id.imageView);
            iv.setImageBitmap(bitmap);
            super.onPostExecute(bitmap);
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            try {
                URL url=new URL(params[0]);
                HttpURLConnection con=(HttpURLConnection)url.openConnection();
                Bitmap img= BitmapFactory.decodeStream(con.getInputStream());
                return img;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }*/
}

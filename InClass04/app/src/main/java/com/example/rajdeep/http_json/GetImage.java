package com.example.rajdeep.http_json;
/*
* Assignment# InClass04
* File Name: MainActivity.java
* Yateen Kedare |  Rajdeep Rao
* */
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Rajdeep on 2/6/2017.
 */

class GetImage extends AsyncTask<String,Void,Bitmap> {
    IData activity;
    public GetImage(IData activity){
        this.activity=activity;
    }
    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        activity.ReturnImage(bitmap);

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

    public static interface IData{
        public void ReturnImage(Bitmap bitmap);
    }
}

package com.example.rajdeep.http_json;
/*
* Assignment# InClass04
* File Name: MainActivity.java
* Yateen Kedare |  Rajdeep Rao
* */
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;

/**
 * Created by Rajdeep on 2/6/2017.
 */
class GetNewsArticles extends AsyncTask<String,Void,String> {
    IData activity;
    public  GetNewsArticles(IData activity){
        this.activity=activity;
    }
    @Override
    protected String doInBackground(String... params) {
        BufferedReader br=null;
        try {
            URL url=new URL(params[0]);
            HttpURLConnection con= (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            br=new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder sb=new StringBuilder();
            String line="";
            while ((line=br.readLine())!=null){
                sb.append(line+"\n");
            }
            return sb.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(br!=null)
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        Log.d("Demo",s);
        activity.resultValues(s);
        super.onPostExecute(s);
    }

    static public interface IData{
        public void resultValues(String result);
    }
}


package com.example.rajdeeprao.inclass05;

import android.os.AsyncTask;
import android.util.Log;

import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by rajdeeprao on 2/13/17.
 */

public class GetNews extends AsyncTask<String,Void,ArrayList<Articles>> {
        IData activity;
        public  GetNews(IData activity){
            this.activity=activity;
        }
        @Override
        protected ArrayList<Articles> doInBackground(String... params) {
            BufferedReader br=null;
            ArrayList<Articles> articleList=new ArrayList<Articles>();
            try {
                URL url=new URL(params[0]);
                HttpURLConnection con= (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                articleList=MainActivity.parseResults(con.getInputStream());

                return articleList;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } finally {
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
        protected void onPostExecute(ArrayList<Articles> articles) {
            try {
                activity.resultValues(articles);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }
            super.onPostExecute(articles);
        }

        static public interface IData{
            public void resultValues(ArrayList<Articles> articles) throws IOException, XmlPullParserException;
        }

    }

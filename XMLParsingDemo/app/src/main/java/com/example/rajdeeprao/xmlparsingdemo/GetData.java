package com.example.rajdeeprao.xmlparsingdemo;

import android.os.AsyncTask;
import android.util.Log;

import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by rajdeeprao on 2/13/17.
 */

public class GetData extends AsyncTask<String,Void,InputStream> {
    IData activity;
    GetData(IData activity){
        this.activity=activity;
    }
    @Override
    protected InputStream doInBackground(String... params) {
        try {
            URL url=new URL(params[0]);
            HttpURLConnection con= (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            Log.d("Stream",con.getInputStream().toString());

            return con.getInputStream();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(InputStream s) {
        super.onPostExecute(s);
        MainActivity.pd.dismiss();
        try {
            activity.returnedValues(s);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

    }

    public static interface IData{
        public void returnedValues(InputStream val) throws IOException, XmlPullParserException, SAXException;
    }
}

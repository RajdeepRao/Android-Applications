package com.example.rajdeep.hw03;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    LinearLayout layout;
    ArrayList<String> searchItems=new ArrayList<String>();
    ArrayList<LinearLayout> items=new ArrayList<LinearLayout>();
    ArrayList<Integer> countedValues=new ArrayList<Integer>();
    int count=0;
    int count2=0;
    String val;
    EditText firstInput;
    FloatingActionButton fab;
    StringBuilder sb;
    String file;
    ArrayList<String> words;
    InputStream is;
    ProgressDialog progressDialog;
    boolean checkBoxVal=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        words=new ArrayList<String>();
        AssetManager am = MainActivity.this.getAssets();
        try{
            is = am.open("textfile.txt");
        }
        catch (Exception e){
            e.printStackTrace();
        }

        Button search=(Button)findViewById(R.id.search);

        layout=(LinearLayout)findViewById(R.id.searchItemsVertical);
        firstInput=(EditText)findViewById(R.id.searchItemName);
        fab=(FloatingActionButton)findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                searchItems.add(firstInput.getText().toString());
                Log.d("String",firstInput.getText().toString());
                fab.setImageDrawable(getResources().getDrawable(android.R.drawable.ic_menu_delete));
                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int index= searchItems.indexOf(firstInput.getText().toString());
                        searchItems.remove(index);
                        layout.removeViewAt(0);
                    }
                });
                EditText editText=new EditText(MainActivity.this);
                FloatingActionButton fab2=new FloatingActionButton(MainActivity.this);
                fab2.setSize(FloatingActionButton.SIZE_MINI);
                fab2.setImageDrawable(getResources().getDrawable(android.R.drawable.ic_menu_add));
                editText.setWidth(750);
                LinearLayout layout2=new LinearLayout(MainActivity.this);
                layout2.addView(editText);
                layout2.addView(fab2);
                layout.addView(layout2);
                fab2.setOnClickListener(MainActivity.this);
            }
        });
        CheckBox checkBox=(CheckBox) findViewById(R.id.matchCase);
        checkBoxVal=checkBox.isChecked();
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog=new ProgressDialog(MainActivity.this);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.setCancelable(false);
                progressDialog.setMessage("Calculating");
                progressDialog.show();
                for(int i=0;i<searchItems.size();i++){
                    new WordCount().execute(searchItems.get(i));
                    Log.d("ArrayList", searchItems.get(i));
                }
                Log.d("Counts: ","here ");
                for(int i=0;i<countedValues.size();i++){
                    Log.d("Counts: ","here "+Integer.toString(countedValues.get(i)));
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        count++;
        if(count<=20){
            final LinearLayout temp=(LinearLayout) v.getParent();
            final EditText temp2=(EditText) temp.getChildAt(0);
            final LinearLayout temp3=(LinearLayout)temp.getParent();
            searchItems.add(temp2.getText().toString());
            for(int i=0;i<searchItems.size();i++){
                Log.d("SearchIems",searchItems.get(i));
            }
            FloatingActionButton fab=(FloatingActionButton)v;
            fab.setImageDrawable(getResources().getDrawable(android.R.drawable.ic_menu_delete));
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int index= searchItems.indexOf(temp2.getText().toString());
                    Log.d("Arraylist",Integer.toString(index));
                    if(index>=0)
                        searchItems.remove(index);
                    for(int i=0;i<searchItems.size();i++){
                        Log.d("SearchRemove",searchItems.get(i));
                    }
                    Log.d("Layout",Integer.toString(temp3.indexOfChild(temp)));
                    temp3.removeViewAt(temp3.indexOfChild(temp));

                }
            });
            final EditText editText=new EditText(MainActivity.this);
            FloatingActionButton fab2=new FloatingActionButton(MainActivity.this);
            fab2.setSize(FloatingActionButton.SIZE_MINI);
            fab2.setImageDrawable(getResources().getDrawable(android.R.drawable.ic_menu_add));
            fab2.setOnClickListener(this);
            editText.setWidth(750);
            LinearLayout layout2=new LinearLayout(MainActivity.this);
            layout2.addView(editText);
            layout2.addView(fab2);
            layout.addView(layout2);
        }
        else
            Toast.makeText(MainActivity.this,"Sorry, Max limit reached",Toast.LENGTH_LONG).show();
    }

    class WordCount extends AsyncTask<String,Integer,Integer>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Integer integer) {
            Log.d("Counted: ",Integer.toString(integer));
            countedValues.add(integer);
            if(count2==count)
                progressDialog.dismiss();

            super.onPostExecute(integer);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected Integer doInBackground(String... params) {
            int counter=0;
            count2++;
            sb = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            if(checkBoxVal==false){
                try{
                    String line;
                    while ((line = br.readLine()) != null) {
                        String[] temp=line.split(" ");
                        for(int i=0;i<temp.length;i++){
                            if(params[0].equals(temp[i]))
                                counter++;
                        }
                    }
                    Log.d("Calc",params[0]+Integer.toString(counter));

                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
            else{
                try{
                    String line;
                    while ((line = br.readLine()) != null) {
                        String[] temp=line.split(" ");
                        for(int i=0;i<temp.length;i++){
                            if(params[0].equalsIgnoreCase(temp[i]))
                                counter++;
                        }

                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
            return counter;
        }
    }
}

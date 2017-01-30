package com.example.rajdeep.homework2;

import android.content.DialogInterface;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final int REQ_CODE=100;
    public static final int REQ_CODE_EDIT=101;
    final static String MOVIE_ARRAY_VALUE= "Movie Array value";
    final static String MOVIE_VALUE= "Movie value";
    ArrayList<MovieClass> movieList=new ArrayList<MovieClass>();
    int i=-1;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==REQ_CODE){
            if(resultCode==RESULT_OK) {
                ++i;
                MovieClass intentReceive = data.getExtras().getParcelable(AddMovieActivity.MOVIE_KEY);
                movieList.add(i,intentReceive);
                Log.d("Invalid", "SUCCESS");
            }
            else{
                Log.d("Invalid", "FAILED");
            }
        }
        else if(requestCode==REQ_CODE_EDIT){
            if(resultCode==RESULT_OK){
                MovieClass intentReceive=data.getExtras().getParcelable(EditActivity.MOVIE_UPDATE_KEY);
                int index=data.getExtras().getInt("Index");
                movieList.set(index,intentReceive);
            }
        }
        for(MovieClass val : movieList)
        {
            Log.d("List",val.toString());
        }


    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Toast.makeText(this,getIntent().getExtras().getParcelable(AddMovieActivity.MOVIE_KEY).toString(),Toast.LENGTH_LONG).show();

        Button add= (Button) findViewById(R.id.AddMovie);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, AddMovieActivity.class);
                startActivityForResult(intent,REQ_CODE);

            }
        });



        findViewById(R.id.Edit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this);

                final String[] nameArray=new String[movieList.size()];
                for(int i=0;i<movieList.size();i++){
                    nameArray[i]=movieList.get(i).getName();
                    Log.d("Edit",nameArray[i]);
                }
                builder.setTitle("Pick a Movie")
                        .setItems(nameArray, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.d("Alert","Selected"+nameArray[which]);
                                Intent intent=new Intent(MainActivity.this, EditActivity.class);
                                intent.putExtra(MOVIE_VALUE,movieList.get(which));
                                intent.putExtra("Index",which);
                                startActivityForResult(intent,REQ_CODE_EDIT);
                            }
                        });

                final AlertDialog alert=builder.create();
                alert.show();

            }
        });

        findViewById(R.id.DeleteMovie).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this);

                final String[] nameArray=new String[movieList.size()];
                for(int i=0;i<movieList.size();i++){
                    nameArray[i]=movieList.get(i).getName();
                }
                builder.setTitle("Pick a Movie")
                        .setItems(nameArray, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.d("Alert","Selected"+nameArray[which]);
                                Toast.makeText(MainActivity.this,"Deleted:"+movieList.get(which).getName(),Toast.LENGTH_LONG).show();
                                movieList.remove(which);

                            }
                        });

                final AlertDialog alert=builder.create();
                alert.show();

            }
        });

        findViewById(R.id.ShowListByYear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent("com.example.rajdeep.homework2.intent.action.YEAR");
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                intent.putParcelableArrayListExtra(MOVIE_ARRAY_VALUE,movieList);
                startActivity(intent);
            }
        });

        findViewById(R.id.ShowListByRating).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent("com.example.rajdeep.homework2.intent.action.RATING");
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                intent.putParcelableArrayListExtra(MOVIE_ARRAY_VALUE,movieList);
                startActivity(intent);
            }
        });



    }
}

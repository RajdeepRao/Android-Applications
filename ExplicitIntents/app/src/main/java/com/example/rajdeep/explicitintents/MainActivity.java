package com.example.rajdeep.explicitintents;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {
    final static String NAME_KEY="Name";
    final static String USER_KEY="User";
    final static String PERSON_KEY="Person";
    CharSequence[] items={"abc","def","ghi"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        final ProgressDialog progress=new ProgressDialog(this);
        progress.setMessage("Loading..");
        builder.setTitle("Condition")

                .setSingleChoiceItems(items,0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("Temp","Selected: "+items[which]);
                    }
                });
                /*
                .setMessage("Are you sure? ")
                .setPositiveButton("okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("demo", "Clicked Oka");
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("Demo","Clicked Cancel");
                    }
                });*/
            final AlertDialog alert=builder.create();

        findViewById(R.id.buttonToSecondActivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent= new Intent(MainActivity.this, secondActivity.class); HardcodedIntent
                /*Intent intent = new Intent("com.example.rajdeep.explicitintents.intent.action.VIEW");
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                intent.putExtra(NAME_KEY,"Rajdeep");
                intent.putExtra(USER_KEY, new User("Raj",21));*/

                /*String url = "http://www.google.com";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);*/


                /*Person person=new Person("Raj","Charlotte", 22.50);
                Intent intent=new Intent(MainActivity.this,secondActivity.class);
                intent.putExtra(PERSON_KEY,person);
                startActivity(intent); Using parcelable */

                alert.show();
                progress.show();
            }
        });
    }
}

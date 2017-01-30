package com.example.rajdeep.dynamiclayouts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        RelativeLayout relativeLayout=new RelativeLayout(this);
        relativeLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        setContentView(relativeLayout);

        TextView textView=new TextView(this);
        textView.setText("Hey!");
        textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        textView.setId(10);
        relativeLayout.addView(textView);

        Button button=new Button(this);
        button.setText("Temp Button");
        RelativeLayout.LayoutParams buttonlayoutparams= new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        buttonlayoutparams.addRule(RelativeLayout.BELOW, textView.getId());
        button.setLayoutParams(buttonlayoutparams);
        relativeLayout.addView(button);

    }
}

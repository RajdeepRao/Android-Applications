package com.example.rajdeep.homework1;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
/*
Assignment: Homework 1
* Name: Rajdeep Rao
* */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText input=(EditText) findViewById(R.id.inputAmt);
        final TextView output=(TextView) findViewById(R.id.outputAmt);
        Button convert=(Button)findViewById(R.id.buttonConvert);
        final RadioGroup inputCurrency=(RadioGroup)findViewById(R.id.inputCurrency);
        final RadioGroup outputCurrency=(RadioGroup)findViewById(R.id.outputCurrency);


        inputCurrency.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if((RadioButton)findViewById(checkedId)!=null);
                RadioButton checked=(RadioButton)findViewById(checkedId);

            }
        });

        outputCurrency.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton checkedOutput=(RadioButton)findViewById(checkedId);
            }
        });


        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String val=input.getText().toString();
                Log.d("Demo", val );


                if(val.matches("")!=true && Double.parseDouble(val)>0)
                {
                    Double inputVal=Double.parseDouble(val);
                    Double answer;
                    if(inputCurrency.getCheckedRadioButtonId()==R.id.INR && outputCurrency.getCheckedRadioButtonId()==R.id.USD) {
                        Log.w("Value", "INR-USD");
                        answer=inputVal/68.14;
                        output.setText(Double.toString(answer));
                    }
                    else if(inputCurrency.getCheckedRadioButtonId()==R.id.INR && outputCurrency.getCheckedRadioButtonId()==R.id.GBP) {
                        Log.w("Value","INR-GBP");
                        answer=inputVal*0.83/68.14;
                        output.setText(Double.toString(answer));
                    }
                    else if(inputCurrency.getCheckedRadioButtonId()==R.id.CAD && outputCurrency.getCheckedRadioButtonId()==R.id.USD) {
                        Log.w("Value","CAD-USD");
                        answer=inputVal/1.32;
                        output.setText(Double.toString(answer));
                    }
                    else if(inputCurrency.getCheckedRadioButtonId()==R.id.CAD && outputCurrency.getCheckedRadioButtonId()==R.id.GBP) {
                        Log.w("Value","CAD-GBP");
                        answer=inputVal*0.83/1.32;
                        output.setText(Double.toString(answer));
                    }
                    else if(inputCurrency.getCheckedRadioButtonId()==R.id.AUD && outputCurrency.getCheckedRadioButtonId()==R.id.USD) {
                        Log.w("Value","AUD-USD");
                        answer=inputVal/1.34;
                        output.setText(Double.toString(answer));
                    }
                    else if(inputCurrency.getCheckedRadioButtonId()==R.id.AUD&& outputCurrency.getCheckedRadioButtonId()==R.id.GBP) {
                        Log.w("Value","AUD-GBP");
                        answer=inputVal*0.83/1.34;
                        output.setText(Double.toString(answer));
                    }
                }
                else
                    Toast.makeText(getApplicationContext(), "Invalid Input", Toast.LENGTH_SHORT).show();




            }
        });
    }

    public void clearFunction(View view){
        Log.d("Demo", "Clear Clicked" );
        final EditText input=(EditText) findViewById(R.id.inputAmt);
        final TextView output=(TextView) findViewById(R.id.outputAmt);
        final RadioGroup inputCurrency=(RadioGroup)findViewById(R.id.inputCurrency);
        final RadioGroup outputCurrency=(RadioGroup)findViewById(R.id.outputCurrency);

        output.setText("");
        input.setText("");
        inputCurrency.clearCheck();
        outputCurrency.clearCheck();

    }
}

package com.example.rajdeep.homework2;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

public class AddMovieActivity extends AppCompatActivity {

    final static String MOVIE_KEY="Movie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setSelection(0);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.genre_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        final EditText name=(EditText) findViewById(R.id.nameEditText);
        final EditText description=(EditText) findViewById(R.id.descriptionValue);
        final EditText year=(EditText) findViewById(R.id.year);
        final EditText imdb=(EditText) findViewById(R.id.imdb);
        final Spinner genre=(Spinner) findViewById(R.id.spinner);
        final SeekBar rating=(SeekBar) findViewById(R.id.seekBar);


        genre.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String spinnerVal= parent.getItemAtPosition(position).toString();
                Log.d("Demo",spinnerVal);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        rating.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int seekbarVal=progress;
                Log.d("Seekbar", Integer.toString(seekbarVal));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        findViewById(R.id.addMovie).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String movieName, movieDescription, movieGenre, movieIMDB,movieYear, movieRating;


                movieName=name.getText().toString();
                movieDescription=description.getText().toString();
                movieGenre=genre.getSelectedItem().toString();
                movieIMDB=imdb.getText().toString();
                movieYear=year.getText().toString();
                movieRating=Integer.toString(rating.getProgress());


                if(movieName.isEmpty() || movieDescription.isEmpty() || movieYear.isEmpty() || movieRating.equalsIgnoreCase("0") || movieGenre.equalsIgnoreCase("Select Genre"))
                {
                    Toast.makeText(AddMovieActivity.this, "Invlaid Input", Toast.LENGTH_LONG).show();
                }
                else{
                    MovieClass m= new MovieClass(movieName, movieDescription, movieGenre, movieIMDB, movieYear, movieRating  );
                    Intent intent=new Intent(AddMovieActivity.this, MainActivity.class);
                    intent.putExtra(MOVIE_KEY,m);
                    Log.d("Intent","Finish");
                    setResult(RESULT_OK, intent);
                    Toast.makeText(AddMovieActivity.this,"Added "+m.getName(),Toast.LENGTH_LONG).show();
                    finish();
                }

            }
        });

    }
}

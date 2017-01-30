package com.example.rajdeep.homework2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

import static com.example.rajdeep.homework2.R.id.Edit;
import static com.example.rajdeep.homework2.R.id.spinner;

public class EditActivity extends AppCompatActivity {
    final static String MOVIE_UPDATE_KEY="Updated Values";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        MovieClass movie=getIntent().getExtras().getParcelable(MainActivity.MOVIE_VALUE);
        final int index=getIntent().getExtras().getInt("Index");
        Toast.makeText(this, movie.toString(),Toast.LENGTH_LONG).show();

        final String name=movie.getName();
        final String description=movie.getDescription();
        final String year=movie.getYear();
        final String imdb=movie.getImdb();
        final String rating=movie.getRating();
        final String genre=movie.getGenre();

        final EditText nameEditText=(EditText)findViewById(R.id.nameEditTextUpdate);
        nameEditText.setText(name);

        final EditText descriptionEditText=(EditText)findViewById(R.id.descriptionValueUpdate);
        descriptionEditText.setText(description);

        final Spinner genreSpinner= (Spinner) findViewById(R.id.spinnerUpdate);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.genre_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genreSpinner.setAdapter(adapter);

        genreSpinner.setSelection(getIndex(genreSpinner, genre));

        final EditText yearEditText=(EditText)findViewById(R.id.yearUpdate);
        yearEditText.setText(year);

        final EditText imdbEditText=(EditText)findViewById(R.id.imdbUpdate);
        imdbEditText.setText(imdb);

        final SeekBar ratingValue= (SeekBar) findViewById(R.id.seekBarUpdate);
        ratingValue.setProgress(Integer.parseInt(rating));

        findViewById(R.id.saveChanges).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nameEditText.getText().toString().isEmpty() || descriptionEditText.getText().toString().isEmpty() || yearEditText.getText().toString().isEmpty() || Integer.toString(ratingValue.getProgress()).equalsIgnoreCase("0") || genreSpinner.getSelectedItem().toString().equalsIgnoreCase("Select Genre"))
                {
                    Toast.makeText(EditActivity.this, "Invlaid Input", Toast.LENGTH_LONG).show();
                }
                else {
                    Intent intent = new Intent(EditActivity.this, MainActivity.class);
                    MovieClass m = new MovieClass(nameEditText.getText().toString(), descriptionEditText.getText().toString(), genreSpinner.getSelectedItem().toString(), imdbEditText.getText().toString(), yearEditText.getText().toString(), Integer.toString(ratingValue.getProgress()));
                    intent.putExtra(MOVIE_UPDATE_KEY, m);
                    intent.putExtra("Index", index);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });


    }
    private int getIndex(Spinner spinner, String myString)
    {
        int index = 0;
        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
                index = i;
                break;
            }
        }
        return index;
    }
}

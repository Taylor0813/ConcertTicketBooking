package com.example.lec3demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    final String TAG = "Concert Demo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editTextNumTix = findViewById(R.id.editTextNumTix);
        Button btnBookConcert = findViewById(R.id.btnBookConcert);
        Spinner spinnerConcertType = findViewById(R.id.spinnerConcertType);

        spinnerConcertType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == 0){
                    Toast.makeText(MainActivity.this,
                            "Selected Rock Band", Toast.LENGTH_SHORT).show();
                } else if (i == 1){
                    Toast.makeText(MainActivity.this,
                            "Selected Jazz Band", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this,
                            "Selected Blues Band", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnBookConcert.setOnClickListener((View view) -> {
                if (editTextNumTix.getText().toString().isEmpty()){
                    Toast.makeText(this,
                            "Number of tickets must be entered!", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        int numTix = Integer.parseInt(editTextNumTix.getText().toString());
                        int index = spinnerConcertType.getSelectedItemPosition();
                        double cost = 0;
                        switch(index){
                            case 0:
                                cost = numTix * 79.99;
                                break;
                            case 1:
                                cost = numTix * 69.99;
                                break;
                            case 2:
                                cost = numTix * 59.99;
                                break;
                        }
                        DecimalFormat df = new DecimalFormat("$#.##");
                        String outputCostTxt = df.format(cost);

                        Toast.makeText(this, outputCostTxt, Toast.LENGTH_SHORT).show();

                        //a bundle is a collection of key value pairs, we can bundle as many things into one object and pass it along
                        Bundle bundle = new Bundle(); //create a bundle object
                        bundle.putInt("NUMTIX",numTix); //NUMTIX is the key, numTix is the value
                        bundle.putString("TYPE", spinnerConcertType.getSelectedItem().toString()); //getSelectedItem() returns an object, we need to convert it to string
                        bundle.putDouble("COST", cost);

                        //create a new intent object
                        Intent myResultsIntent = new Intent(MainActivity.this,
                                ResultActivity.class);
                        myResultsIntent.putExtras(bundle);  //add the bundle data into the intent
                        startActivity(myResultsIntent);  //start the activity

                    } catch (Exception ex){
                        ex.printStackTrace();
                        Log.d(TAG,"Error in parse/number of tickets");
                        Toast.makeText(this,
                                "Number of tickets must be whole number > 0",
                                Toast.LENGTH_SHORT).show();
                    }
                }

        });
    }
}
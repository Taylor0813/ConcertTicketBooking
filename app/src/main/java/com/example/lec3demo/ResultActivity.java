package com.example.lec3demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;

import java.text.DecimalFormat;

public class ResultActivity extends AppCompatActivity {
    final String TAG = "Concert Demo";  //also need to add the TAG here

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        try{
            Bundle bundle = getIntent().getExtras();
            //getIntent() returns the current intent object
            // getExtras() gets the bundle out of the intent
            double costR = bundle.getDouble("COST", 0); //get the information from the bundle
            int numTix = getIntent().getExtras().getInt("NUMTIX", 0); //can also merge into one step
            String concertType = bundle.getString("TYPE", "NOTHING");

            DecimalFormat df = new DecimalFormat("$#.##");
            String outputStr = "Concert Type: " + concertType + "\n"
                    + "Number of Tickets: " + numTix + "\n"
                    + "Total Cost: " + df.format(costR);

            TextView txtViewResults = findViewById(R.id.txtViewResults); //instantiate the textView
            txtViewResults.setText(outputStr);
            txtViewResults.setGravity(Gravity.CENTER); //change the alignment of the text to the center, both vertically and horizontally
            txtViewResults.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL); //combining the vertical and horizontal gravity

        } catch (Exception ex){
            ex.printStackTrace();
            Log.d(TAG, ex.getMessage());
        }
    }
}
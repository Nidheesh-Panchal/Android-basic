package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button roll;
        roll=(Button)findViewById(R.id.roll_button);
        final ImageView ldice=(ImageView) findViewById(R.id.firstdie);
        final ImageView rdice=(ImageView) findViewById(R.id.seconddie);
        final int[] dice={
                R.drawable.dice1,
                R.drawable.dice2,
                R.drawable.dice3,
                R.drawable.dice4,
                R.drawable.dice5,
                R.drawable.dice6,
        };

        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Log.d("Android","Hello");

                Random ran = new Random();
                int num=ran.nextInt(6);
                //Log.d("Android",""+num);
                ldice.setImageResource(dice[num]);
                num=ran.nextInt(6);
                rdice.setImageResource(dice[num]);
            }
        });


    }
}


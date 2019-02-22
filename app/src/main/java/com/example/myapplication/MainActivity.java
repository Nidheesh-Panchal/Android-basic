package com.example.myapplication;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private final int NR_OF_SIMULTANEOUS_SOUNDS = 7;
    private final float LEFT_VOLUME = 1.0f;
    private final float RIGHT_VOLUME = 1.0f;
    private final int NO_LOOP = 0;
    private final int PRIORITY = 0;
    private final float NORMAL_PLAY_RATE = 2.0f;

    SoundPool sounds;

    int C;
    int D;
    int E;
    int F;
    int G;
    int A;
    int B;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            sounds = new SoundPool.Builder()
                    .setMaxStreams(NR_OF_SIMULTANEOUS_SOUNDS)
                    .build();
        }
        else
        {
            // Deprecated way of creating a SoundPool before Android API 21.
            sounds = new SoundPool(NR_OF_SIMULTANEOUS_SOUNDS, AudioManager.STREAM_MUSIC, 0);
        }
        C=sounds.load(getApplicationContext(),R.raw.note1_c,1);
        D=sounds.load(getApplicationContext(),R.raw.note2_d,1);
        E=sounds.load(getApplicationContext(),R.raw.note3_e,1);
        F=sounds.load(getApplicationContext(),R.raw.note4_f,1);
        G=sounds.load(getApplicationContext(),R.raw.note5_g,1);
        A=sounds.load(getApplicationContext(),R.raw.note6_a,1);
        B=sounds.load(getApplicationContext(),R.raw.note7_b,1);
        Spinner instruments=(Spinner) findViewById(R.id.instrument);
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,
                R.layout.support_simple_spinner_dropdown_item,getResources().getStringArray(R.array.instrum));
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        instruments.setAdapter(adapter);
        instruments.setSelection(0);

        instruments.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("item",""+i);
                if(i==0)//xylophone
                {
                    C=sounds.load(getApplicationContext(),R.raw.note1_c,1);
                    D=sounds.load(getApplicationContext(),R.raw.note2_d,1);
                    E=sounds.load(getApplicationContext(),R.raw.note3_e,1);
                    F=sounds.load(getApplicationContext(),R.raw.note4_f,1);
                    G=sounds.load(getApplicationContext(),R.raw.note5_g,1);
                    A=sounds.load(getApplicationContext(),R.raw.note6_a,1);
                    B=sounds.load(getApplicationContext(),R.raw.note7_b,1);
                }
                else if(i==1)//guitar
                {
                    C=sounds.load(getApplicationContext(),R.raw.gc,1);
                    D=sounds.load(getApplicationContext(),R.raw.gd,1);
                    E=sounds.load(getApplicationContext(),R.raw.ge,1);
                    F=sounds.load(getApplicationContext(),R.raw.gf,1);
                    G=sounds.load(getApplicationContext(),R.raw.gg,1);
                    A=sounds.load(getApplicationContext(),R.raw.ga,1);
                    B=sounds.load(getApplicationContext(),R.raw.gb,1);
                }
                else if(i==2)//piano
                {
                    C=sounds.load(getApplicationContext(),R.raw.c1,1);
                    D=sounds.load(getApplicationContext(),R.raw.d1,1);
                    E=sounds.load(getApplicationContext(),R.raw.e1,1);
                    F=sounds.load(getApplicationContext(),R.raw.f1,1);
                    G=sounds.load(getApplicationContext(),R.raw.g1,1);
                    A=sounds.load(getApplicationContext(),R.raw.a1,1);
                    B=sounds.load(getApplicationContext(),R.raw.b1,1);
                }

                else if(i==3)//cowbell
                {
                    C=sounds.load(getApplicationContext(),R.raw.cowc,1);
                    D=sounds.load(getApplicationContext(),R.raw.cowd,1);
                    E=sounds.load(getApplicationContext(),R.raw.cowe,1);
                    F=sounds.load(getApplicationContext(),R.raw.cowf,1);
                    G=sounds.load(getApplicationContext(),R.raw.cowg,1);
                    A=sounds.load(getApplicationContext(),R.raw.cowa,1);
                    B=sounds.load(getApplicationContext(),R.raw.cowb,1);
                }
                else if(i==4)//electric guitar
                {
                    C=sounds.load(getApplicationContext(),R.raw.egc,1);
                    D=sounds.load(getApplicationContext(),R.raw.egd,1);
                    E=sounds.load(getApplicationContext(),R.raw.ege,1);
                    F=sounds.load(getApplicationContext(),R.raw.egf,1);
                    G=sounds.load(getApplicationContext(),R.raw.egg,1);
                    A=sounds.load(getApplicationContext(),R.raw.ega,1);
                    B=sounds.load(getApplicationContext(),R.raw.egb,1);
                }
                else if(i==5)//EDM
                {
                    C=sounds.load(getApplicationContext(),R.raw.flc,1);
                    D=sounds.load(getApplicationContext(),R.raw.fld,1);
                    E=sounds.load(getApplicationContext(),R.raw.fle,1);
                    F=sounds.load(getApplicationContext(),R.raw.flf,1);
                    G=sounds.load(getApplicationContext(),R.raw.flg,1);
                    A=sounds.load(getApplicationContext(),R.raw.fla,1);
                    B=sounds.load(getApplicationContext(),R.raw.flb,1);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void playC(View v) {
       sounds.play(C, LEFT_VOLUME, RIGHT_VOLUME, PRIORITY, NO_LOOP, NORMAL_PLAY_RATE);
    }

    public void playD(View v) {
        sounds.play(D, LEFT_VOLUME, RIGHT_VOLUME, PRIORITY, NO_LOOP, NORMAL_PLAY_RATE);
    }

    public void playE(View v) {
        sounds.play(E, LEFT_VOLUME, RIGHT_VOLUME, PRIORITY, NO_LOOP, NORMAL_PLAY_RATE);
    }

    public void playF(View v) {
        sounds.play(F, LEFT_VOLUME, RIGHT_VOLUME, PRIORITY, NO_LOOP, NORMAL_PLAY_RATE);
    }

    public void playG(View v) {
        sounds.play(G, LEFT_VOLUME, RIGHT_VOLUME, PRIORITY, NO_LOOP, NORMAL_PLAY_RATE);
    }

    public void playA(View v) {
        sounds.play(A, LEFT_VOLUME, RIGHT_VOLUME, PRIORITY, NO_LOOP, NORMAL_PLAY_RATE);
    }

    public void playB(View v) {
        sounds.play(B, LEFT_VOLUME, RIGHT_VOLUME, PRIORITY, NO_LOOP, NORMAL_PLAY_RATE);
    }
}


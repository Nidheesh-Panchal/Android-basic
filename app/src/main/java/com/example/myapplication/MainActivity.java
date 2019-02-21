package com.example.myapplication;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private final int NR_OF_SIMULTANEOUS_SOUNDS = 7;
    private final float LEFT_VOLUME = 1.0f;
    private final float RIGHT_VOLUME = 1.0f;
    private final int NO_LOOP = 0;
    private final int PRIORITY = 0;
    private final float NORMAL_PLAY_RATE = 1.0f;

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
    }

    public void playC(View v) {
       sounds.play(C, LEFT_VOLUME, RIGHT_VOLUME, 0, NO_LOOP, NORMAL_PLAY_RATE);
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


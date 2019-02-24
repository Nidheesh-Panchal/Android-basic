package com.example.myapplication;

import android.content.DialogInterface;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @NonNull
    private qanda[] q=new qanda[]{
            new qanda(R.string.q1,true),
            new qanda(R.string.q2,true),
            new qanda(R.string.q3,true),
            new qanda(R.string.q4,true),
            new qanda(R.string.q5,true),
            new qanda(R.string.q6,false),
            new qanda(R.string.q7,true),
            new qanda(R.string.q8,false),
            new qanda(R.string.q9,true),
            new qanda(R.string.q10,true),
            new qanda(R.string.q11,false),
            new qanda(R.string.q12,false),
            new qanda(R.string.q13,true),
            new qanda(R.string.q14,true),
            new qanda(R.string.q15,true),
            new qanda(R.string.q16,false),
            new qanda(R.string.q17,false),
            new qanda(R.string.q18,true),
            new qanda(R.string.q19,false),
            new qanda(R.string.q20,true)
    };

    int index;
    int sc;
    TextView question;
    TextView score;
    ProgressBar mProgressBar;
    Button true_button;
    Button false_button;
    Toast mToast;
    int progress_inc=(int) Math.ceil(100.0/q.length);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        question=findViewById(R.id.q);
        score=findViewById(R.id.score_text);
        mProgressBar=findViewById(R.id.progressBar);
        true_button=findViewById(R.id.button);
        false_button=findViewById(R.id.button2);

        if(savedInstanceState!=null)
        {
            index=savedInstanceState.getInt("index");
            sc=savedInstanceState.getInt("score");
        }
        else
        {
            index=0;
            sc=0;
        }

        question.setText(q[index].getQid());

        true_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check(true);
                update();
            }
        });
        false_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check(false);
                update();
            }
        });
    }

    void check(boolean answer)
    {
        if(mToast!=null)
        {
            mToast.cancel();
        }
        if(q[index].isAns()==answer)
        {
            sc++;
            score.setText("Score :"+ sc + "/" + q.length);
            mToast=Toast.makeText(getApplicationContext(),R.string.correct, Toast.LENGTH_SHORT);
        }
        else
        {
            mToast=Toast.makeText(getApplicationContext(),R.string.wrong, Toast.LENGTH_SHORT);
        }
        mToast.show();
    }
    void update()
    {
        index=(index+1)%q.length;
        if(index==0)
        {
            AlertDialog.Builder alert=new AlertDialog.Builder(this);
            alert.setTitle("Game Over");
            alert.setMessage("So did you prove yourself as a loser or not?");
            alert.setCancelable(false);
            alert.setPositiveButton("Yes, I did", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            alert.show();
        }
        question.setText(q[index].getQid());
        mProgressBar.incrementProgressBy(progress_inc);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("index",index);
        outState.putInt("score",sc);
    }
}


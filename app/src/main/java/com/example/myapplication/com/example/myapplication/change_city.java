package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.textclassifier.TextSelection;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class change_city extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_change_city);

		Button back=findViewById(R.id.back_button);
		back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				finish();
			}
		});
		final EditText city_text=findViewById(R.id.city_text);
		city_text.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				city_text.setText("");
			}
		});
		city_text.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
				String newcity=city_text.getText().toString();
				Intent newcity_intent=new Intent(change_city.this,MainActivity.class);
				newcity_intent.putExtra("city",newcity);
				startActivity(newcity_intent);
				return false;
			}
		});
	}
}

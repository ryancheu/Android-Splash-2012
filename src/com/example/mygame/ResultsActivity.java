package com.example.mygame;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultsActivity extends Activity{
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        TextView TV = new TextView(this);        
        TV.setText("You Lose!");
        setContentView(TV);
    }
	
}

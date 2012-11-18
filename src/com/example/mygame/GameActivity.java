package com.example.mygame;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;

public class GameActivity extends Activity {

	MyGameView mSurfaceView;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        mSurfaceView = new MyGameView(this);
        FrameLayout fl = new FrameLayout(this);  
        fl.setLayoutParams(new LayoutParams());  
        fl.addView(mSurfaceView);        
        
        setContentView(fl);
        
    }
    
}

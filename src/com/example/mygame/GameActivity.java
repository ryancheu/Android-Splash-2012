package com.example.mygame;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager.LayoutParams;
import android.widget.Button;
import android.widget.FrameLayout;

public class GameActivity extends Activity {

	MySurfaceView mSurfaceView;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        mSurfaceView = new MySurfaceView(this);
        FrameLayout fl = new FrameLayout(this);  
        fl.setLayoutParams(new LayoutParams());  
        fl.addView(mSurfaceView);

        setContentView(fl);
    }
    
}

package com.example.mygame;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class MyGameView extends View
{
	Paint mPaint;
	private Rect mRect;
	Bitmap mPlayerBitmap;
	Bitmap mEnemyBitmap;
	private final int RECT_WIDTH = 100;
	private final int RECT_HEIGHT = 100;
	private final int PLAYER_START_X = 0;
	private final int PLAYER_START_Y = 400;
	private final Rect mPlayerImageSize = new Rect(0,0,256,256);
	private final Rect mEnemyImageSize = new Rect(0,0,256,256);
	
	private final RectF mImageDrawSize = new RectF(0,0,RECT_WIDTH,RECT_HEIGHT);
	
	final static int FINGER_UP = -1;
	
	public float mPointerX = FINGER_UP;
	public float mPointerY = FINGER_UP;
	
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	
	private Player player;
	
	private long mPrevTimeMilli;
	
	public MyGameView(Context context) {
		super(context);	
		mPaint = new Paint();
		mPaint.setARGB(255,0,255,0);
		mPlayerBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.player);
		mEnemyBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.enemy);
		
		
		player = new Player(PLAYER_START_X,PLAYER_START_Y,mPlayerBitmap,mPlayerImageSize,mImageDrawSize, 50);
		
		enemies.add(new Enemy(10, 10, 100, 100, mEnemyBitmap, mEnemyImageSize,mImageDrawSize, 25));
		
		mPrevTimeMilli = System.currentTimeMillis();
		invalidate();
	}
	@Override
	public void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		player.draw(canvas, mPaint);
		for(Enemy e : enemies )
		{
	        e.draw(canvas, mPaint);
		}
		update();
	}
	private void update()
	{
		long curTimeMillis = System.currentTimeMillis();
		
		float timeStep = (curTimeMillis-mPrevTimeMilli)/1000f; 
		
		player.update(mPointerX,mPointerY,timeStep);
		
		for(Enemy e : enemies)
		{
			e.update(timeStep);
		}

		mPrevTimeMilli = curTimeMillis;
		invalidate();				
	}
	public boolean onTouchEvent(MotionEvent event) 
    {
		switch(event.getAction())
		{
		case MotionEvent.ACTION_DOWN:
		case MotionEvent.ACTION_MOVE:
			mPointerX = event.getX();
			mPointerY = event.getY();
			return true;
		case MotionEvent.ACTION_UP:	
			mPointerX = FINGER_UP;
			mPointerY = FINGER_UP;
			return true;
		default:
			return true;
		}
    }
	
	
}

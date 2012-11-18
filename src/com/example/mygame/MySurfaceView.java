package com.example.mygame;

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

public class MySurfaceView extends View
{
	Paint mPaint;
	private Rect mRect;
	Bitmap mPlayerBitmap;
	private final int RECT_WIDTH = 100;
	private final int RECT_HEIGHT = 100;
	private final int RECT_START_X = 0;
	private final int RECT_START_Y = 400;
	private final Rect mTestImageSize = new Rect(0,0,256,256);
	
	private final int FINGER_UP = -1;
	
	private float mPointerX = FINGER_UP;
	private float mPointerY = FINGER_UP;
	
	
	public MySurfaceView(Context context) {
		super(context);	
		mPaint = new Paint();
		mPaint.setARGB(255,0,255,0);
		mPlayerBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.player);		
		mRect = new Rect(RECT_START_X,RECT_START_Y, RECT_START_X + RECT_WIDTH, RECT_START_Y + RECT_HEIGHT);
		Log.v("Test","surface view created");
		invalidate();
	}
	@Override
	public void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		Log.v("Test","Calling On Draw");
        canvas.drawBitmap(mPlayerBitmap, mTestImageSize, mRect, null);
		update();
	}
	private void update()
	{
		int xDirection = 0;
		int yDirection = 0;
		if (mPointerX != FINGER_UP && mPointerY != FINGER_UP)
		{
			if ( mRect.centerX() > mPointerX )  { xDirection = -1; }
			else if ( mRect.centerX() < mPointerX ) { xDirection = 1; }
			if ( mRect.centerY() > mPointerY ) { yDirection = -1; }
			else if ( mRect.centerY() < mPointerY ) { yDirection =1; }
			
			mRect.left += xDirection;
			mRect.right += xDirection;
			
			mRect.top += yDirection;
			mRect.bottom += yDirection;
		}
		
		Log.v("TEST APP","x: " + mRect.left);
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

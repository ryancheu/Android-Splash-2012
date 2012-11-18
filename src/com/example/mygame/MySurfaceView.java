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
import android.view.View;

public class MySurfaceView extends View
{
	Paint mPaint;
	private Rect mRect;
	Bitmap mPlayerBitmap;
	private final int RECT_WIDTH = 200;
	private final int RECT_HEIGHT = 200;
	private final int RECT_START_X = 0;
	private final int RECT_START_Y = 400;
	private Rect mTestImageSize = new Rect(0,0,96,96);
	
	
	public MySurfaceView(Context context) {
		super(context);	
		mPaint = new Paint();
		mPaint.setARGB(255,0,255,0);
		mPlayerBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);		
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
		mRect.left++;
		mRect.right++;
		if ( mRect.left >= this.getWidth())
		{
			mRect.right = 0;
			mRect.left = -1*RECT_WIDTH;			
		}
		
		Log.v("TEST APP","x: " + mRect.left);
		invalidate();		
	}
}

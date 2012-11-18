package com.example.mygame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

public class Player {
	
	RectF mLocation;
	Rect mSize;
	
	Bitmap mBitmap;
	
	float mVelocity;
	
	public Player(int startX, int startY, Bitmap bitmap, Rect size, RectF drawSize, float velocity)
	{
		mSize = new Rect(size);
		mLocation = new RectF(drawSize);
		mBitmap = bitmap;		
		mVelocity = velocity;
		
		mLocation.left += startX;
		mLocation.right += startX;
		mLocation.top += startY;
		mLocation.bottom += startY;
		
	}
	public void draw(Canvas canvas, Paint paint)
	{
		canvas.drawBitmap(mBitmap, mSize, mLocation, null);
	}
	
	public RectF getLocationRect()
	{
		return mLocation;
	}
	
	public void update(float pointerX, float pointerY, float timeStep)
	{
		int xDirection = 0;
		int yDirection = 0;
		if (pointerX != MyGameView.FINGER_UP && pointerY != MyGameView.FINGER_UP)
		{
			if ( mLocation.centerX() > pointerX )  { xDirection = -1; }
			else if ( mLocation.centerX() < pointerX ) { xDirection = 1; }
			if ( mLocation.centerY() > pointerY ) { yDirection = -1; }
			else if ( mLocation.centerY() < pointerY ) { yDirection =1; }
			
			mLocation.left += xDirection*mVelocity*timeStep;
			mLocation.right += xDirection*mVelocity*timeStep;
			
			mLocation.top += yDirection*mVelocity*timeStep;
			mLocation.bottom += yDirection*mVelocity*timeStep;
		}
	}
}

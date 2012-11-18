package com.example.mygame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;

public class Enemy {
	
	private RectF mLocation;
	private Rect mSize;
	private int mStartX;
	private int mStartY;
	private int mEndX;
	private int mEndY;
		
	private int mDirectionX;
	private int mDirectionY;
	
	private float mVelocity;
	
	private Bitmap mBitmap;
	
	public Enemy(int startX, int startY, int endX, int endY, Bitmap bitmap, Rect size, RectF drawSize, float velocity)
	{
		mSize = new Rect(size);
		mLocation = new RectF(drawSize);
		mStartX = startX;
		mStartY = startY;
		mEndX = endX;
		mEndY = endY;
		mBitmap = bitmap;
		mVelocity = velocity;
		
		mLocation.left += mStartX;
		mLocation.right += mStartX;
		mLocation.top += mStartY;
		mLocation.bottom += mStartY;
		
		mDirectionX = (mEndX < mStartX) ? -1 : 1;
		mDirectionY = (mEndY < mStartY) ? -1 : 1;
	}
	public void update(float timeStep)
	{
		float slope = ((float)mEndY - (float)mStartY)/((float)mEndX - (float)mStartX);
		
		float changeY =  slope*timeStep*mVelocity*mDirectionY;
		float changeX = timeStep*mVelocity*mDirectionX; 
		Log.v("test","changeX: " + changeX);
		
		mLocation.top  += changeY;
		mLocation.bottom += changeY;
		
		mLocation.left += changeX;
		mLocation.right += changeX;
		
		if ( (changeY < 0 && mLocation.top < Math.min(mEndY,mStartY)) || (changeY > 0 && mLocation.top > Math.max(mEndY,mStartY) ) )
		{			
			mDirectionY *= -1;
		}
		if ( (changeX < 0 && mLocation.left < Math.min(mEndX,mStartX)) || (changeX > 0 && mLocation.left > Math.max(mEndX,mStartX) ) )
		{
			mDirectionX *= -1;
		}		
	}
	
	public void draw(Canvas canvas, Paint paint )
	{
		canvas.drawBitmap(mBitmap, mSize, mLocation, null);
	}
	public boolean isColliding( RectF other )
	{
		return mLocation.intersects(other.left, other.top, other.right, other.bottom);
	}
	public RectF getLocationRect()
	{
		return mLocation;
	}

}

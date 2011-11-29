package com.ploukitroid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

public class PloukitroidView extends View{
	Drawable bg;
	Canvas canvas;
	LayoutParams lp;
	Ship ship;

	public PloukitroidView(Context context, Ship ship) {
		super(context);
		// TODO Auto-generated constructor stub
		this.ship = ship;
		this.setBackgroundResource(R.drawable.bg);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		Paint paint = new Paint();
		paint.setFilterBitmap(true);
		Bitmap bShip = BitmapFactory.decodeResource(this.getResources(), ship.getPath());
		Rect dest = ship.getRect();
		canvas.drawBitmap(bShip, null, dest, paint);
	}

}

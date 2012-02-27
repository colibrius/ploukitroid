package com.ploukitroid;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

// View
public class PloukitroidView extends View {
	Drawable bg;
	Canvas canvas;
	LayoutParams lp;
	Ship ship;
	ArrayList<Ship> sEnemies;
	Bitmap bShip;
	Bitmap bEnemy;
	Bitmap bBg;
	TextView tScore;

	public PloukitroidView(Context context, Ship ship, ArrayList<Ship> sEnemies) {
		super(context);
		// TODO Auto-generated constructor stub
		this.ship = ship;
		bShip = BitmapFactory.decodeResource(this.getResources(),
				ship.getPath());
		bEnemy = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.bad);
		this.sEnemies = sEnemies;
		bBg = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.bg);
		tScore = new TextView(context);
		tScore.setText("" + 0);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		Paint paint = new Paint();
		paint.setFilterBitmap(true);
		Rect dest = new Rect(0, 0, this.getWidth(), this.getHeight());
		//canvas.drawBitmap(bBg, null, dest, paint);
		dest = ship.getRect();
		canvas.drawBitmap(bShip, null, dest, paint);
		for (int i = 0; i < sEnemies.size(); ++i) {
			dest = sEnemies.get(i).getRect();
			canvas.drawBitmap(bEnemy, null, dest, paint);
		}
		paint.setColor(Color.RED);
		paint.setTextSize(66);
		canvas.drawText((String) tScore.getText(), (float)(this.getWidth()*0.9), (float)(this.getHeight()*0.1), paint);
	}
	public void setScore(int nScore){
		tScore.setText(Integer.toString(nScore));
	}

}

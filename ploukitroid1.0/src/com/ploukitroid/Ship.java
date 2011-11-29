package com.ploukitroid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.shapes.RectShape;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

public class Ship extends RectShape implements View.OnTouchListener {
	private int path;
	private Rect rectangle;
	private int posX, posY, width, heigth, oldX, oldY, screenWidth,
			screenHeigth;
	private boolean isTouching;
	private View pView;

	public Ship(View pView, Context context, int type) {
		if (type == CONSTANT.SHIP_PLAYER)
			path = R.drawable.ic_launcher;
		else if (type == CONSTANT.SHIP_ENEMY)
			path = R.drawable.enemy;
		posX = 100;
		posY = 100;
		oldX = posX;
		oldY = posY;

		Bitmap bShip = BitmapFactory.decodeResource(context.getResources(),
				path);
		width = bShip.getWidth();
		heigth = bShip.getHeight();
		rectangle = new Rect(posX - width / 2, posY - heigth / 2, posX + width
				/ 2, posY + heigth / 2);
		isTouching = false;
		this.pView = pView;

		Display display = ((WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		screenHeigth = display.getHeight();
		screenWidth = display.getWidth();
	}

	public int getPath() {
		return path;
	}

	public Rect getRect() {
		return rectangle;
	}

	public void setPosition(int x, int y) {
		rectangle.set(x - width / 2, y - heigth / 2, x + width / 2, y
				+ heigth / 2);
	}
	
	public void setPosition(int x, int y, MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN)
			if (x >= rectangle.left && x <= rectangle.right)
				if (y >= rectangle.top && y <= rectangle.bottom) {
					isTouching = true;
					oldX = x;
					oldY = y;
				} else if (event.getAction() == (MotionEvent.ACTION_UP))
					isTouching = false;

		// To avoid "teleport" when multi-touching
		if (Math.abs(x - oldX) > 199 || Math.abs(y - oldY) > 199)
			isTouching = false;
		if (isTouching) {
			if (x < width / 2)
				x = width / 2;
			else if (x > screenWidth / 4)
				x = screenWidth / 4;
			if (y < heigth / 2)
				y = heigth / 2;
			else if (y > screenHeigth - heigth / 2)
				y = screenHeigth - heigth / 2;
			rectangle.set(x - width / 2, y - heigth / 2, x + width / 2, y
					+ heigth / 2);
		}
		oldX = x;
		oldY = y;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		int x = (int) event.getX();
		int y = (int) event.getY();

		this.setPosition(x, y, event);
		v.invalidate();
		return true;
	}

}

package com.ploukitroid;

import java.util.Random;

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
		Display display = ((WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		screenHeigth = display.getHeight();
		screenWidth = display.getWidth();

		if (type == CONSTANT.SHIP_PLAYER) {
			path = R.drawable.good;
			posX = 100;
			posY = screenHeigth / 2;
		} else if (type == CONSTANT.SHIP_ENEMY) {
			path = R.drawable.bad;
			posX = screenWidth;
			Random rand = new Random();
			int temp = screenHeigth / 20;
			posY = (int) (temp + temp * rand.nextInt(18));
		}
		Bitmap bShip = BitmapFactory.decodeResource(context.getResources(),
				path);
		width = bShip.getWidth();
		heigth = bShip.getHeight();

		oldX = posX;
		oldY = posY;

		rectangle = new Rect(posX - width / 2, posY - heigth / 2, posX + width
				/ 2, posY + heigth / 2);

		isTouching = false;
		this.pView = pView;
	}

	public Ship(View pView, Context context, int type, int lastY) {
		Display display = ((WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		screenHeigth = display.getHeight();
		screenWidth = display.getWidth();

		if (type == CONSTANT.SHIP_PLAYER) {
			path = R.drawable.good;
			posX = 100;
			posY = screenHeigth / 2;
		} else if (type == CONSTANT.SHIP_ENEMY) {
			path = R.drawable.bad;
			posX = screenWidth;
			Random rand = new Random();
			int temp = screenHeigth / 20 + screenHeigth / 20 * rand.nextInt(19);
			if (temp == lastY) {
				if (temp == screenHeigth / 20)
					temp = temp + temp;
				else if (temp == screenHeigth * 0.95)
					temp = (int) (screenHeigth * 0.9);
				else {
					int rand2 = rand.nextInt(2);
					if (rand2 == 0)
						temp = temp - screenHeigth / 20;
					else
						temp = temp + screenHeigth / 20;
				}
			}
			posY = temp;
		}
		Bitmap bShip = BitmapFactory.decodeResource(context.getResources(),
				path);
		width = bShip.getWidth();
		heigth = bShip.getHeight();

		oldX = posX;
		oldY = posY;

		rectangle = new Rect(posX - width / 2, posY - heigth / 2, posX + width
				/ 2, posY + heigth / 2);

		isTouching = false;
		this.pView = pView;
	}

	public int getPath() {
		return path;
	}

	public Rect getRect() {
		return rectangle;
	}

	public int getX() {
		return posX;
	}

	public int getY() {
		return posY;
	}
	
	public int getShipWidth(){
		return width;
	}
	
	public int getShipHeigth(){
		return heigth;
	}

	public void setPosition(int x, int y) {
		posX = x;
		posY = y;
		rectangle.set(x - width / 2, y - heigth / 2, x + width / 2, y + heigth/ 2);
	}

	public void setPosition(int x, int y, MotionEvent event) {
		posX = x;
		posY = y;
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
		return true;
	}
}

package com.plokitriod;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

public class test extends View implements View.OnTouchListener {
	private Rect rectangle;
	private Rect ennemy;
	private boolean isTouching;
	private int oldX, oldY;
	public boolean isTouched;

	// private Button touch, gyro;
	// private boolean touchOrGyro; // touch = true and gyro = false

	// private final SensorManager mSensorManager;
	// private final Sensor mGyro;

	public test(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		isTouching = false;
		isTouched = false;
		this.setBackgroundColor(Color.GRAY);
		Display display = ((WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		int hOver2 = display.getHeight() / 2;
		int wOver2 = display.getWidth() / 2;
		rectangle = new Rect(wOver2 - 50, hOver2 / 5, wOver2 + 50, hOver2 / 5
				+ hOver2 / 10);
		ennemy = new Rect(wOver2 - 50, hOver2, wOver2 + 50, hOver2 / 10);

		// touch = new Button(context);
		// gyro = new Button(context);
		//
		// // Sensor options
		// mSensorManager =
		// (SensorManager)context.getSystemService(Context.SENSOR_SERVICE);
		// //mGyro = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
	}

	public Rect getRectangle() {
		return rectangle;
	}

	public void setRectangle(Rect rectangle) {
		this.rectangle = rectangle;
	}

	public Rect getEnnemy() {
		return ennemy;
	}

	public void setEnnemy(Rect ennemy) {
		this.ennemy = ennemy;
	}

	protected void onDraw(Canvas canvas) {
		Paint rectAttributs = new Paint();
		if (isTouched) {
			rectAttributs.setColor(Color.RED);
		} else
			rectAttributs.setColor(Color.BLUE);
		
		canvas.drawRect(rectangle, rectAttributs);
		rectAttributs.setColor(Color.RED);
		canvas.drawRect(ennemy, rectAttributs);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		int x = (int) event.getX();
		int y = (int) event.getY();

		if (event.getAction() == (MotionEvent.ACTION_DOWN))
			if (x >= rectangle.left && x <= rectangle.right)
				if (y >= rectangle.top && y <= rectangle.bottom) {
					isTouching = true;
					oldX = x;
					oldY = y;
				} else if (event.getAction() == (MotionEvent.ACTION_UP))
					isTouching = false;
		// To avoid "teleport" when multitouching, max is a BITCH !!
		if (Math.abs(x - oldX) > 199 || Math.abs(y - oldY) > 199)
			isTouching = false;
		if (isTouching) {
			if (y > 250)
				y = 250;
			rectangle.set(x - 50, y - 50, x + 50, y + 50);
			this.invalidate();
		}
		oldX = x;
		oldY = y;
		return true;
	}

	// @Override
	// public void onAccuracyChanged(Sensor sensor, int accuracy) {
	// // TODO Auto-generated method stub
	//
	// }
	//
	// @Override
	// public void onSensorChanged(SensorEvent event) {
	// // TODO Auto-generated method stub
	// float axisX = event.values[0];
	//
	// if (axisX > 0) {
	// rectangle.set(rectangle.left - 50, rectangle.top,
	// rectangle.right - 50, rectangle.bottom);
	// invalidate();
	// }
	//
	// }

}

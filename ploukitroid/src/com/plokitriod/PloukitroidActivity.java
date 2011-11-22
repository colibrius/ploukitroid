package com.plokitriod;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

public class PloukitroidActivity extends Activity implements
		SensorEventListener {
	/** Called when the activity is first created. */
	test layoutRect;
	private SensorManager mSensorManager;
	private Sensor mGyro;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		layoutRect = new test(this);

		setContentView(layoutRect);
		layoutRect.setOnTouchListener(layoutRect);
		new Thread(new RefreshRunner(layoutRect)).start();
		// Sensor options
		mSensorManager = (SensorManager) this
				.getSystemService(Context.SENSOR_SERVICE);
		mGyro = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
	}

	protected void onResume() {
		super.onResume();
		mSensorManager.registerListener(this,
				mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_FASTEST);
	}

	protected void onStop() {
		mSensorManager.unregisterListener(this);
		super.onStop();
	}

	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub

	}

	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		float axisX = event.values[0];
		float axisY = event.values[1];

		if (axisX > 1) {
			layoutRect.getRectangle().set(layoutRect.getRectangle().left - 1,
					layoutRect.getRectangle().top,
					layoutRect.getRectangle().right - 1,
					layoutRect.getRectangle().bottom);
			layoutRect.invalidate();
		} else if (axisX<-1) {
			layoutRect.getRectangle().set(layoutRect.getRectangle().left + 1,
					layoutRect.getRectangle().top,
					layoutRect.getRectangle().right + 1,
					layoutRect.getRectangle().bottom);
			layoutRect.invalidate();

		}

	}
}
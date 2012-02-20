package com.ploukitroid;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnCreateContextMenuListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

// Model
public class PloukitroidActivity extends Activity implements View.OnTouchListener, OnCreateContextMenuListener
{
	/** Called when the activity is first created. */
	PloukitroidView pView;
	Button bStart, bStop;
	Ship ship;
	ArrayList<Ship> sEnemies;
	boolean bMenu;
	MainThread main;
	private SensorManager mSensorManager;
	Captor c;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.main);

		bStart = (Button) findViewById(R.id.bStart);
		bStart.setText("Start");
		bStart.setOnTouchListener(this);
		bStop = (Button) findViewById(R.id.bStop);
		bStop.setText("Stop");
		bStop.setOnTouchListener(this);
		bMenu = true;

		// Sensor options
		mSensorManager = (SensorManager) this.getSystemService(Context.SENSOR_SERVICE);
		Sensor mGyro = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event)
	{
		switch (v.getId())
		{
		// Start application :
		case R.id.bStart:
			if (event.getAction() == MotionEvent.ACTION_UP)
			{
				ship = new Ship(pView, this, CONSTANT.SHIP_PLAYER);
				sEnemies = new ArrayList<Ship>();
				// sEnemies.add(new Ship(pView, this, CONSTANT.SHIP_ENEMY));
				pView = new PloukitroidView(getApplicationContext(), ship, sEnemies);
				this.setContentView(pView);
				pView.setOnTouchListener(ship);

				c = new Captor(ship, mSensorManager, pView);
				mSensorManager.registerListener(c, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_FASTEST);
				
//				c.onCreate(savedInstanceState);
				new Thread(new MainThread(ship, sEnemies, pView, this, ((WindowManager) this.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getWidth() / 3000.0)).start();
			}
			break;

		// Press stop button
		case R.id.bStop:
			if (event.getAction() == MotionEvent.ACTION_UP)
				this.finish();
			break;
		}
		return false;
	}

	@Override
	protected void onSaveInstanceState(Bundle outState)
	{
		super.onSaveInstanceState(outState);
	}

	@Override
	protected void onPause()
	{
		super.onPause();
	}

	protected void onResume()
	{
		super.onResume();
		mSensorManager.registerListener(c, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_FASTEST);
	}

	protected void onStop()
	{
		mSensorManager.unregisterListener(c);
		super.onStop();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if ((keyCode == KeyEvent.KEYCODE_BACK))
		{
			this.setContentView(R.layout.main);

			bStart = (Button) findViewById(R.id.bStart);
			bStart.setText("Start");
			bStart.setOnTouchListener(this);
			bStop = (Button) findViewById(R.id.bStop);
			bStop.setText("Stop");
			bStop.setOnTouchListener(this);
			bMenu = true;
			return true;
		}
		else
			return super.onKeyDown(keyCode, event);
	}
}
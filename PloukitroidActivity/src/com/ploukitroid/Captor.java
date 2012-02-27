package com.ploukitroid;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

public class Captor implements SensorEventListener
{

	private float x, y, z;
	private SensorManager mSensorManager;
	private Ship ship;
	private PloukitroidView pView;
	private Double basicInclination;

	public Captor(Ship ship, SensorManager mSensorManager, PloukitroidView pView)
	{
		this.ship = ship;
		this.mSensorManager = mSensorManager;
		this.pView = pView;
	}

	public void onAccuracyChanged(Sensor sensor, int accuracy)
	{
		// TODO Auto-generated method stub

	}

	public void onSensorChanged(SensorEvent event)
	{
		x = event.values[0];
		y = event.values[1];
		if (basicInclination == null)
		{
			basicInclination = (double)y;
		}

//		if (y > basicInclination+CONSTANT.SENSOR_SENSIBILITY_LIMIT)
//		{
//			if (ship.getRect().bottom > pView.getHeight())
//				ship.setPosition(ship.getX(), pView.getHeight() - ship.getShipHeigth() / 2);
//			else
//				ship.setPosition(ship.getX(), (int)(ship.getY() + 2*(y-basicInclination)*(y-basicInclination)));
//		}
//		else if (y < basicInclination-CONSTANT.SENSOR_SENSIBILITY_LIMIT)
//		{
//			if (ship.getRect().top < 0)
//				ship.setPosition(ship.getX(), 0 + ship.getShipHeigth() / 2);
//			else
//				ship.setPosition(ship.getX(), (int)(ship.getY() - 2*(y-basicInclination)*(y-basicInclination)));
//		}

	}

}

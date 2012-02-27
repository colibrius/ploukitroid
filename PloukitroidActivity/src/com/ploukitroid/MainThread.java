package com.ploukitroid;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.graphics.Rect;

public class MainThread implements Runnable
{
	private Ship ship;
	PloukitroidView pView;
	int isStarting;
	int speed;
	int score;
	int lastEnemY;
	private int nbEnemies;
	private ArrayList<Ship> sEnemies;
	private Thread enemiesThread;
	Context context;
	Ship sEnemy;
	double eSpeed;
	Random r;
	Timer t;
	TimerTask timerTask;
	Boolean youCanSend;

	public MainThread(Ship ship, ArrayList<Ship> sEnnemies, PloukitroidView pView, Context context, double eSpeed)
	{
		this.ship = ship;
		this.pView = pView;
		isStarting = 0;
		speed = 1;
		nbEnemies = 1;
		this.context = context;
		this.sEnemies = sEnnemies;
		this.eSpeed = eSpeed;
		r = new Random();
		score = 0;
		t = new Timer();
		youCanSend = true;
	}

	@Override
	public void run()
	{
		while (!Thread.currentThread().isInterrupted())
		{
			if (r.nextDouble() > 0.90 && nbEnemies < 20)
				nbEnemies++;
			else if (r.nextDouble() < 0.01)
				if (nbEnemies > 1)
					nbEnemies--;
			if (sEnemies.size() <= nbEnemies)
			{
				if (sEnemies.isEmpty())
				{
					sEnemies.add(new Ship(pView, context, CONSTANT.SHIP_ENEMY));
					enemiesThread = new Thread(new EnemyThread(sEnemies, pView, eSpeed));
					enemiesThread.start();
				}
				else if (sEnemies.get(sEnemies.size() - 1).getX() < pView.getWidth() * 0.95)
				{
					if (youCanSend)
					{
						sEnemies.add(new Ship(pView, context, CONSTANT.SHIP_ENEMY, sEnemies.get(sEnemies.size() - 1).getY()));
						t.schedule(new EnnemyPop(), CONSTANT.TIME_BETWEEN_ENNEMIES); // Launch
																						// timer
						youCanSend = false;
					}
				}
			}
			for (int i = 0; i < sEnemies.size() - 1; ++i)
			{
				if (ship.getKillAllTheFuckingEnemies())
				{
					sEnemies.remove(i);
					if (sEnemies.isEmpty())
						ship.setKillAllTheFuckingEnemies(false);
				}
				if (sEnemies.get(i).getRect().right <= 0)
				{
					score++;
					pView.setScore(score);
					sEnemies.remove(i);
				}
				if (Rect.intersects(sEnemies.get(i).getRect(), ship.getRect()))
				{
					score = score - 10;
					if (score < 0)
						score = 0;
					pView.setScore(score);
					sEnemies.remove(i);
				}
			}
			if (sEnemies.isEmpty())
			{
				enemiesThread.interrupt();
			}
			try
			{
				Thread.sleep(CONSTANT.WAIT_TIME);
				pView.postInvalidate();
			}
			catch (InterruptedException e)
			{
				return;
			}
		}

	}

	class EnnemyPop extends TimerTask
	{
		@Override
		public void run()
		{
			youCanSend = true;
		}

	}
}

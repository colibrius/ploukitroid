package com.ploukitroid;

import java.util.ArrayList;

public class EnemyThread implements Runnable
{
	ArrayList<Ship> sEnemy;
	PloukitroidView pView;
	double speed;

	public EnemyThread(ArrayList<Ship> sEnemy, PloukitroidView pView, double eSpeed)
	{
		this.sEnemy = sEnemy;
		this.pView = pView;
		this.speed = eSpeed;
	}

	@Override
	public void run()
	{
		while (!Thread.currentThread().isInterrupted())
		{
			if (!sEnemy.isEmpty())
			{
				for (int i = 0; i < sEnemy.size(); ++i)
				{
					if (sEnemy.get(i).getRect().right > 0)
					{
						sEnemy.get(i).setPosition((int) (sEnemy.get(i).getX() - speed * CONSTANT.WAIT_TIME), (int) sEnemy.get(i).getY());
					}
				}
				try
				{
					Thread.sleep(CONSTANT.WAIT_TIME);
				}
				catch (InterruptedException e)
				{
					return;
				}
			}
		}
	}
}

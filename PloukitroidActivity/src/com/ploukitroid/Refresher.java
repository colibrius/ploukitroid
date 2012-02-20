package com.ploukitroid;

import android.view.View;

public class Refresher extends Thread {
	View v;

	public Refresher(View v) {
		// TODO Auto-generated constructor stub
		this.v = v;
	}

	public void run() {
		v.invalidate();
		try {
			Thread.sleep(40);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			// Thread.currentThread().interrupt();
		}
	}
}

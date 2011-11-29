package com.ploukitroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class PloukitroidActivity extends Activity implements
		View.OnTouchListener, OnCreateContextMenuListener {
	/** Called when the activity is first created. */
	PloukitroidView pView;
	Button bStart;
	Button bStop;
	Ship ship;
	boolean bMenu;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.setContentView(R.layout.main);

		bStart = (Button) findViewById(R.id.bStart);
		bStart.setText("Start");
		bStart.setOnTouchListener(this);
		bStop = (Button) findViewById(R.id.bStop);
		bStop.setText("Stop");
		bStop.setOnTouchListener(this);
		bMenu = true;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch (v.getId()) {
		// Start application :
		case R.id.bStart:
			ship = new Ship(pView, this);
			pView = new PloukitroidView(getApplicationContext(), ship);

			this.setContentView(pView);
			pView.setOnTouchListener(ship);
			break;

		// Press stop button
		case R.id.bStop:
			this.finish();
			break;
		}
		return false;

	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK)) {
			this.setContentView(R.layout.main);
		}
		return super.onKeyDown(keyCode, event);
	}

}
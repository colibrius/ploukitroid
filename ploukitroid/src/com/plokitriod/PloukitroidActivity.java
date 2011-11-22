package com.plokitriod;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;

public class PloukitroidActivity extends Activity {
	/** Called when the activity is first created. */
	test layoutRect;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		layoutRect = new test(this);

		setContentView(layoutRect);
		layoutRect.setOnTouchListener(layoutRect);
		new Thread(new RefreshRunner(layoutRect)).start();
	}
}
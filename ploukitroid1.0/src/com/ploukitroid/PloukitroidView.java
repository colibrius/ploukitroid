package com.ploukitroid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;

public class PloukitroidView extends View {
	Drawable bg;
	Canvas canvas;
	public PloukitroidView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		
		this.setBackgroundResource(R.drawable.ic_launcher);
	}

}

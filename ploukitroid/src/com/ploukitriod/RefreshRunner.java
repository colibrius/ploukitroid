package com.ploukitriod;

import android.graphics.Color;
import android.graphics.Paint;

public class RefreshRunner implements Runnable {
	test layout;
	int isStarting;
	int speed;

	public RefreshRunner(test layoutRect) {
		this.layout = layoutRect;
		isStarting = 0;
		speed = 3;
		// layoutRect.getRectangle().set(wOver2-50, hOver2/5+50, wOver2+50,
		// hOver2/5-50);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (!Thread.currentThread().isInterrupted()) {
			if(layout.getEnnemy().top < layout.getRectangle().bottom  && 
					layout.getEnnemy().bottom > layout.getRectangle().top
					&& layout.getEnnemy().left < layout.getRectangle().right &&
					layout.getEnnemy().right > layout.getRectangle().left
					)
			{
				layout.isTouched = !layout.isTouched;
				speed = 3;
			}
			if (layout.getEnnemy().top > 0) {
				layout.getEnnemy().top = layout.getEnnemy().top - speed;
				layout.getEnnemy().bottom = layout.getEnnemy().bottom - speed;
			} else {
				layout.getEnnemy().top = layout.getHeight();
				layout.getEnnemy().bottom = layout.getHeight() + 100;
			}
			try {
				Thread.sleep(10);
				layout.postInvalidate();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				Thread.currentThread().interrupt();
			}
		}
	}

}

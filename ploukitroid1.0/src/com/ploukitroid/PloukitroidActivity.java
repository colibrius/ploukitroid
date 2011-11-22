package com.ploukitroid;

import android.app.Activity;
import android.os.Bundle;

public class PloukitroidActivity extends Activity {
    /** Called when the activity is first created. */
	PloukitroidView pView;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        pView = new PloukitroidView(this);
        setContentView(pView);
    }
}
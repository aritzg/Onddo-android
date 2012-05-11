package net.sareweb.android.onddo.activity;

import com.googlecode.androidannotations.annotations.EActivity;

import net.sareweb.android.onddo.R;
import android.app.Activity;
import android.os.Bundle;

@EActivity
public class MapTabActivity extends Activity {
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.map);
	}

}

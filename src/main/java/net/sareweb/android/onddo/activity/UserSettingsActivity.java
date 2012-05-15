package net.sareweb.android.onddo.activity;

import net.sareweb.android.onddo.R;
import net.sareweb.android.onddo.util.OnddoConstants;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.googlecode.androidannotations.annotations.EActivity;

@EActivity(R.layout.user_settings)
public class UserSettingsActivity extends Activity {

	SharedPreferences userPrefs;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		userPrefs = getSharedPreferences(OnddoConstants.USER_PREFS, MODE_PRIVATE);
	
	}

	
	private static String TAG = "UserSettingsActivity";

}

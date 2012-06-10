package net.sareweb.android.onddo.activity;

import net.sareweb.android.onddo.R;
import net.sareweb.android.onddo.manager.AppManager;
import net.sareweb.android.onddo.util.OnddoConstants;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import com.googlecode.androidannotations.annotations.Bean;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.ViewById;

@EActivity(R.layout.user_settings)
public class UserSettingsActivity extends Activity {

	SharedPreferences userPrefs;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		userPrefs = getSharedPreferences(OnddoConstants.USER_PREFS, MODE_PRIVATE);
	
	}
	
	@Click(R.id.btnReset)
	void resetOnddo(){
		appManager.localReset(this);
		finish();
	}
	
	@Bean
	 AppManager appManager;
	
	private static String TAG = "UserSettingsActivity";

}

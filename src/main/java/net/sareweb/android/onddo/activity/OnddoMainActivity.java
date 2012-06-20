package net.sareweb.android.onddo.activity;

import net.sareweb.android.onddo.R;
import net.sareweb.android.onddo.manager.AppManager;
import net.sareweb.android.onddo.util.ConnectionUtil;
import net.sareweb.android.onddo.util.OnddoConstants;
import android.app.ProgressDialog;
import android.app.TabActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.widget.TabHost;
import android.widget.Toast;

import com.googlecode.androidannotations.annotations.Background;
import com.googlecode.androidannotations.annotations.Bean;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.OptionsItem;
import com.googlecode.androidannotations.annotations.OptionsMenu;
import com.googlecode.androidannotations.annotations.UiThread;

@EActivity
@OptionsMenu(value = R.menu.onddo_menu)
public class OnddoMainActivity extends TabActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.onddo_main);
		userPrefs = getSharedPreferences(OnddoConstants.USER_PREFS,
				MODE_PRIVATE);

		Resources res = getResources();
		tabHost = getTabHost();
		TabHost.TabSpec spec;

		spec = tabHost
				.newTabSpec("pickings")
				.setIndicator("Pickings",
						res.getDrawable(R.drawable.ic_tab_pickings))
				.setContent(PickingsTabActivity_.intent(this).get());
		tabHost.addTab(spec);

		Intent mapIntent = new Intent().setClass(this, MapTabActivity.class);
		spec = tabHost.newTabSpec("map")
				.setIndicator("Map", res.getDrawable(R.drawable.ic_tab_map))
				.setContent(mapIntent);
		tabHost.addTab(spec);

		tabHost.setCurrentTab(0);
	}

	@OptionsItem(R.id.itemAdd)
	void itemAdd() {
		PickingEditActivity_.intent(this).pickingId(0).start();
	}

	@OptionsItem(R.id.itemSynch)
	void itemSynch() {
		if (ConnectionUtil.isOnline(this)) {
			dialog = ProgressDialog.show(this, "", "Synchronizing...", true);
			dialog.show();
			manualSynch();
		} else {
			Toast.makeText(this, "Sorry.No internet access available.",
					Toast.LENGTH_SHORT).show();
		}
	}

	@OptionsItem(R.id.itemSettings)
	void itemSettings() {
		UserSettingsActivity_.intent(this).start();
	}

	@Background
	void manualSynch() {
		mLockScreenRotation();
		appManager.synchronize(
				userPrefs.getLong(OnddoConstants.USER_PREFS_USER_ID, 0), this);
		finishedBackgroundThread(BG_RESULT_SYNCH_OK);
	}

	@UiThread
	void finishedBackgroundThread(int result) {
		switch (result) {
		case BG_RESULT_SYNCH_OK:
			dialog.cancel();
			Toast.makeText(this, "Synch done!", Toast.LENGTH_SHORT).show();
			startActivity(getIntent());
			finish();
			break;

		default:
			break;
		}
		mUnlockScreenRotation();
	}

	@OptionsItem(R.id.itemExit)
	void itemExit() {
		userPrefs.edit().clear().commit();
		finish();
		StartActivity_.intent(this).start();
	}
	
	private void mLockScreenRotation()
	{
	  // Stop the screen orientation changing during an event
	    switch (this.getResources().getConfiguration().orientation)
	    {
	  case Configuration.ORIENTATION_PORTRAIT:
	    this.setRequestedOrientation(
	ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	    break;
	  case Configuration.ORIENTATION_LANDSCAPE:
	    this.setRequestedOrientation(
	ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
	    break;
	    }
	}
	
	private void mUnlockScreenRotation()
	{
		this.setRequestedOrientation(
				ActivityInfo.SCREEN_ORIENTATION_SENSOR);

	}

	@Bean
	AppManager appManager;
	
	SharedPreferences userPrefs;
	TabHost tabHost;
	
	private ProgressDialog dialog;
	private final int BG_RESULT_SYNCH_OK = 0;
	private static String TAG = "OnddoMainActivity";
}

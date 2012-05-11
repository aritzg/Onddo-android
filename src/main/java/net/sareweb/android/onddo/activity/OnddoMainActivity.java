package net.sareweb.android.onddo.activity;

import net.sareweb.android.onddo.R;
import net.sareweb.android.onddo.manager.AppManager;
import net.sareweb.android.onddo.util.OnddoConstants;
import android.app.Activity;
import android.app.TabActivity;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.Toast;

import com.googlecode.androidannotations.annotations.Bean;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.Extra;
import com.googlecode.androidannotations.annotations.OptionsItem;
import com.googlecode.androidannotations.annotations.OptionsMenu;
import com.googlecode.androidannotations.annotations.ViewById;

@EActivity
@OptionsMenu(value = R.menu.onddo_menu)
public class OnddoMainActivity extends TabActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.onddo_main);
		userPrefs = getSharedPreferences(OnddoConstants.USER_PREFS, MODE_PRIVATE);
		
		Resources res = getResources();
		TabHost tabHost = getTabHost();
	    TabHost.TabSpec spec;
		
		spec = tabHost.newTabSpec("pickings").setIndicator("Pickings",
                res.getDrawable(R.drawable.ic_tab_pickings))
            .setContent(PickingsTabActivity_.intent(this). get());
		tabHost.addTab(spec);
		
		spec = tabHost.newTabSpec("map").setIndicator("Map",
                res.getDrawable(R.drawable.ic_tab_map))
            .setContent(PickingsTabActivity_.intent(this).get());
		tabHost.addTab(spec);
		
		tabHost.setCurrentTab(0);
	}
	

	 @OptionsItem(R.id.itemAdd)
	 void itemAdd(){
		 PickingEditActivity_.intent(this).pickingId(0).start();
	 }
	 
	 @OptionsItem(R.id.itemSynch)
	 void itemSynch(){
		 appManager.synchronize(userPrefs.getLong(OnddoConstants.USER_PREFS_USER_ID, 0), this);
	 }
	 
	 @OptionsItem(R.id.itemExit)
	 void itemExit(){
		 userPrefs.edit().clear().commit();
		 finish();
		 StartActivity_.intent(this).start();
	 }
	 
	 SharedPreferences userPrefs;
	 private static String TAG = "OnddoMainActivity";
	 @Bean
	 AppManager appManager;

}

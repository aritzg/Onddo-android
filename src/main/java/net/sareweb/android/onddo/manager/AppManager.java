package net.sareweb.android.onddo.manager;

import android.content.Context;
import android.util.Log;

import com.googlecode.androidannotations.annotations.Bean;
import com.googlecode.androidannotations.annotations.EBean;

@EBean
public class AppManager{

	public void synchronize(long userId , Context context) {
		Log.d(TAG,"Sinchronizing app!");
		
		pickingNamager.synchronize(context, userId);
	}
	
	@Bean
	PickingManager pickingNamager;

	String TAG ="AppManager";
}

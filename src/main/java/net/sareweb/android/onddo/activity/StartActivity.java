package net.sareweb.android.onddo.activity;

import net.sareweb.android.onddo.R;
import net.sareweb.android.onddo.util.OnddoConstants;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.googlecode.androidannotations.annotations.EActivity;

@EActivity(R.layout.start)
public class StartActivity extends Activity {

	SharedPreferences userPrefs;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		userPrefs = getSharedPreferences(OnddoConstants.USER_PREFS, MODE_PRIVATE);
		String loggedUser = userPrefs.getString(OnddoConstants.USER_PREFS_EMAIL_ADDRESS, "");
		
		if(loggedUser.equals("")){
			Log.d(TAG, "Not logged yet!");
			showAcountDialog();	
		}
		else{
			Log.d(TAG, "Logged as " + loggedUser);
			finish();
			OnddoMainActivity_.intent(this).start();
		}
	}

	private void showAcountDialog() {

		AccountManager am = AccountManager.get(this);
		Account[] accounts = am.getAccountsByType("com.google");
		
		if(accounts!=null){
		
			final CharSequence[] items = new CharSequence[accounts.length + 1];
			for (int i = 0; i < accounts.length; i++) {
				items[i]=((Account)accounts[i]).name;
			}
			items[accounts.length]="Other";
	
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Choose account");
			builder.setItems(items, new DialogInterface.OnClickListener() {
			    public void onClick(DialogInterface dialog, int item) {
			        goToLogIn(items[item].toString());
			    }
			});
			AlertDialog alert = builder.create();
			alert.show();
		}
	}
	
	private void goToLogIn(String emailAddress){
		String mail = emailAddress;
		if(emailAddress.equals(other))mail="";
		LogInActivity_.intent(this).emailAddress(mail).start(); 
	}

	
	private String other ="Other";
	private static String TAG = "StartActivity";

}

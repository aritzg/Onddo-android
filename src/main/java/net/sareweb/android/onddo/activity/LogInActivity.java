package net.sareweb.android.onddo.activity;

import net.sareweb.android.onddo.R;
import net.sareweb.android.onddo.util.OnddoConstants;
import net.sareweb.lifedroid.manager.UserManager;
import net.sareweb.lifedroid.model.User;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.googlecode.androidannotations.annotations.Background;
import com.googlecode.androidannotations.annotations.Bean;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.Extra;
import com.googlecode.androidannotations.annotations.UiThread;
import com.googlecode.androidannotations.annotations.ViewById;

@EActivity
public class LogInActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		txEmail.setText(emailAddress);
		userPrefs = getSharedPreferences(OnddoConstants.USER_PREFS, MODE_PRIVATE);
	}

	@Click(R.id.btnLogIn)
	void clickBtnLogIn() {
		dialog = ProgressDialog.show(this, "", "Loging in...", true);
		dialog.show();
		validateUser(txEmail.getText().toString(), txPass.getText().toString());
	}
	
	@Background
	void validateUser(String email, String pass) {
		User user = userManager.validateUser( email, pass);
		if(user!=null){
			SharedPreferences.Editor editor = userPrefs.edit();
			editor.putLong(OnddoConstants.USER_PREFS_USER_ID, user.getUserId());
			editor.putString(OnddoConstants.USER_PREFS_NAME, user.getScreenName());
			editor.putString(OnddoConstants.USER_PREFS_EMAIL_ADDRESS, user.getEmailAddress());
			editor.putString(OnddoConstants.USER_PREFS_PASS, txPass.getText().toString());
			editor.commit();
			validateResult(LOG_IN_OK);
		}
		else{
			validateResult(LOG_IN_ERROR);
		}
	}
    
    @UiThread
	void validateResult(int result){
    	dialog.cancel();
    	if(result==LOG_IN_OK){
    		OnddoMainActivity_.intent(this).start();
    	}
    	else{
    		Toast.makeText(getApplicationContext(), "Wrong pass or not registered!", Toast.LENGTH_SHORT).show();
    	}
	}
	
	@Click(R.id.btnRegister)
	void clickBtnRegister() {
		RegisterActivity_.intent(this).emailAddress(txEmail.getText().toString()).start();
	}

	
	@ViewById
	EditText txEmail;
	@ViewById
	EditText txPass;
	@ViewById
	Button btnLogIn;
	@ViewById
	Button btnRegister;

	@Extra(OnddoConstants.PARAM_EMAIL_ADDRESS)
	String emailAddress;
	
	@Bean
	UserManager userManager;
	private ProgressDialog dialog;
	private SharedPreferences userPrefs;
	private static String TAG = "LogInActivity";
	
	private static final int LOG_IN_OK = 0;
	private static final int LOG_IN_ERROR = 1;

}

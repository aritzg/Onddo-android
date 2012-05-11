package net.sareweb.android.onddo.activity;

import net.sareweb.android.onddo.R;
import net.sareweb.android.onddo.util.OnddoConstants;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.Extra;
import com.googlecode.androidannotations.annotations.ViewById;

@EActivity
public class RegisterActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		txEmail.setText(emailAddress);
		Toast.makeText(getApplicationContext(), "Would you like to register with " + emailAddress + " email address?", Toast.LENGTH_SHORT).show();
	}
	
	@ViewById
	EditText txEmail;
	
	@Extra(OnddoConstants.PARAM_EMAIL_ADDRESS)
	String emailAddress;

	private static String TAG = "RegisterActivity";

}

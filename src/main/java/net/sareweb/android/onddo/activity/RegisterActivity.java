package net.sareweb.android.onddo.activity;

import net.sareweb.android.onddo.R;
import net.sareweb.android.onddo.util.ConnectionUtil;
import net.sareweb.android.onddo.util.OnddoConstants;
import net.sareweb.lifedroid.liferay.service.UserRESTService;
import net.sareweb.lifedroid.model.User;
import net.sareweb.lifedroid.util.LDConstants;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.googlecode.androidannotations.annotations.Background;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.Extra;
import com.googlecode.androidannotations.annotations.UiThread;
import com.googlecode.androidannotations.annotations.ViewById;

@EActivity
public class RegisterActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		userRestService = new UserRESTService(OnddoConstants.DEFAULT_USER, OnddoConstants.DEFAULT_PASS);
		userPrefs = getSharedPreferences(OnddoConstants.USER_PREFS, MODE_PRIVATE);
		setContentView(R.layout.register);
		txEmail.setText(emailAddress);
	}
	
	@Click(R.id.btnRegister)
	void clickBtnRegister(){
		if(ConnectionUtil.isOnline(this)){
			if(validateRegisterForm()){
				dialog = ProgressDialog.show(this, "", "Registering user.", true);
				dialog.show();
				createUser(txName.getText().toString(), txSurname.getText().toString(), txEmail.getText().toString(), txPass.getText().toString());
			}
		}
		else{
			Toast.makeText(this, "Sorry.No internet access available.", Toast.LENGTH_SHORT).show();
		}
	}
	
	private boolean validateRegisterForm(){
		if(txName.getText().toString().equals("")){
			Toast.makeText(this, "Name required!", Toast.LENGTH_SHORT).show();
			return false;
		}
		if(txSurname.getText().toString().equals("")){
			Toast.makeText(this, "Surname required!", Toast.LENGTH_SHORT).show();
			return false;
		}
		if(!android.util.Patterns.EMAIL_ADDRESS.matcher(txEmail.getText().toString()).matches()){
			Toast.makeText(this, "Not valid email address!", Toast.LENGTH_SHORT).show();
			return false;
		}
		if(txPass.getText().toString().length()==0){
			Toast.makeText(this, "Empty passwords not allowed!", Toast.LENGTH_SHORT).show();
			return false;			
		}
		if(!txPass.getText().toString().equals(txPass2.getText().toString())){
			Toast.makeText(this, "Passwords are not equal", Toast.LENGTH_SHORT).show();
			return false;
		}
		return true;
	}
	
	@Background
	void createUser(String name, String surname, String email, String pass){
		User u = userRestService.getUserByEmailAddress(email);
		if(u.getEmailAddress()!=null){//Retrieve user
			createUserResult(USER_EXISTS);
			return;
		}
		u = userRestService.addUser(Long.parseLong(LDConstants.LIFERAY_COMPANY_ID),
								false,
								pass, pass,
								true, 
								null,
								email,
								0,
								"aa",
								"es_ES",
								name,
								null,
								surname,
								1,
								1,
								true,
								1,
								1,
								2000,
								null,
								null,
								null,
								null,
								null, 
								true);
		
		if(u!=null){
			loginUser(u);
			createUserResult(REGISTER_OK);
		}
		
	}
	
	@UiThread
	void createUserResult(int result){
		switch (result) {
		case USER_EXISTS:
			Toast.makeText(this, "User already exists!", Toast.LENGTH_LONG).show();
			dialog.cancel();
			break;
			
		case REGISTER_OK:
			Toast.makeText(this, "User registered", Toast.LENGTH_LONG).show();
			dialog.cancel();
			finish();
			OnddoMainActivity_.intent(this).start();
			finish();
			break;
		default:
			break;
		}
	}
	
	private void loginUser(User user){
		SharedPreferences.Editor editor = userPrefs.edit();
		editor.putLong(OnddoConstants.USER_PREFS_USER_ID, user.getUserId());
		editor.putString(OnddoConstants.USER_PREFS_NAME, user.getScreenName());
		editor.putString(OnddoConstants.USER_PREFS_EMAIL_ADDRESS, user.getEmailAddress());
		editor.putString(OnddoConstants.USER_PREFS_PASS, txPass.getText().toString());
		editor.commit();
	}
	
	@ViewById
	EditText txName;
	@ViewById
	EditText txSurname;
	@ViewById
	EditText txEmail;
	@ViewById
	EditText txPass;
	@ViewById
	EditText txPass2;
	
	@Extra(OnddoConstants.PARAM_EMAIL_ADDRESS)
	String emailAddress;
	private ProgressDialog dialog;
	private SharedPreferences userPrefs;
	private UserRESTService userRestService;
	private static String TAG = "RegisterActivity";
	
	private static final int REGISTER_OK = 0;
	private static final int USER_EXISTS = 1;
	private static final int REGISTER_ERROR = 2;

}

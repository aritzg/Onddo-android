package net.sareweb.android.onddo.activity;

import java.io.File;
import java.util.Date;

import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.Extra;
import com.googlecode.androidannotations.annotations.ViewById;

import net.sareweb.android.onddo.R;
import net.sareweb.android.onddo.model.Picking;
import net.sareweb.android.onddo.sqlite.PickingOpenHelper;
import net.sareweb.android.onddo.util.OnddoConstants;
import net.sareweb.lifedroid.exception.IntrospectionException;

import android.app.Activity;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

@EActivity
public class PickingEditActivity extends Activity {

	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.picking_edit);
		
		pickingHelper = new PickingOpenHelper(this);
		
		userPrefs = getSharedPreferences(OnddoConstants.USER_PREFS, MODE_PRIVATE);
		userId = userPrefs.getLong(OnddoConstants.USER_PREFS_USER_ID, 0);
		
		if(pickingId!=0){
			retrievePricking();
		}
		else{
			initPicking();
		}
		populateFields(p);
	}
	
	@Click(R.id.btnSave)
	void clickBtnSave(){
		updatePickingFromFields();
		persistPicking();
		finish();
	}
	
	@Click(R.id.btnBack)
	void clickBtnBack(){
		finish();
	}
	
	private void retrievePricking(){
		p = pickingHelper.getById(pickingId);
	}
	
	private void initPicking(){
		p = new Picking();
		p.setCreateDate(new Date().getTime());
		p.setLat(0.0);
		p.setLng(0.0);
		p.setUserId(userId);
	}
	
	private void persistPicking(){
		try {
			if(p.getId()==null){
				p.setCreateDate(new Date().getTime());
			}
			p.setModifiedDate(new Date().getTime());
			
			Log.d(TAG,"p.getModifiedDate()  " +  p.getModifiedDate());
			
			p = pickingHelper.persist(p);
			
			Toast.makeText(this, "Picking saved!", Toast.LENGTH_LONG).show();
		} catch (IntrospectionException e) {
			Toast.makeText(this, "Error saving picking!", Toast.LENGTH_LONG).show();
		}
	}
	
	private void updatePickingFromFields(){
		if(!txLat.getText().toString().equals(""))p.setLat(Double.parseDouble(txLat.getText().toString()));
		if(!txLng.getText().toString().equals(""))p.setLng(Double.parseDouble(txLng.getText().toString()));
		p.setType(txType.getText().toString());
		//p.setWeather(spWeather.getSelectedItem().));
	}
	
	private void populateFields(Picking p) {
		txLat.setText(p.getLat().toString());
		txLng.setText(p.getLng().toString());
		txType.setText(p.getType());
	}
	
	@ViewById
	EditText txLat;
	@ViewById
	EditText txLng;
	@ViewById
	EditText txType;
	@ViewById
	Spinner spWeather;
	
	@Extra(OnddoConstants.PARAM_PICKING_ID)
	long pickingId;
	
	Picking p;
	PickingOpenHelper pickingHelper;
	long userId;
	SharedPreferences userPrefs;
	
	String TAG ="PickingEditActivity";
	
}

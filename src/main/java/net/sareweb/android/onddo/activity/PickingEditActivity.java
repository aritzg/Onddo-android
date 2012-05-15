package net.sareweb.android.onddo.activity;

import java.util.Date;

import net.sareweb.android.onddo.R;
import net.sareweb.android.onddo.model.Picking;
import net.sareweb.android.onddo.sqlite.PickingOpenHelper;
import net.sareweb.android.onddo.util.OnddoConstants;
import net.sareweb.lifedroid.exception.IntrospectionException;
import net.sareweb.lifedroid.sqlite.generic.LDSQLiteHelper;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.Extra;
import com.googlecode.androidannotations.annotations.ViewById;

@EActivity
public class PickingEditActivity extends Activity implements LocationListener {

	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.picking_edit);
		
		configGPS();
		
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
	
	protected void onStop() {
	    super.onStop();
	    locationManager.removeUpdates(this);
	}
	

	@Click(R.id.btnSave)
	void clickBtnSave(){
		updatePickingFromFields();
		persistPicking();
		finish();
	}
	
	@Click(R.id.btnRefresh)
	void clickBtnRefresh(){
		if(!gpsEnabled){
			Toast.makeText(this, "GPS not enabled! ", Toast.LENGTH_SHORT).show();
			return;
		}
		if(location==null){
			Toast.makeText(this, "Still waiting for location. Sorry! ", Toast.LENGTH_SHORT).show();
			return;
		}
		txLat.setText(String.valueOf(location.getLatitude()));
		txLng.setText(String.valueOf(location.getLongitude()));
		Toast.makeText(this, "Location updated!", Toast.LENGTH_SHORT).show();
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
			
			if(p.getObjectStatus().equals(LDSQLiteHelper.OBJECT_STATUS_SYNCH))p.setObjectStatus(LDSQLiteHelper.OBJECT_STATUS_DIRTY);
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
	
	private void configGPS() {
		locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
		gpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
		if(!gpsEnabled){
			Toast.makeText(this, "GPS not enabled! ", Toast.LENGTH_SHORT).show();
		}
		else{
			locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 5, this);
		}
	}
	
	
	@Override
	public void onLocationChanged(Location location) {
		this.location=location;
	}

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub
	}
	
	@ViewById
	Button btnRefresh;
	
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
	LocationManager locationManager;
	Location location;
	boolean gpsEnabled;
		
	String TAG ="PickingEditActivity";

	
	
}


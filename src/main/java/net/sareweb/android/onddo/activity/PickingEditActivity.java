package net.sareweb.android.onddo.activity;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import net.sareweb.android.onddo.R;
import net.sareweb.android.onddo.adapter.DialogImagesAdapter;
import net.sareweb.android.onddo.dialog.MoonDialogOnClickListener;
import net.sareweb.android.onddo.dialog.WeatherDialogOnClickListener;
import net.sareweb.android.onddo.model.Picking;
import net.sareweb.android.onddo.sqlite.PickingOpenHelper;
import net.sareweb.android.onddo.util.ImageUtil;
import net.sareweb.android.onddo.util.MoonUtil;
import net.sareweb.android.onddo.util.OnddoConstants;
import net.sareweb.android.onddo.util.WeatherUtil;
import net.sareweb.lifedroid.exception.IntrospectionException;
import net.sareweb.lifedroid.sqlite.generic.LDSQLiteHelper;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
	
	@Click(R.id.imgLocation)
	void clickImgLocation(){
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
	
	@Click(R.id.imgMoon)
	void clickImgMoon(){
		moonsAdapter = new DialogImagesAdapter(this, MoonUtil.getMoonDialogOptions());
		new AlertDialog.Builder(this)
	    .setTitle("Moon Phase")
	    .setAdapter(moonsAdapter, new MoonDialogOnClickListener(p, imgMoon)).show();
	}
	
	@Click(R.id.imgWeather)
	void clickImgWeather(){
		weathersAdapter = new DialogImagesAdapter(this, WeatherUtil.getWeatherDialogOptions());
		new AlertDialog.Builder(this)
	    .setTitle("Weather")
	    .setAdapter(weathersAdapter, new WeatherDialogOnClickListener(p, imgWeather)).show();
	}
	
	@Click(R.id.imgCamera)
	void clickImgCamera(){
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		fileUri = Uri.fromFile(ImageUtil.getOutputMediaFile());
		imagePath = fileUri.getPath();
		intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
		startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
	}
	
	@Click(R.id.imgGallery)
	void clickImgGallery(){
		Intent intent = new Intent(Intent.ACTION_PICK,
			     android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
			   startActivityForResult(intent, GET_IMG_FROM_GALLERY_ACTIVITY_REQUEST_CODE);
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
		p.setMoonPhase(MoonUtil.calculatePhase(new Date()));
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
		if(!p.getMoonPhase().equals("")){
			imgMoon.setImageResource(MoonUtil.getMoonDialogOptionsMap().get(p.getMoonPhase()).getImgResId());
		}
		if(!p.getWeather().equals("")){
			imgWeather.setImageResource(WeatherUtil.getWeatherDialogOptionsMap().get(p.getWeather()).getImgResId());
		}
		if(p.getImgName()!=null && !p.getImgName().equals("")){
			imgPic.setImageURI(Uri.fromFile(new File(ImageUtil.getMediaStorageDir() + "/" + p.getImgName())));
		}
		
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
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
	        if (resultCode == RESULT_OK) {
	        	File image = new File(imagePath);
	        	ImageUtil.resizeFile(image);
	        	imgPic.setImageURI(Uri.fromFile(new File(imagePath)));
	        	p.setImgName(image.getName());
	        	
	        } else if (resultCode == RESULT_CANCELED) {
	            // User cancelled the image capture
	        } else {
	            // Image capture failed, advise user
	        }
	    }
	    else if (requestCode == GET_IMG_FROM_GALLERY_ACTIVITY_REQUEST_CODE) {
	        if (resultCode == RESULT_OK) {
	        	Uri targetUri = data.getData();
	        	
	        	File original = new File(targetUri.toString());
	        	File dest = ImageUtil.getOutputMediaFile();
	        	
	        	try {
					ImageUtil.copyInputStreamToFile(getContentResolver().openInputStream(targetUri), dest);
					ImageUtil.resizeFile(dest);
					
					imgPic.setImageURI(targetUri);
					p.setImgName(dest.getName());
				} 
	        	catch (IOException e) {
	        		Log.e(TAG, "Error copying file", e);
					Toast.makeText(this, "Error copying file!", Toast.LENGTH_SHORT).show();
				}
	        	
	        }
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
	EditText txLat;
	@ViewById
	EditText txLng;
	@ViewById
	EditText txType;
	@ViewById
	ImageView imgMoon;
	@ViewById
	ImageView imgWeather;
	@ViewById
	ImageView imgPic;
	
	@Extra(OnddoConstants.PARAM_PICKING_ID)
	long pickingId;
	
	Picking p;
	PickingOpenHelper pickingHelper;
	long userId;
	SharedPreferences userPrefs;
	LocationManager locationManager;
	Location location;
	boolean gpsEnabled;
	DialogImagesAdapter moonsAdapter;
	DialogImagesAdapter weathersAdapter;
	Uri fileUri;
	String imagePath;
	final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	final int GET_IMG_FROM_GALLERY_ACTIVITY_REQUEST_CODE = 200;
	
		
	String TAG ="PickingEditActivity";

	
	
}


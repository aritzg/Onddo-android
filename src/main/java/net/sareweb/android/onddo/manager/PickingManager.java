package net.sareweb.android.onddo.manager;

import java.util.ArrayList;
import java.util.List;

import net.sareweb.android.onddo.liferay.service.PickingRESTService;
import net.sareweb.android.onddo.model.Picking;
import net.sareweb.android.onddo.sqlite.PickingOpenHelper;
import net.sareweb.android.onddo.util.OnddoConstants;
import net.sareweb.lifedroid.exception.IntrospectionException;
import net.sareweb.lifedroid.model.LDObject;
import net.sareweb.lifedroid.sqlite.generic.LDSQLiteHelper;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.googlecode.androidannotations.annotations.EBean;

@EBean
public class PickingManager {
	List<Picking> usersPickings;
	
	List<Picking> pickingsToBeDeleted = new ArrayList<Picking>();
	List<Picking> pickingsToBeUpdated = new ArrayList<Picking>();
	List<Picking> pickingsToBeAdded = new ArrayList<Picking>();
	
	public void synchronize(Context context, long userId){
		
		userPrefs = context.getSharedPreferences(OnddoConstants.USER_PREFS, Context.MODE_PRIVATE);
		
		pickingHelper = new PickingOpenHelper(context);
		
		String email = userPrefs.getString(OnddoConstants.USER_PREFS_EMAIL_ADDRESS, "");
		String pass = userPrefs.getString(OnddoConstants.USER_PREFS_PASS, "");
		prs = new PickingRESTService(email, pass);
		
		Log.d(TAG, "Synchronizing pickings!");
		
		
		usersPickings = pickingHelper.getByUserIdDeletedToo(userId);
		if(usersPickings==null || usersPickings.size()==0){
			Log.d(TAG, "No pickings to be updated");
		}
		else{
			Log.d(TAG, "Updating " + usersPickings.size() + " picking");
		}
		
		separatePickings();
		deletePickings();
		updatePickings();
		addPickings();
		
		retrievePickings(userId);
	}
	
	

	private void separatePickings() {
		if(usersPickings == null) return ;
		for (Picking picking: usersPickings) {
			Log.d(TAG, "\tStatus " + picking.getObjectStatus());
			if(picking.getObjectStatus()!=null && picking.getObjectStatus().equals(LDSQLiteHelper.OBJECT_STATUS_DIRTY)){
				pickingsToBeUpdated.add(picking);
			}
			else if(picking.getObjectStatus()!=null && picking.getObjectStatus().equals(LDSQLiteHelper.OBJECT_STATUS_NEW)){
				pickingsToBeAdded.add(picking);
			}
			else{
				pickingsToBeDeleted.add(picking);
			}
		}
	}
	private void deletePickings() {
		for (Picking picking: pickingsToBeDeleted) {
			Log.d(TAG, "Deleting picking " + picking.getPickingId());
			if(picking.getObjectStatus()!=null && picking.getObjectStatus().equals(LDSQLiteHelper.OBJECT_STATUS_DELETED_REMOTE)){
				prs.deletePickingById(String.valueOf(picking.getPickingId()));
			}
			pickingHelper.delete(picking.getPickingId());
		}
	}

	private void updatePickings() {
		for (Picking picking: pickingsToBeUpdated) {
			Log.d(TAG, "Updating picking " + picking.getPickingId());
			prs.updatePicking(picking);
			pickingHelper.delete(picking.getPickingId());
		}
	}
	
	private void addPickings() {
		for (Picking picking: pickingsToBeAdded) {
			Log.d(TAG, "Adding picking " + picking.getPickingId());
			prs.addPicking(picking);
			pickingHelper.delete(picking.getPickingId());
		}
	}
	
	private void retrievePickings(long userId) {
		List<Picking> pickings = prs.findByUserId(userId);
		if(pickings!=null){
			Log.d("TAG","Pickings retrieved from server " + pickings.size());
			for (Picking picking : pickings) {
				try {
					picking.setObjectStatus(LDSQLiteHelper.OBJECT_STATUS_SYNCH);
					pickingHelper.persist(picking);
				} catch (IntrospectionException e) {
					Log.e(TAG,"Error persisting retrieved picking");
				}
			}
		}
		else{
			Log.d("TAG","No pickings retrieved!");
		}
		
	}

	SharedPreferences userPrefs;
	private PickingOpenHelper pickingHelper;
	private PickingRESTService prs;
	private String TAG = "PickingManager";
}

package net.sareweb.android.onddo.activity;

import java.util.List;

import net.sareweb.android.onddo.R;
import net.sareweb.android.onddo.adapter.PickingAdapter;
import net.sareweb.android.onddo.model.Picking;
import net.sareweb.android.onddo.sqlite.PickingOpenHelper;
import net.sareweb.android.onddo.util.OnddoConstants;
import android.app.ListActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.googlecode.androidannotations.annotations.EActivity;


@EActivity
public class PickingsTabActivity extends ListActivity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pickings);
		
		userPrefs = getSharedPreferences(OnddoConstants.USER_PREFS, MODE_PRIVATE);
		userId = userPrefs.getLong(OnddoConstants.USER_PREFS_USER_ID, 0);
		pickingHelper = new PickingOpenHelper(this);
		
		pickings =  pickingHelper.getByUserId(userId);
		Log.d(TAG, "Read pickings: " + pickings.size());
		
		adapter = new PickingAdapter(this, pickings);
		ListView list = (ListView)findViewById(android.R.id.list);
		list.setAdapter(adapter);

	}
	
	@Override
	protected void onResume() {
		Log.d(TAG, "ACtivity resumed");
		pickings =  pickingHelper.getByUserId(userId);
		adapter.updateList(pickings);
		super.onResume();
	}
	
	
	SharedPreferences userPrefs;
	long userId;
	PickingOpenHelper pickingHelper;
	PickingAdapter adapter;
	List<Picking> pickings;
	private static String TAG = "PickingsTabActivity";
	
	
}

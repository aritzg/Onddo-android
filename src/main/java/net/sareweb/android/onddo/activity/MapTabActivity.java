package net.sareweb.android.onddo.activity;

import java.util.List;

import net.sareweb.android.onddo.R;
import net.sareweb.android.onddo.model.Picking;
import net.sareweb.android.onddo.overlay.PickingItemizedOverlay;
import net.sareweb.android.onddo.overlay.PickingOverlayItem;
import net.sareweb.android.onddo.sqlite.PickingOpenHelper;
import net.sareweb.android.onddo.util.OnddoConstants;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;


public class MapTabActivity extends MapActivity {
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.map);
	    
	    MapView mapView = (MapView) findViewById(R.id.mapview);
	    mapView.setBuiltInZoomControls(true);
	    
	    mapOverlays = mapView.getOverlays();
	    loadPickingsInMap();
	}
	
	
	
	@Override
	protected void onResume() {
		super.onResume();
		loadPickingsInMap();
	}
	
	private void loadPickingsInMap(){
		Drawable drawable = this.getResources().getDrawable(R.drawable.pin);
	    itemizedoverlay = new PickingItemizedOverlay(drawable, this);
	    
	    SharedPreferences userPrefs = getSharedPreferences(OnddoConstants.USER_PREFS, MODE_PRIVATE);
	    Long userId = userPrefs.getLong(OnddoConstants.USER_PREFS_USER_ID, 0);
	    
	    createOverlays(userId);
	}



	private void createOverlays(long userId) {
		PickingOpenHelper pickingHelper = new PickingOpenHelper(this);

		List<Picking> pickings = pickingHelper.getByUserId(userId);

		if (pickings == null || pickings.size() < 1)
			return;
		mapOverlays.clear();
		for (Picking p : pickings) {

			Double lat = p.getLat() * 1000000;
			Double lng = p.getLng() * 1000000;

			if (lat.intValue() != 0 && lng.intValue() != 0) {
				GeoPoint point = new GeoPoint(lat.intValue(), lng.intValue());
				PickingOverlayItem overlayitem = new PickingOverlayItem(point,
						p.getType(), "", p);
				itemizedoverlay.addOverlay(overlayitem);
				mapOverlays.add(itemizedoverlay);
			}
		}
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	
	private List<Overlay> mapOverlays;
	private PickingItemizedOverlay itemizedoverlay;

}

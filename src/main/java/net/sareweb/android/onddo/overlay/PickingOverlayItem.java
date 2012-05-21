package net.sareweb.android.onddo.overlay;

import net.sareweb.android.onddo.model.Picking;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.OverlayItem;

public class PickingOverlayItem extends OverlayItem {
	
	private Picking picking;
	
	public PickingOverlayItem(GeoPoint point, String title, String snippet) {
		super(point, title, snippet);
		picking = new Picking();
	}
	
	public PickingOverlayItem(GeoPoint point, String title, String snippet, Picking picking) {
		super(point, title, snippet);
		this.picking = picking;
	}
	
	public Picking getPicking(){
		return picking;
	}

}

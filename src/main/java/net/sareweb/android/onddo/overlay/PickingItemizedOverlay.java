package net.sareweb.android.onddo.overlay;

import java.io.File;
import java.util.ArrayList;

import net.sareweb.android.onddo.R;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class PickingItemizedOverlay extends ItemizedOverlay<PickingOverlayItem> {

	private ArrayList<PickingOverlayItem> mOverlays = new ArrayList<PickingOverlayItem>();
	Context mContext;

	public PickingItemizedOverlay(Drawable defaultMarker) {
		super(boundCenterBottom(defaultMarker));
	}

	public PickingItemizedOverlay(Drawable defaultMarker, Context context) {
		super(boundCenterBottom(defaultMarker));
		mContext = context;
	}

	public void addOverlay(PickingOverlayItem overlay) {
		mOverlays.add(overlay);
		populate();
	}

	@Override
	protected PickingOverlayItem createItem(int i) {
		return mOverlays.get(i);
	}

	@Override
	public int size() {
		return mOverlays.size();
	}

	@Override
	protected boolean onTap(int index) {
		PickingOverlayItem item = mOverlays.get(index);
		Dialog dialog = new Dialog(mContext);
		dialog.setContentView(R.layout.picking_dialog);

		dialog.setTitle(item.getTitle());

		TextView text = (TextView) dialog.findViewById(R.id.text);
		text.setText(item.getPicking().getType());
		
		ImageView image = (ImageView) dialog.findViewById(R.id.image);
		image.setImageURI(Uri.fromFile(new File(item.getPicking().getImgName())));

		dialog.show();
		return true;
	}

}

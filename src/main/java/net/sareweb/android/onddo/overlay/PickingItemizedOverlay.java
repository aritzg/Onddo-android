package net.sareweb.android.onddo.overlay;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import net.sareweb.android.onddo.R;
import net.sareweb.android.onddo.model.Picking;
import net.sareweb.android.onddo.util.ImageUtil;
import net.sareweb.android.onddo.util.MoonUtil;
import net.sareweb.android.onddo.util.WeatherUtil;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.maps.ItemizedOverlay;

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
		Picking p = item.getPicking();
		Dialog dialog = new Dialog(mContext);
		dialog.setContentView(R.layout.picking_dialog);

		dialog.setTitle(item.getTitle());
		
		ImageView imgPic = (ImageView) dialog.findViewById(R.id.imgPic);
		ImageUtil.setImageToImageView(imgPic, p.getImgName());
		
		TextView date = (TextView) dialog.findViewById(R.id.date);
		Date tmpDate = new Date();
		if(p.getCreateDate()!=null)tmpDate = new Date(p.getCreateDate());
		SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd");
		date.setText("Date : " + sdf.format(tmpDate));
		
		if(!p.getMoonPhase().equals("")){
			ImageView imgMoon = (ImageView) dialog.findViewById(R.id.imgMoon);
			imgMoon.setImageResource(MoonUtil.getMoonDialogOptionsMap().get(p.getMoonPhase()).getImgResId());
		}
		if(!p.getWeather().equals("")){
			ImageView imgWeather = (ImageView) dialog.findViewById(R.id.imgWeather);
			imgWeather.setImageResource(WeatherUtil.getWeatherDialogOptionsMap().get(p.getWeather()).getImgResId());
		}
		
		TextView temperature = (TextView) dialog.findViewById(R.id.temperature);
		temperature.setText("T: " + p.getTemperature() + "ºC");
		
		TextView humidity = (TextView) dialog.findViewById(R.id.humidity);
		humidity.setText("H: " + p.getHumidity() +"%");
	
		dialog.show();
		return true;
	}

}

package net.sareweb.android.onddo.dialog;

import net.sareweb.android.onddo.model.Picking;
import net.sareweb.android.onddo.util.WeatherUtil;
import android.content.DialogInterface;
import android.widget.ImageView;

public class WeatherDialogOnClickListener implements DialogInterface.OnClickListener {

	private Picking picking;
	private ImageView image;
	
	public WeatherDialogOnClickListener(Picking picking, ImageView image){
		this.picking = picking;
		this.image = image;
	}

	@Override
	public void onClick(DialogInterface dialog, int position) {
		DialogImageOption dialogImageOption = WeatherUtil.getWeatherDialogOptions().get(position);
		
		picking.setWeather(dialogImageOption.getValue());
		image.setImageResource(dialogImageOption.getImgResId());
	}

}

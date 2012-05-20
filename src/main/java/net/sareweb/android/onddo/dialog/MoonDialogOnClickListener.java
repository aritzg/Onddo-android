package net.sareweb.android.onddo.dialog;

import net.sareweb.android.onddo.model.Picking;
import net.sareweb.android.onddo.util.MoonUtil;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class MoonDialogOnClickListener implements DialogInterface.OnClickListener {

	private Picking picking;
	private ImageView image;
	
	public MoonDialogOnClickListener(Picking picking, ImageView image){
		this.picking = picking;
		this.image = image;
	}

	@Override
	public void onClick(DialogInterface dialog, int position) {
		DialogImageOption dialogImageOption = MoonUtil.getMoonDialogOptions().get(position);
		
		picking.setMoonPhase(dialogImageOption.getValue());
		image.setImageResource(dialogImageOption.getImgResId());
	}

}

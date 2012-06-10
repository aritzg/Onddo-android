package net.sareweb.android.onddo.activity;

import net.sareweb.android.onddo.R;
import net.sareweb.android.onddo.util.ImageUtil;
import net.sareweb.android.onddo.util.OnddoConstants;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.Extra;
import com.googlecode.androidannotations.annotations.ViewById;

@EActivity
public class PickingViewActivity extends Activity {

	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.picking_view);
		ImageUtil.setImageToImageView(imgPic, imageName);

	}

	@Extra(OnddoConstants.PARAM_IMAGE_NAME)
	String imageName;
	@ViewById
	ImageView imgPic;

	String TAG = "PickingViewActivity";

}

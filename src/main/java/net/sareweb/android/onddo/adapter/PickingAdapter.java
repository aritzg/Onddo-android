package net.sareweb.android.onddo.adapter;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import net.sareweb.android.onddo.R;
import net.sareweb.android.onddo.activity.PickingEditActivity_;
import net.sareweb.android.onddo.model.Picking;
import net.sareweb.android.onddo.sqlite.PickingOpenHelper;
import net.sareweb.android.onddo.util.ImageUtil;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PickingAdapter extends BaseAdapter implements OnClickListener,
		OnLongClickListener, DialogInterface.OnClickListener {

	private List<Picking> pickings;
	private Context context;
	private PickingOpenHelper pickingHelper;

	public PickingAdapter(Context context, List<Picking> pickings) {
		this.context = context;
		this.pickings = pickings;
		pickingHelper = new PickingOpenHelper(context);
	}

	@Override
	public int getCount() {
		if (pickings == null)
			return 0;
		return pickings.size();
	}

	@Override
	public Object getItem(int position) {
		return pickings.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd");
		Picking picking = pickings.get(position);
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.pickings_entry, null);
		}
		TextView ident = (TextView) convertView.findViewById(R.id.ident);
		ident.setText(picking.getPickingId().toString());

		TextView type = (TextView) convertView.findViewById(R.id.type);
		type.setText(picking.getType());
		
		TextView date = (TextView) convertView.findViewById(R.id.date);
		Date tmpDate = new Date();
		if(picking.getCreateDate()!=null)tmpDate = new Date(picking.getCreateDate());
		date.setText(sdf.format(tmpDate));
		
		Log.d(TAG, "picking.getImgName() " + picking.getImgName());
		ImageView imgPic = (ImageView)convertView.findViewById(R.id.imgPic);
		if(picking.getImgName()!=null && !"".equals(picking.getImgName())){
			imgPic.setImageURI(Uri.fromFile(new File(ImageUtil.getMediaStorageDir() + "/" + picking.getImgName())));
		}
		else{
			imgPic.setImageResource(R.drawable.no_image);
		}
		
		convertView.setOnClickListener(this);
		convertView.setOnLongClickListener(this);
		convertView.setTag(picking);
		return convertView;
	}

	@Override
	public void onClick(View view) {
		long pickingId = ((Picking)view.getTag()).getPickingId();
		PickingEditActivity_.intent(context).pickingId(pickingId).start();
	}

	@Override
	public boolean onLongClick(View view) {
		
		selectedPickingView = view;
		
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context)
				.setIcon(android.R.drawable.ic_dialog_alert)
				.setTitle("Delete picking")
				.setMessage("Are you sure you want to delete this picking?");
		
		alertDialogBuilder.setPositiveButton("Yes", this);
		alertDialogBuilder.setNegativeButton("No", this);
		
		alertDialogBuilder.show();
		
		return true;
	}

	

	public void updateList(List<Picking> pickings) {
		this.pickings = pickings;
		notifyDataSetChanged();
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		if(which==-1){
			deletePicking();
		}
	}
	
	private void deletePicking() {
		Picking entry = (Picking) selectedPickingView.getTag();
		long pickingId = Long.parseLong(((TextView) selectedPickingView
				.findViewById(R.id.ident)).getText().toString());
		pickings.remove(entry);
		pickingHelper.logicalDelete(entry.getPickingId());
		Toast.makeText(context, "Deleted!", Toast.LENGTH_LONG)
				.show();
		notifyDataSetChanged();
	}
	
	private View selectedPickingView;
	private String TAG = "PickingAdapter";

}

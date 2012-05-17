package net.sareweb.android.onddo.adapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import net.sareweb.android.onddo.R;
import net.sareweb.android.onddo.activity.PickingEditActivity_;
import net.sareweb.android.onddo.model.Picking;
import net.sareweb.android.onddo.sqlite.PickingOpenHelper;
import android.R.integer;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class WeathersAdapter extends BaseAdapter implements OnClickListener, DialogInterface.OnClickListener {

	private List<DialogImageItem> items;
	private Context context;
	
	public WeathersAdapter(Context context){
		this.context = context;
		
		items = new ArrayList<DialogImageItem>();
		items.add(new DialogImageItem("aaa1", R.drawable.w01));
		items.add(new DialogImageItem("aaa2", R.drawable.w02));
		items.add(new DialogImageItem("aaa3", R.drawable.w03));
		items.add(new DialogImageItem("aaa3", R.drawable.w04));
		items.add(new DialogImageItem("aaa3", R.drawable.w05));
		items.add(new DialogImageItem("aaa3", R.drawable.w06));
		items.add(new DialogImageItem("aaa3", R.drawable.w07));
		items.add(new DialogImageItem("aaa3", R.drawable.w08));
		items.add(new DialogImageItem("aaa3", R.drawable.w09));
		items.add(new DialogImageItem("aaa3", R.drawable.w10));
		items.add(new DialogImageItem("aaa3", R.drawable.w11));
		items.add(new DialogImageItem("aaa3", R.drawable.w12));
		items.add(new DialogImageItem("aaa3", R.drawable.w13));
		items.add(new DialogImageItem("aaa3", R.drawable.w14));
		items.add(new DialogImageItem("aaa3", R.drawable.w15));
		items.add(new DialogImageItem("aaa3", R.drawable.w16));
		items.add(new DialogImageItem("aaa3", R.drawable.w17));
		items.add(new DialogImageItem("aaa3", R.drawable.w18));
		items.add(new DialogImageItem("aaa3", R.drawable.w19));
		items.add(new DialogImageItem("aaa3", R.drawable.w20));
	}

	@Override
	public int getCount() {
		if (items == null)
			return 0;
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		DialogImageItem item = items.get(position);
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.image_dialog_entry, null);
		}
		TextView ident = (TextView) convertView.findViewById(R.id.name);
		ident.setText(item.getName());

		ImageView image = (ImageView) convertView.findViewById(R.id.image);
		image.setImageResource(item.getImageId());
		
		convertView.setOnClickListener(this);

		return convertView;
	}

	@Override
	public void onClick(View view) {
		
	}
	

	@Override
	public void onClick(DialogInterface dialog, int which) {
		if(which==-1){
		
		}
	}
	
	
	private String TAG = "MoonsAdapter";
	
	
	

}

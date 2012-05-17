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

public class MoonsAdapter extends BaseAdapter implements OnClickListener, DialogInterface.OnClickListener {

	private List<DialogImageItem> items;
	private Context context;
	
	public MoonsAdapter(Context context){
		this.context = context;
		
		items = new ArrayList<DialogImageItem>();
		items.add(new DialogImageItem("aaa1", R.drawable.m01));
		items.add(new DialogImageItem("aaa2", R.drawable.m02));
		items.add(new DialogImageItem("aaa3", R.drawable.m03));
		items.add(new DialogImageItem("aaa3", R.drawable.m04));
		items.add(new DialogImageItem("aaa3", R.drawable.m05));
		items.add(new DialogImageItem("aaa3", R.drawable.m06));
		items.add(new DialogImageItem("aaa3", R.drawable.m07));
		items.add(new DialogImageItem("aaa3", R.drawable.m08));
		items.add(new DialogImageItem("aaa3", R.drawable.m09));
		items.add(new DialogImageItem("aaa3", R.drawable.m10));
		items.add(new DialogImageItem("aaa3", R.drawable.m11));
		items.add(new DialogImageItem("aaa3", R.drawable.m12));
		items.add(new DialogImageItem("aaa3", R.drawable.m13));
		items.add(new DialogImageItem("aaa3", R.drawable.m14));
		items.add(new DialogImageItem("aaa3", R.drawable.m15));
		items.add(new DialogImageItem("aaa3", R.drawable.m16));
		items.add(new DialogImageItem("aaa3", R.drawable.m17));
		items.add(new DialogImageItem("aaa3", R.drawable.m18));
		items.add(new DialogImageItem("aaa3", R.drawable.m19));
		items.add(new DialogImageItem("aaa3", R.drawable.m20));
		items.add(new DialogImageItem("aaa3", R.drawable.m21));
		items.add(new DialogImageItem("aaa3", R.drawable.m22));
		items.add(new DialogImageItem("aaa3", R.drawable.m23));
		items.add(new DialogImageItem("aaa3", R.drawable.m24));
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

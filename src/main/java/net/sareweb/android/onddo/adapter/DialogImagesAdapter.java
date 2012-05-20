package net.sareweb.android.onddo.adapter;

import java.util.List;

import net.sareweb.android.onddo.R;
import net.sareweb.android.onddo.dialog.DialogImageOption;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DialogImagesAdapter extends BaseAdapter{

	private List<DialogImageOption> options;
	private Context context;
	
	public DialogImagesAdapter(Context context, List<DialogImageOption> options){
		this.context = context;
		this.options=options;
	}

	@Override
	public int getCount() {
		if (options == null)
			return 0;
		return options.size();
	}

	@Override
	public Object getItem(int position) {
		return options.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		DialogImageOption item = options.get(position);
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.image_dialog_entry, null);
		}
		TextView ident = (TextView) convertView.findViewById(R.id.name);
		ident.setText(item.getTextResId());

		ImageView image = (ImageView) convertView.findViewById(R.id.image);
		image.setImageResource(item.getImgResId());

		return convertView;
	}

	
	private String TAG = "DialogImagesAdapter";
	
	
	

}

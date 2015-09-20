package com.snapsofts.picture.adapter;

import java.util.ArrayList;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.snapsofts.anh.R;
import com.snapsofts.picture.activities.ShowActivity;
import com.snapsofts.picture.libs.AnimateFirstDisplayListener;
import com.snapsofts.picture.libs.ImageLoad;
import com.snapsofts.picture.object.Picture;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AdapterGridview extends BaseAdapter {

	private ArrayList<Picture> list_picture;

	private Activity activity;
	private ImageLoader imageLoader;
	private DisplayImageOptions options;
	private ImageLoadingListener animateFirstListener = (ImageLoadingListener) new AnimateFirstDisplayListener();

	public AdapterGridview(ArrayList<Picture> list_picture, Activity activity) {
		super();
		this.list_picture = list_picture;
		this.activity = activity;
		ImageLoaderConfiguration config = ImageLoad.config(activity);
		options = ImageLoad.options();
		imageLoader = ImageLoader.getInstance();
		imageLoader.init(config);
	}

	@Override
	public int getCount() {
		return list_picture.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = activity.getLayoutInflater().inflate(
					R.layout.item_gridview, null);
		}
		ImageView iv = (ImageView) convertView.findViewById(R.id.iv_anh);
		TextView tv = (TextView) convertView.findViewById(R.id.tv);

		String url = list_picture.get(position).getPath();
		imageLoader.displayImage(url, iv, options, animateFirstListener);
		tv.setText(list_picture.get(position).getLuotTai() + "");
		convertView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent=new Intent(activity, ShowActivity.class);
				ShowActivity.list_picture=list_picture;
				ShowActivity.vitri=position;
				activity.startActivity(intent);
			}
		});
		return convertView;
	}

}

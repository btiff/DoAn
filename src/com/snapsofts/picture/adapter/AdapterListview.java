package com.snapsofts.picture.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.sax.StartElementListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.snapsofts.anh.R;
import com.snapsofts.picture.activities.MainActivity;
import com.snapsofts.picture.activities.ShowActivity;
import com.snapsofts.picture.libs.AnimateFirstDisplayListener;
import com.snapsofts.picture.libs.ImageLoad;
import com.snapsofts.picture.object.Picture;

public class AdapterListview extends BaseAdapter {

	private ArrayList<Picture> list_picture;
	private Activity activity;
	private ImageLoader imageLoader;
	private DisplayImageOptions options;
	private ImageLoadingListener animateFirstListener = (ImageLoadingListener) new AnimateFirstDisplayListener();
	private int a[];
	private static int b;

	public AdapterListview(ArrayList<Picture> list_picture, Activity activity) {
		super();
		this.list_picture = list_picture;
		this.activity = activity;
		ImageLoaderConfiguration config = ImageLoad.config(activity);
		options = ImageLoad.options();
		imageLoader = ImageLoader.getInstance();
		imageLoader.init(config);
		a = new int[list_picture.size()];
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
					R.layout.item_listview, null);
		}
		ImageView iv_anh = (ImageView) convertView
				.findViewById(R.id.iv_list_anh);
		TextView tv_chude = (TextView) convertView.findViewById(R.id.tv_chude);
		TextView tv_gioithieu = (TextView) convertView
				.findViewById(R.id.tv_gioithieu);
		TextView tv = (TextView) convertView.findViewById(R.id.tv_listtai);
		TextView tv_time = (TextView) convertView.findViewById(R.id.tv_time);

		String url = list_picture.get(position).getPath();
		imageLoader.displayImage(url, iv_anh, options, animateFirstListener);

		tv_chude.setText(list_picture.get(position).getNamePictures());
		tv_gioithieu.setText(list_picture.get(position).getInformation());
		tv.setText(list_picture.get(position).getLuotTai() + "");
		tv_time.setText(list_picture.get(position).getDay() + "/"
				+ list_picture.get(position).getMonth() + "/"
				+ list_picture.get(position).getYears());
		convertView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(activity, ShowActivity.class);
				ShowActivity.list_picture = list_picture;
				ShowActivity.vitri = position;
				activity.startActivity(intent);
			}
		});
		if (a[position] == 0) {
			Animation a1 = AnimationUtils.loadAnimation(activity,
					R.anim.animate_itemlistview);
			convertView.startAnimation(a1);
			a[position] = 1;
		}
		return convertView;
	}

}

package com.snapsofts.picture.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.snapsofts.picture.activities.ShowActivity;
import com.snapsofts.picture.libs.AnimateFirstDisplayListener;
import com.snapsofts.picture.libs.ImageLoad;
import com.snapsofts.picture.object.Picture;

public class ShowAdapter extends PagerAdapter {

	private Activity activity;
	private ArrayList<Picture> list_picture;
	private ImageLoader imageLoader;
	private DisplayImageOptions options;
	private ImageLoadingListener animateFirstListener = (ImageLoadingListener) new AnimateFirstDisplayListener();
	private static Bitmap bmp;

	public ShowAdapter(Activity activity, ArrayList<Picture> list_picture) {
		super();
		this.activity = activity;
		this.list_picture = list_picture;
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
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

	@Override
	public Object instantiateItem(ViewGroup container, final int position) {
		final ImageView img = new ImageView(activity);
		img.setScaleType(ImageView.ScaleType.FIT_XY);
		String url =list_picture.get(position).getPath();
		imageLoader.displayImage(url,img, options,animateFirstListener);
		img.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				((ShowActivity) activity).anim();
			}
		});
		container.addView(img);
		return img;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
	}
}

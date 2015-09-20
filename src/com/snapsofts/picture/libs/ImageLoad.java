package com.snapsofts.picture.libs;

import android.app.Activity;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.snapsofts.anh.R;

public class ImageLoad {

	public static ImageLoaderConfiguration config(Activity activity){
		ImageLoaderConfiguration config1 = new ImageLoaderConfiguration.Builder(
				activity).threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				.tasksProcessingOrder(QueueProcessingType.LIFO).build();
		return config1;
	}
	public static DisplayImageOptions options(){
		DisplayImageOptions options1 = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.ic_launcher)
		.showImageForEmptyUri(R.drawable.ic_launcher)
		.showImageOnFail(R.drawable.ic_launcher).cacheInMemory(true)
		.cacheOnDisk(true)
		.build();
		return options1;
	}
//	.displayer(new RoundedBitmapDisplayer(20))
}

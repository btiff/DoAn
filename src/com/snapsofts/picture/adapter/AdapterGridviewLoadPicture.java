package com.snapsofts.picture.adapter;

import java.util.ArrayList;

import com.snapsofts.anh.R;
import com.snapsofts.picture.object.Picture;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class AdapterGridviewLoadPicture extends BaseAdapter{

	private ArrayList<Picture>list_picture;
	private Activity activity;
	public AdapterGridviewLoadPicture(ArrayList<Picture> list_picture,
			Activity activity) {
		super();
		this.list_picture = list_picture;
		this.activity = activity;
	}

	public AdapterGridviewLoadPicture() {
		super();
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
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView==null){
			convertView=activity.getLayoutInflater().inflate(R.layout.item_gridview,null);
		}
		
		return null;
	}

}

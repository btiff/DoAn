package com.snapsofts.picture.adapter;

import java.util.ArrayList;

import com.snapsofts.anh.R;
import com.snapsofts.picture.activities.MainActivity;
import com.snapsofts.picture.object.Category;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AdapterListviewMenu extends BaseAdapter{

	private ArrayList<Category>listCategories;
	private Activity activity;
	
	public AdapterListviewMenu() {
		super();
	}

	public AdapterListviewMenu(ArrayList<Category>listCategories, Activity activity) {
		super();
		this.listCategories = listCategories;
		this.activity = activity;
	}

	@Override
	public int getCount() {
		return listCategories.size();
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
		if(convertView==null){
			convertView=activity.getLayoutInflater().inflate(R.layout.item_listmenu, null);
		}
		TextView tv=(TextView)convertView.findViewById(R.id.tv_itemlistmenu);
		tv.setText(listCategories.get(position).getNameCategory());
		convertView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				((MainActivity) activity).setIntCategory(listCategories.get(position).getCategoryId());
			}
		});
		return convertView;
	}

}

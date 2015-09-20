package com.snapsofts.picture.fragment;

import java.util.ArrayList;
import java.util.StringTokenizer;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;
import com.snapsofts.anh.R;
import com.snapsofts.picture.activities.MainActivity;
import com.snapsofts.picture.adapter.AdapterGridview;
import com.snapsofts.picture.adapter.AdapterListview;
import com.snapsofts.picture.libs.Day;
import com.snapsofts.picture.libs.InternetConnect;
import com.snapsofts.picture.object.Picture;

public class FragmentFit extends Fragment{

	private ArrayList<Picture> list_picture;
	private ArrayList<Picture>list;
	private ListView listView;
	private GridView gridView;
	private int width;
	private int height;
	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View layout = inflater.inflate(R.layout.fragment_fit, container,
				false);
		listView = (ListView) layout.findViewById(R.id.list_fragment_fit);
		gridView = (GridView) layout.findViewById(R.id.grid_fragment_fit);
		Display display = getActivity().getWindowManager().getDefaultDisplay();
		width = display.getWidth();
		height = display.getHeight();
		chuyen();
		return layout;
	}


	public void hotest() {
		ArrayList<Picture> list = new ArrayList<Picture>();
		Log.e("123", list_picture.size() + "");
		for (int i = 0; i < list_picture.size(); i++) {
			if (list_picture.get(i).getLuotTai() > 10) {
				list.add(list_picture.get(i));
			}
		}
		if (MainActivity.trangThai == true) {
			gridView.setVisibility(View.INVISIBLE);
			listView.setVisibility(View.VISIBLE);
			AdapterListview adapter_listview = new AdapterListview(list,
					getActivity());
			listView.setAdapter(adapter_listview);
		} else {
			gridView.setVisibility(View.VISIBLE);
			listView.setVisibility(View.INVISIBLE);
			AdapterGridview adapter_gridview = new AdapterGridview(list,
					getActivity());
			gridView.setAdapter(adapter_gridview);
		}
	}

	public void newest(){
		String day="10",month="6";
		ArrayList<Picture>list=new ArrayList<Picture>();
		for(int i=0;i<list_picture.size();i++){
			if(Integer.parseInt(list_picture.get(i).getMonth())>Integer.parseInt(month)&&Integer.parseInt(list_picture.get(i).getDay())>Integer.parseInt(day)){
				list.add(list_picture.get(i));
			}
		}
		if (MainActivity.trangThai == true) {
			gridView.setVisibility(View.INVISIBLE);
			listView.setVisibility(View.VISIBLE);
			AdapterListview adapter_listview = new AdapterListview(list,
					getActivity());
			listView.setAdapter(adapter_listview);
		}else{
			gridView.setVisibility(View.VISIBLE);
			listView.setVisibility(View.INVISIBLE);
			AdapterGridview adapter_gridview=new AdapterGridview(list, getActivity());
			gridView.setAdapter(adapter_gridview);
		}
	}

	public void chuyen() {
		list= MainActivity.db.getDataWidthHeight(width+"",height+"");
		list_picture=list;
		if (list.size() != 0) {
			list_picture = list;
			if (MainActivity.intCategory != 0) {
				for (int i = 0; i < list_picture.size(); i++) {
					if (kiemtra(list_picture.get(i)) == 0) {
						list_picture.remove(i);
						i--;
					}
				}
			}
			if (MainActivity.neworhot == true) {
				newest();
			} else {
				hotest();
			}
		}
	}

	private int kiemtra(Picture p) {
		String a = p.getCategoryId();
		String c = "";
		StringTokenizer b = new StringTokenizer(a);
		while (b.hasMoreTokens()) {
			c = b.nextToken();
			if (c.contains("2"))
				Log.e("", p.getNamePictures());
			if (MainActivity.intCategory == Integer.parseInt(c))
				return 1;
		}
		return 0;
	}
}

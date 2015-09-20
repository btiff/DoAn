package com.snapsofts.picture.activities;

import java.util.ArrayList;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NotificationCompat.CarExtender;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;
import com.snapsofts.anh.R;
import com.snapsofts.picture.adapter.AdapterListviewMenu;
import com.snapsofts.picture.data.DataBase;
import com.snapsofts.picture.fragment.FragmentAll;
import com.snapsofts.picture.fragment.FragmentDownload;
import com.snapsofts.picture.fragment.FragmentFit;
import com.snapsofts.picture.fragment.FragmentFitHeight;
import com.snapsofts.picture.fragment.FragmentFitWidth;
import com.snapsofts.picture.libs.Day;
import com.snapsofts.picture.libs.InternetConnect;
import com.snapsofts.picture.object.Category;
import com.snapsofts.picture.object.Picture;

public class MainActivity extends FragmentActivity {

	private PagerSlidingTabStrip tabs;
	private ViewPager pager;
	private MyPagerAdapter adapter;
	public static boolean trangThai = true;
	public static boolean neworhot = true,status_menu=true;
	public static int intCategory;
	public static int vitri;
	private Button btn_list, btn_grid, btn_newest, btn_hotest;
	private ListView list_menu;
	private ImageView iv_menu;
	private DrawerLayout drawerLayout;
	private FragmentAll all;
	private FragmentDownload download;
	private FragmentFit fit;
	private FragmentFitHeight fit_height;
	private FragmentFitWidth fit_width;
	public static DataBase db;
	private ArrayList<Category>listCategories;

	// hàm khởi tạo;
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		neworhot=true;
		trangThai=true;
		intCategory=1;
		db = new DataBase(this);
		tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
		pager = (ViewPager) findViewById(R.id.pager);
		btn_list = (Button) findViewById(R.id.btn_list);
		btn_grid = (Button) findViewById(R.id.btn_grid);
		btn_newest = (Button) findViewById(R.id.btn_newest);
		btn_hotest = (Button) findViewById(R.id.btn_hotest);
		iv_menu = (ImageView) findViewById(R.id.iv_menu);
		drawerLayout = (DrawerLayout) findViewById(R.id.DrawerLayout);
		list_menu=(ListView)findViewById(R.id.list_menu);
		drawerLayout
		.setDrawerLockMode(drawerLayout.LOCK_MODE_LOCKED_CLOSED);
		if (InternetConnect.isNetworkConnected(this) == false) {
			adapter = new MyPagerAdapter(getSupportFragmentManager());
			pager.setAdapter(adapter);
			tabs.setViewPager(pager);
		} else {
			db.delete();
			getAll();
			setCategories();
		}
		btn_hotest.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				neworhot = false;
				int a = pager.getCurrentItem();
				kiemtra(a);
			}
		});
		btn_newest.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				neworhot = true;
				int a = pager.getCurrentItem();
				kiemtra(a);
			}
		});
		btn_list.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				trangThai = true;
				int a = pager.getCurrentItem();
				kiemtra(a);
			}
		});
		btn_grid.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				trangThai = false;
				int a = pager.getCurrentItem();
				kiemtra(a);
			}
		});
		iv_menu.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				menu();
			}
		});

	}

	public void kiemtra(int a) {
		switch (a) {
		case 0:
			all.chuyen();
			fit_height.chuyen();
			break;
		case 1:
			all.chuyen();
			fit_height.chuyen();
			fit_width.chuyen();
			break;
		case 2:
			fit_height.chuyen();
			fit_width.chuyen();
			fit.chuyen();
			break;
		case 3:
			fit_width.chuyen();
			fit.chuyen();
			download.chuyen();
			break;
		case 4:
			fit.chuyen();
			download.chuyen();
			break;
		}
	}

	// set adapter cho viewpager;
	public class MyPagerAdapter extends FragmentPagerAdapter {

		private final String[] TITLES = { "      all      ", "fit_height",
				"fit_width", "        fit        ", "download" };

		public MyPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return TITLES[position];
		}

		@Override
		public int getCount() {
			return TITLES.length;
		}

		@Override
		public Fragment getItem(int position) {
			if (position == 0) {
				all = new FragmentAll();
				return all;
			} else {
				if (position == 1) {
					fit_height = new FragmentFitHeight();
					return fit_height;
				} else {
					if (position == 2) {
						fit_width = new FragmentFitWidth();
						return fit_width;
					} else {
						if (position == 3) {
							fit = new FragmentFit();
							return fit;
						} else {
							download = new FragmentDownload();
							return download;
						}
					}
				}

			}
		}
	}

	public void getAll() {
		AsyncHttpClient client = new AsyncHttpClient();
		JSONObject object = new JSONObject();
		RequestParams params = new RequestParams();
		params.put("registerId", 2);
		params.put("type", 0);
		client.get(
				"http://mobilewalls-snapsofts.rhcloud.com/mobilewalls/api/list-walls",
				params, new TextHttpResponseHandler() {
					@Override
					public void onFinish() {
						super.onFinish();
						// Log.e("hoang anh tuan ","hoang anh tuan");
						adapter = new MyPagerAdapter(
								getSupportFragmentManager());
						pager.setAdapter(adapter);
						tabs.setViewPager(pager);
					}

					@Override
					public void onFailure(int arg0, Header[] arg1, String arg2,
							Throwable arg3) {
						Log.e("123", arg3 + "");
					}

					@Override
					public void onSuccess(int arg0, Header[] arg1, String arg2) {
						try {
							JSONObject object = new JSONObject(arg2);
							JSONArray array = new JSONArray();
							array = object.getJSONArray("data");
							for (int i = 0; i < array.length(); i++) {
								JSONObject ob = new JSONObject();
								ob = array.getJSONObject(i);
								Picture p = new Picture();
								p.setInformation("");
								p.setNamePictures(ob.getString("title"));
								p.setPath(ob.getString("photoUrl"));
								p.setVitri(i);
								p.setDay(Day.day(ob.getLong("createdTime")));
								p.setMonth(Day.month(ob.getLong("createdTime")));
								p.setYears(Day.year(ob.getLong("createdTime")));
								p.setTime(Day.time(ob.getLong("createdTime")));
								p.setLuotTai(ob.getInt("downloadCount"));
								p.setChieucao(ob.getString("height"));
								p.setChieurong(ob.getString("width"));
								JSONArray a=ob.getJSONArray("categories");
								String c="";
								for(int j=0;j<a.length();j++){
									JSONObject b=a.getJSONObject(j);
									c=c+b.getInt("categoryId")+" ";
									
								}
								p.setCategoryId(c);
								db.createData(p);
							}
							
						} catch (JSONException e) {
							e.printStackTrace();
							Log.e("hoang anh tuan ", e.getMessage().toString());
						}
					}

				});
	}
	private void menu(){
		Animation a=AnimationUtils.loadAnimation(this,R.anim.animatemenu);
		iv_menu.startAnimation(a);
		if (!drawerLayout.isDrawerOpen(GravityCompat.START)) {
			new Handler().postDelayed(new Runnable(){
	            @Override
	            public void run() {
	            	iv_menu.setImageResource(R.drawable.ic_action_back);
	            	Animation a1=AnimationUtils.loadAnimation(MainActivity.this,R.anim.animatemenu1);
	        		iv_menu.startAnimation(a1);
	            }
	        }, 500);
			status_menu = true;
			drawerLayout
					.setDrawerLockMode(drawerLayout.LOCK_MODE_LOCKED_OPEN);
		} else {
			new Handler().postDelayed(new Runnable(){
	            @Override
	            public void run() {
	            	iv_menu.setImageResource(R.drawable.ic_menu);
	            	Animation a1=AnimationUtils.loadAnimation(MainActivity.this,R.anim.animatemenu1);
	        		iv_menu.startAnimation(a1);
	            }
	        }, 500);
			status_menu = false;
			drawerLayout
					.setDrawerLockMode(drawerLayout.LOCK_MODE_LOCKED_CLOSED);
		}
	}
	private void setCategories(){
		listCategories=new ArrayList<Category>();
		listCategories.add(new Category(0, "All"));
		AsyncHttpClient client=new AsyncHttpClient();
		client.get("http://mobilewalls-snapsofts.rhcloud.com/mobilewalls/api/list-categories", new TextHttpResponseHandler() {
			
			@Override
			public void onFinish() {
				super.onFinish();
				AdapterListviewMenu a=new AdapterListviewMenu(listCategories, MainActivity.this);
				list_menu.setAdapter(a);
				
			}
			@Override
			public void onSuccess(int arg0, Header[] arg1, String arg2) {
				try {
					JSONObject ob=new JSONObject(arg2);
					JSONArray ja=ob.getJSONArray("data");
					for(int i=0;i<ja.length();i++){
						Category a=new Category();
						JSONObject b=ja.getJSONObject(i);
						a.setCategoryId(b.getInt("categoryId"));
						a.setNameCategory(b.getString("name"));
						listCategories.add(a);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
			}
			
			@Override
			public void onFailure(int arg0, Header[] arg1, String arg2, Throwable arg3) {
				
			}
		});
	}
	public void setIntCategory(int a){
		intCategory=a;
		iv_menu.setImageResource(R.drawable.ic_menu);
		status_menu = false;
		drawerLayout
				.setDrawerLockMode(drawerLayout.LOCK_MODE_LOCKED_CLOSED);
		int b = pager.getCurrentItem();
		kiemtra(b);
	}

}

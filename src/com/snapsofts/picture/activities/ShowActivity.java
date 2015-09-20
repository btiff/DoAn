package com.snapsofts.picture.activities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.apache.http.Header;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DownloadManager;
import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;
import com.snapsofts.anh.R;
import com.snapsofts.picture.adapter.ShowAdapter;
import com.snapsofts.picture.libs.InternetConnect;
import com.snapsofts.picture.object.Picture;

public class ShowActivity extends Activity {

	private ViewPager viewpager;
	public static ArrayList<Picture> list_picture;
	public static int vitri;
	private TextView tv_hienthi_tenanh, tv_soluotdowloaded;
	private RelativeLayout rl_show_thoat, rl_thoat, rl_show_download,btn_hienthi_dowloaded;
	public Bitmap bitmaptwo;
	public static String path = "";
	private boolean trangthai = false;

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_showpicture);
		viewpager = (ViewPager) findViewById(R.id.viewpager_anh);

		rl_thoat = (RelativeLayout) findViewById(R.id.rl_thoat);
		rl_show_download = (RelativeLayout) findViewById(R.id.rl_show_download);
		rl_show_thoat = (RelativeLayout) findViewById(R.id.rl_show_thoat);

		btn_hienthi_dowloaded = (RelativeLayout) findViewById(R.id.btn_hienthi_dowloaded);
		tv_hienthi_tenanh = (TextView) findViewById(R.id.tv_hienthi_tenanh);
		tv_soluotdowloaded = (TextView) findViewById(R.id.tv_soluotdowloaded);

		ShowAdapter adapter = new ShowAdapter(this, list_picture);
		viewpager.setAdapter(adapter);
		viewpager.setCurrentItem(vitri);
		tv_hienthi_tenanh.setText(list_picture.get(vitri).getNamePictures()
				+ "");
		tv_soluotdowloaded.setText(list_picture.get(vitri).getLuotTai()
				+ " Dowloaded");
		viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(final int arg0) {
				tv_hienthi_tenanh.setText(list_picture.get(arg0)
						.getNamePictures() + "");
				tv_soluotdowloaded.setText(list_picture.get(arg0).getLuotTai()
						+ " Dowloaded");
				anim1();
			}

			@Override
			public void onPageScrolled(final int arg0, final float arg1,
					final int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(final int arg0) {
			}
		});

		rl_thoat.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});

		btn_hienthi_dowloaded.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				AsyncHttpClient client = new AsyncHttpClient();
				RequestParams params = new RequestParams();
				params.put("photoId", list_picture.get(vitri).getVitri() + 2);
				client.post(
						ShowActivity.this,
						"http://mobilewalls-snapsofts.rhcloud.com/mobilewalls/api/update-photo-download",
						params, new TextHttpResponseHandler() {

							@Override
							public void onFailure(int arg0, Header[] arg1,
									String arg2, Throwable arg3) {
							}

							@Override
							public void onSuccess(int arg0, Header[] arg1,
									String arg2) {
							}

						});

				new AsyncTask<Void, Void, Void>() {

					@Override
					protected void onPreExecute() {
						super.onPreExecute();
					}

					@Override
					protected Void doInBackground(Void... params) {
						return null;
					}

					@Override
					protected void onPostExecute(Void result) {
						super.onPostExecute(result);
						final int a = viewpager.getCurrentItem();

						if (InternetConnect
								.isNetworkConnected(ShowActivity.this) == false) {
							Toast.makeText(
									ShowActivity.this,
									"you cannot download picture beacause you donnot connect internet !",
									Toast.LENGTH_LONG).show();
						} else {
							file_download(list_picture.get(a));
							new BackgroundTask().execute(list_picture.get(a)
									.getPath());
						}
					}
				}.execute();
			}
		});
	}

	@SuppressLint("NewApi")
	public void file_download(Picture a) {
		File direct = new File(Environment.getExternalStorageDirectory()
				+ "/hoanganhtuan");

		if (!direct.exists()) {
			direct.mkdirs();
		}

		DownloadManager mgr = (DownloadManager) this
				.getSystemService(Context.DOWNLOAD_SERVICE);

		Uri downloadUri = Uri.parse(a.getPath());
		DownloadManager.Request request = new DownloadManager.Request(
				downloadUri);

		request.setAllowedNetworkTypes(
				DownloadManager.Request.NETWORK_WIFI
						| DownloadManager.Request.NETWORK_MOBILE)
				.setAllowedOverRoaming(false)
				.setTitle("Demo")
				.setDescription("Something useful. No, really.")
				.setDestinationInExternalPublicDir("/hoanganhtuan",
						a.getNamePictures() + ".jpg");
		mgr.enqueue(request);
		Toast.makeText(ShowActivity.this, "lưu thành công", Toast.LENGTH_SHORT)
				.show();
	}

	private class BackgroundTask extends AsyncTask<String, Void, Bitmap> {
		protected Bitmap doInBackground(String... url) {
			// --- download an image ---
			Bitmap bitmap = DownloadImage(url[0]);
			return bitmap;
		}

		protected void onPostExecute(Bitmap bitmap) {
			bitmaptwo = bitmap;
			WallpaperManager wManager;

			try {
				wManager = WallpaperManager
						.getInstance(getApplicationContext());
				wManager.setBitmap(bitmaptwo);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private InputStream OpenHttpConnection(String urlString) throws IOException {
		InputStream in = null;
		int response = -1;
		URL url = new URL(urlString);
		URLConnection conn = url.openConnection();
		if (!(conn instanceof HttpURLConnection))
			throw new IOException("Not an HTTP connection");
		try {
			HttpURLConnection httpConn = (HttpURLConnection) conn;
			httpConn.setAllowUserInteraction(false);
			httpConn.setInstanceFollowRedirects(true);
			httpConn.setRequestMethod("GET");
			httpConn.connect();
			response = httpConn.getResponseCode();
			if (response == HttpURLConnection.HTTP_OK) {
				in = httpConn.getInputStream();
			}
		} catch (Exception ex) {
			throw new IOException("Error connecting");
		}

		return in;
	}

	private Bitmap DownloadImage(String URL) {
		Bitmap bitmap = null;
		InputStream in = null;
		try {
			in = OpenHttpConnection(URL);
			bitmap = BitmapFactory.decodeStream(in);
			in.close();
		} catch (Exception e1) {
			Toast.makeText(this, e1.getLocalizedMessage(), Toast.LENGTH_LONG)
					.show();

			e1.printStackTrace();
		}
		return bitmap;
	}

	public void anim() {
		if (trangthai == false) {
			trangthai=true;
			Animation a = AnimationUtils.loadAnimation(this, R.anim.move1);
			Animation a1 = AnimationUtils.loadAnimation(this, R.anim.move2);
			rl_show_thoat.startAnimation(a1);
			rl_show_download.startAnimation(a);
			rl_show_download.setVisibility(View.INVISIBLE);
			rl_show_thoat.setVisibility(View.INVISIBLE);
		}
		else{
			rl_show_download.setVisibility(View.VISIBLE);
			rl_show_thoat.setVisibility(View.VISIBLE);
			trangthai=false;
			Animation a = AnimationUtils.loadAnimation(this, R.anim.move3);
			Animation a1 = AnimationUtils.loadAnimation(this, R.anim.move4);
			rl_show_thoat.startAnimation(a1);
			rl_show_download.startAnimation(a);
			
		}
	}
	public void anim1() {
		if(trangthai==true){
			trangthai=false;
			rl_show_download.setVisibility(View.VISIBLE);
			rl_show_thoat.setVisibility(View.VISIBLE);
			Animation a = AnimationUtils.loadAnimation(this, R.anim.move4);
			Animation a1 = AnimationUtils.loadAnimation(this, R.anim.move3);
			rl_show_thoat.startAnimation(a);
			rl_show_download.startAnimation(a1);
		}
	}
	
}

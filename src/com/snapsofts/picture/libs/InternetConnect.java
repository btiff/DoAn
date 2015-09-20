package com.snapsofts.picture.libs;

import java.net.InetAddress;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class InternetConnect {
	public static boolean isInternetAvailable() {
		try {
			InetAddress ipAddr = InetAddress.getByName("google.com");
			Log.e("123","hoang anh tuan");
			if (ipAddr.equals("")) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean isNetworkConnected(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni = cm.getActiveNetworkInfo();
		if (ni == null) {
			return false;
		} else
			return true;
	}
}

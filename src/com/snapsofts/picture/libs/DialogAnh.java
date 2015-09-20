package com.snapsofts.picture.libs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.snapsofts.anh.R;
import com.snapsofts.picture.activities.MainActivity;

public class DialogAnh {
	public static void showDialog(Context context) {
		final Dialog dialog = new Dialog(context);
		dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		dialog.getWindow().setBackgroundDrawable(
				new ColorDrawable(android.graphics.Color.TRANSPARENT));
		View dia = ((MainActivity) context).getLayoutInflater().inflate(
				R.layout.dialog, null);
		Button btn_ok = (Button) dia.findViewById(R.id.btn_ok);
		btn_ok.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.cancel();
			}
		});
		dialog.setContentView(dia);
		dialog.show();
	}
}

package com.snapsofts.picture.data;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.snapsofts.picture.object.Picture;

public class DataBase extends SQLiteOpenHelper {

	private static final String NAMEDATA = "namedata";
	private static final String NAMETABLE = "listmuzic";
	private static final String COLUMN_ID = "id";
	private static final String COLUMN_PATH = "path";
	private static final String COLUMN_NAME = "name";
	private static final String COLUMN_LUOTTAI = "luoitai";
	private static final String COLUMN_TIME = "time";
	private static final String COLUMN_DAY = "day";
	private static final String COLUMN_MONTH = "month";
	private static final String COLUMN_HEIGHT = "chieucao";
	private static final String COLUMN_WIDTH = "chieurong";
	private static final String COLUMN_YEARS = "year";
	private static final String COLUMN_CATEGORY = "category";
	private Context context;

	public DataBase(Context context) {
		super(context, NAMEDATA, null, 1);
		this.context = context;
	}

	// thêm đối tượng;
	public void createData(Picture p) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(COLUMN_NAME, p.getNamePictures());
		values.put(COLUMN_PATH, p.getPath());
		values.put(COLUMN_LUOTTAI, p.getLuotTai() + "");
		values.put(COLUMN_TIME, p.getTime() + "");
		values.put(COLUMN_DAY, p.getDay() + "");
		values.put(COLUMN_MONTH, p.getMonth() + "");
		values.put(COLUMN_YEARS, p.getYears() + "");
		values.put(COLUMN_HEIGHT, p.getChieucao() + "");
		values.put(COLUMN_WIDTH, p.getChieurong() + "");
		values.put(COLUMN_CATEGORY, p.getCategoryId() + "");
		if (db.insert(NAMETABLE, null, values) != -1) {
		} else {
		}
	}

	public void delete() {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(NAMETABLE, null, null);
	}

	// lấy danh sách ảnh;
	public ArrayList<Picture> getData() {

		ArrayList<Picture> list = new ArrayList<Picture>();
		SQLiteDatabase db = this.getWritableDatabase();
		String sql = "select * from " + NAMETABLE;
		Cursor cursor = db.rawQuery(sql, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Picture p = new Picture();
			p.setLuotTai(Integer.parseInt(cursor.getString(cursor
					.getColumnIndex(COLUMN_LUOTTAI))));
			p.setNamePictures(cursor.getString(cursor
					.getColumnIndex(COLUMN_NAME)));
			p.setPath(cursor.getString(cursor.getColumnIndex(COLUMN_PATH)));
			p.setDay(cursor.getString(cursor.getColumnIndex(COLUMN_DAY)));
			p.setMonth(cursor.getString(cursor.getColumnIndex(COLUMN_MONTH)));
			p.setYears(cursor.getString(cursor.getColumnIndex(COLUMN_YEARS)));
			p.setTime(cursor.getString(cursor.getColumnIndex(COLUMN_TIME)));
			p.setChieucao(cursor.getString(cursor.getColumnIndex(COLUMN_HEIGHT)));
			p.setChieurong(cursor.getString(cursor.getColumnIndex(COLUMN_WIDTH)));
			p.setCategoryId(cursor.getString(cursor
					.getColumnIndex(COLUMN_CATEGORY)));
			list.add(p);
			cursor.moveToNext();
		}
		return list;
	}

	public ArrayList<Picture> getDataHeght(String b) {

		ArrayList<Picture> list = new ArrayList<Picture>();
		SQLiteDatabase db = this.getWritableDatabase();
		String sql = "select * from " + NAMETABLE;
		Cursor cursor = db.rawQuery(sql, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			String a = cursor.getString(cursor.getColumnIndex(COLUMN_HEIGHT));
			if (b.compareTo(a) == 0) {

				Picture p = new Picture();
				p.setLuotTai(Integer.parseInt(cursor.getString(cursor
						.getColumnIndex(COLUMN_LUOTTAI))));
				p.setNamePictures(cursor.getString(cursor
						.getColumnIndex(COLUMN_NAME)));
				p.setPath(cursor.getString(cursor.getColumnIndex(COLUMN_PATH)));
				p.setDay(cursor.getString(cursor.getColumnIndex(COLUMN_DAY)));
				p.setMonth(cursor.getString(cursor.getColumnIndex(COLUMN_MONTH)));
				p.setYears(cursor.getString(cursor.getColumnIndex(COLUMN_YEARS)));
				p.setTime(cursor.getString(cursor.getColumnIndex(COLUMN_TIME)));
				p.setChieucao(cursor.getString(cursor
						.getColumnIndex(COLUMN_HEIGHT)));
				p.setChieurong(cursor.getString(cursor
						.getColumnIndex(COLUMN_WIDTH)));
				p.setCategoryId(cursor.getString(cursor
						.getColumnIndex(COLUMN_CATEGORY)));
				list.add(p);

			}
			cursor.moveToNext();
		}
		return list;
	}

	public ArrayList<Picture> getDataWidth(String b) {

		ArrayList<Picture> list = new ArrayList<Picture>();
		SQLiteDatabase db = this.getWritableDatabase();
		String sql = "select * from " + NAMETABLE;
		Cursor cursor = db.rawQuery(sql, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			String a = cursor.getString(cursor.getColumnIndex(COLUMN_WIDTH));
			if (b.compareTo(a) == 0) {

				Picture p = new Picture();
				p.setLuotTai(Integer.parseInt(cursor.getString(cursor
						.getColumnIndex(COLUMN_LUOTTAI))));
				p.setNamePictures(cursor.getString(cursor
						.getColumnIndex(COLUMN_NAME)));
				p.setPath(cursor.getString(cursor.getColumnIndex(COLUMN_PATH)));
				p.setDay(cursor.getString(cursor.getColumnIndex(COLUMN_DAY)));
				p.setMonth(cursor.getString(cursor.getColumnIndex(COLUMN_MONTH)));
				p.setYears(cursor.getString(cursor.getColumnIndex(COLUMN_YEARS)));
				p.setTime(cursor.getString(cursor.getColumnIndex(COLUMN_TIME)));
				p.setChieucao(cursor.getString(cursor
						.getColumnIndex(COLUMN_HEIGHT)));
				p.setChieurong(cursor.getString(cursor
						.getColumnIndex(COLUMN_WIDTH)));
				p.setCategoryId(cursor.getString(cursor
						.getColumnIndex(COLUMN_CATEGORY)));
				list.add(p);

			}
			cursor.moveToNext();
		}
		return list;
	}

	public ArrayList<Picture> getDataWidthHeight(String b, String d) {

		ArrayList<Picture> list = new ArrayList<Picture>();
		SQLiteDatabase db = this.getWritableDatabase();
		String sql = "select * from " + NAMETABLE;
		Cursor cursor = db.rawQuery(sql, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			String a = cursor.getString(cursor.getColumnIndex(COLUMN_WIDTH));
			String c = cursor.getString(cursor.getColumnIndex(COLUMN_HEIGHT));
			if (b.compareTo(a) == 0 && d.compareTo(c) == 0) {

				Picture p = new Picture();
				p.setLuotTai(Integer.parseInt(cursor.getString(cursor
						.getColumnIndex(COLUMN_LUOTTAI))));
				p.setNamePictures(cursor.getString(cursor
						.getColumnIndex(COLUMN_NAME)));
				p.setPath(cursor.getString(cursor.getColumnIndex(COLUMN_PATH)));
				p.setDay(cursor.getString(cursor.getColumnIndex(COLUMN_DAY)));
				p.setMonth(cursor.getString(cursor.getColumnIndex(COLUMN_MONTH)));
				p.setYears(cursor.getString(cursor.getColumnIndex(COLUMN_YEARS)));
				p.setTime(cursor.getString(cursor.getColumnIndex(COLUMN_TIME)));
				p.setChieucao(cursor.getString(cursor
						.getColumnIndex(COLUMN_HEIGHT)));
				p.setChieurong(cursor.getString(cursor
						.getColumnIndex(COLUMN_WIDTH)));
				p.setChieurong(cursor.getString(cursor
						.getColumnIndex(COLUMN_CATEGORY)));
				list.add(p);

			}
			cursor.moveToNext();
		}
		return list;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "create table " + NAMETABLE + " ( " + COLUMN_ID
				+ " Integer primary key autoincrement, " + COLUMN_PATH
				+ " text, " + COLUMN_NAME + " text, " + COLUMN_LUOTTAI
				+ " text, " + COLUMN_DAY + " text, " + COLUMN_YEARS + " text, "
				+ COLUMN_MONTH + " text, " + COLUMN_HEIGHT + " text, "
				+ COLUMN_WIDTH + " text, " + COLUMN_CATEGORY + " text, "
				+ COLUMN_TIME + " text " + ")";

		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("drop table if exists" + NAMETABLE);
		onCreate(db);
	}

}

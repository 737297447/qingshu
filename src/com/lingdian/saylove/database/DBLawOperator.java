package com.lingdian.saylove.database;

import java.io.ByteArrayOutputStream;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public class DBLawOperator {

	public void insert(SQLiteDatabase db, String table, String phone,
			String content, String single) {

		db.execSQL("insert into " + table
				+ "(phone, content,single) values(?,?,?)", new String[] {
				phone, content, single });
	}

	public void update(SQLiteDatabase db, String table, String phone,
			String content) {

		db.execSQL("update " + table
				+ " set phone=?,content=? where single='Gril'", new String[] {
				phone, content });
	}

	public void deleteALLData(SQLiteDatabase db) {
		db.beginTransaction();
		db.execSQL("delete from Mms");
		db.setTransactionSuccessful();
		db.endTransaction();
	}

}

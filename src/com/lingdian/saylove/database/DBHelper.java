package com.lingdian.saylove.database;

import java.io.File;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * 
 * @author Administrator
 * 
 */
public class DBHelper {

	/*
	 * 定义数据库基本信息，数据库名称，版本，表名
	 */
	public final static String DATABASE_NAME = "saylove.db";
	public final static int DATABASE_VERSION = 1;

	// 这样写方便以后可以更改字段名称
	public final static String TABLE_MMS_CREATE = "Mms(phone varchar(50),"
			+ "content varchar(200)," + "single varcher(10))";

	public final static String TABLE_MMS = "Mms";

	// 相遇
	public final static String TABLE_XIANGYU_CREATE = "Xiangyu(phone varchar(50),"
			+ "content varchar(200)," + "single varcher(10))";
	public final static String TABLE_XIANGYU = "Xiangyu";

	// 相识
	public final static String TABLE_XIANGSHI_CREATE = "Xiangshi(phone varchar(50),"
			+ "content varchar(200)," + "single varcher(10))";
	public final static String TABLE_XIANGSHI = "Xiangshi";

	// 相知
	public final static String TABLE_XIANGZHI_CREATE = "Xiangzhi(phone varchar(50),"
			+ "content varchar(200)," + "single varcher(10))";
	public final static String TABLE_XIANGZHI = "Xiangzhi";

	// 心动
	public final static String TABLE_XINDONG_CREATE = "Xindong(phone varchar(50),"
			+ "content varchar(200)," + "single varcher(10))";
	public final static String TABLE_XINDONG = "Xindong";

	// 定义数据库操作的帮助类，主要是用来打开和关闭数据库连接，而其他增删改查的方法应该在当前类(数据库操作管理类)中定义
	private static DBHelper dbHelper;

	// 定义数据库操作的实例
	private static SQLiteDatabase sqliteDatabase;

	private DBHelper(Context context) {
		File dbFile = new File(context.getFilesDir().getAbsolutePath() + "/"
				+ DATABASE_NAME);
		if (!dbFile.exists()) {
			sqliteDatabase = SQLiteDatabase.openOrCreateDatabase(context
					.getFilesDir().getAbsolutePath() + "/" + DATABASE_NAME,
					null);
			createTable();
		} else {
			sqliteDatabase = SQLiteDatabase.openDatabase(context.getFilesDir()
					.getAbsolutePath() + "/" + DATABASE_NAME, null,
					SQLiteDatabase.OPEN_READWRITE);
		}
	}

	// 打开数据库时创建数据库帮助类,获取写的失败时就获取读的
	public static SQLiteDatabase getDBInstance(Context context) {
		if (dbHelper == null) {
			dbHelper = new DBHelper(context);
		}
		if (!sqliteDatabase.isOpen()) {
			sqliteDatabase = SQLiteDatabase.openDatabase(context.getFilesDir()
					.getAbsolutePath() + "/" + DATABASE_NAME, null,
					SQLiteDatabase.OPEN_READWRITE);
		}

		return sqliteDatabase;
	}

	public void close() {
		sqliteDatabase.close();
	}

	/*
	 * 初次建表
	 */
	private void createTable() {
		sqliteDatabase
				.execSQL("create table if not exists " + TABLE_MMS_CREATE);
		sqliteDatabase.execSQL("create table if not exists "
				+ TABLE_XIANGYU_CREATE);
		sqliteDatabase.execSQL("create table if not exists "
				+ TABLE_XIANGSHI_CREATE);
		sqliteDatabase.execSQL("create table if not exists "
				+ TABLE_XIANGZHI_CREATE);
		sqliteDatabase.execSQL("create table if not exists "
				+ TABLE_XINDONG_CREATE);

	}

}
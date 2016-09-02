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
	 * �������ݿ������Ϣ�����ݿ����ƣ��汾������
	 */
	public final static String DATABASE_NAME = "saylove.db";
	public final static int DATABASE_VERSION = 1;

	// ����д�����Ժ���Ը����ֶ�����
	public final static String TABLE_MMS_CREATE = "Mms(phone varchar(50),"
			+ "content varchar(200)," + "single varcher(10))";

	public final static String TABLE_MMS = "Mms";

	// ����
	public final static String TABLE_XIANGYU_CREATE = "Xiangyu(phone varchar(50),"
			+ "content varchar(200)," + "single varcher(10))";
	public final static String TABLE_XIANGYU = "Xiangyu";

	// ��ʶ
	public final static String TABLE_XIANGSHI_CREATE = "Xiangshi(phone varchar(50),"
			+ "content varchar(200)," + "single varcher(10))";
	public final static String TABLE_XIANGSHI = "Xiangshi";

	// ��֪
	public final static String TABLE_XIANGZHI_CREATE = "Xiangzhi(phone varchar(50),"
			+ "content varchar(200)," + "single varcher(10))";
	public final static String TABLE_XIANGZHI = "Xiangzhi";

	// �Ķ�
	public final static String TABLE_XINDONG_CREATE = "Xindong(phone varchar(50),"
			+ "content varchar(200)," + "single varcher(10))";
	public final static String TABLE_XINDONG = "Xindong";

	// �������ݿ�����İ����࣬��Ҫ�������򿪺͹ر����ݿ����ӣ���������ɾ�Ĳ�ķ���Ӧ���ڵ�ǰ��(���ݿ����������)�ж���
	private static DBHelper dbHelper;

	// �������ݿ������ʵ��
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

	// �����ݿ�ʱ�������ݿ������,��ȡд��ʧ��ʱ�ͻ�ȡ����
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
	 * ���ν���
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
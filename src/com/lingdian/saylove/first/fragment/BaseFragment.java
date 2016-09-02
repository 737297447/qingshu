package com.lingdian.saylove.first.fragment;

import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;

import com.lingdian.saylove.database.DBLawOperator;

public class BaseFragment extends Fragment{
	
	protected DBLawOperator dbLawOper;
	protected SQLiteDatabase db;
	protected String[] sqlTime;
	protected String[] sqlGanshou;
	

}

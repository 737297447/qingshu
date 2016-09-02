package com.lingdian.saylove.first;

import com.lingdian.saylove.R;
import com.lingdian.saylove.database.DBHelper;
import com.lingdian.saylove.database.DBLawOperator;
import com.lingdian.saylove.first.adapter.FirstPagerAdapter;
import com.lingdian.saylove.first.fragment.FiveFragment;
import com.lingdian.saylove.first.fragment.FourFragment;
import com.lingdian.saylove.first.fragment.OneFragment;
import com.lingdian.saylove.first.fragment.ThreeFragment;
import com.lingdian.saylove.first.fragment.TwoFragment;
import com.lingdian.saylove.first.view.ColorAnimationView;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class FirstActivity extends FragmentActivity{
	
	private OneFragment oneFragment = new OneFragment();
	private TwoFragment twoFragment = new TwoFragment();
	private ThreeFragment threeFragment = new ThreeFragment();
	private FourFragment fourFragment = new FourFragment();
	private FiveFragment fiveFragment = new FiveFragment();
	private Fragment[] fragments = { oneFragment, twoFragment,threeFragment,fourFragment,fiveFragment };
	private FirstPagerAdapter fPagerAdapter;
	private ColorAnimationView colorAnimationView;
	private ViewPager viewPager;
	private DBLawOperator dbLawOper;
	private SQLiteDatabase db;
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_first);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		db = DBHelper.getDBInstance(this.getApplicationContext());
		dbLawOper = new DBLawOperator();
		initView();
	}
	
	public void initView(){
		fPagerAdapter = new FirstPagerAdapter(getSupportFragmentManager(), fragments);
		colorAnimationView = (ColorAnimationView) findViewById(R.id.ColorAnimationView);
		viewPager = (ViewPager) findViewById(R.id.viewPager);
		viewPager.setAdapter(fPagerAdapter);
		colorAnimationView.setmViewPager(viewPager, fragments.length);
        colorAnimationView.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.e("TAG","onPageScrolled");
            }

            @Override
            public void onPageSelected(int position) {
                Log.e("TAG","onPageSelected");
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.e("TAG","onPageScrollStateChanged");
            }
        });
		
	}
	

}

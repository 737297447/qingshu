package com.lingdian.saylove.first.fragment;

import com.lingdian.saylove.R;
import com.lingdian.saylove.first.view.SecretTextView;
import com.lingdian.saylove.second.SecondActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FiveFragment extends BaseFragment {

	private Context context;
	private SecretTextView secretTextView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		context = getActivity();
		View v = inflater.inflate(R.layout.fragment_five, null);
		return v;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		initView();
	}

	public void initView() {
		secretTextView = (SecretTextView) this.getView().findViewById(
				R.id.textview);
		secretTextView.setDuration(3000);
        secretTextView.setIsVisible(true);
		secretTextView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				secretTextView.toggle();
             new Handler().postDelayed(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					Intent i = new Intent(context,SecondActivity.class);
					startActivity(i);
				}
			}, 3100);
			}
		});
	}
}

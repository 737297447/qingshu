package com.lingdian.saylove.second;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.lingdian.saylove.R;
import com.lingdian.saylove.second.layout.DiscrollvableLastLayout;

public class SecondActivity extends Activity {

	
	private DiscrollvableLastLayout dLastLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_second);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
        dLastLayout = (DiscrollvableLastLayout)findViewById(R.id.second_end_layout);
        dLastLayout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(SecondActivity.this, "Ìø×ªÏÂÒ»Ò³", 0).show();
			}
		});
    }
}

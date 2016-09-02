package com.lingdian.saylove.tongxunlu;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.lingdian.saylove.R;



public class ContextActivity extends Activity{
	
	
	private ListView li;
	private List<ContextInfo> info;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.context_activity);
		
		info=new ContextInfoService(this).getContextInfo();
		li=(ListView)findViewById(R.id.lv_select_contact);
		li.setAdapter(new SelectContactAdapter());
		li.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				String number=info.get(position).getPhone();
				Intent intent = new Intent();
                intent.putExtra("number", number);
                //把要返回的数据设置进去，便通过onActivityResult(int, int, Intent)拿到
                setResult(1, intent);
                finish();
		
			}
			
		});
	}
	
	public class SelectContactAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return info.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return info.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			ContextInfo in=info.get(position);
			View view;
			ContactViews views;
			if(convertView==null){
				views=new ContactViews();
				view = View.inflate(ContextActivity.this, R.layout.context_activity_item, null);
				views.tv_name = (TextView) view.findViewById(R.id.tv_contact_name);
                views.tv_number = (TextView) view.findViewById(R.id.tv_contact_number);
                views.tv_name.setText("联系人：" + in.getName());
                views.tv_number.setText("联系电话：" + in.getPhone());

                view.setTag(views);
			}
			else
			{
				view = convertView;
			}
			
			
			
			return view;
		}
		
		
		
	}
	   private class ContactViews

       {
               TextView tv_name;
               TextView tv_number;
       }


	

}

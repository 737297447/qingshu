package com.lingdian.saylove.util.common;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.lingdian.saylove.R;
/**
 * 自定义dialog
 * 包含标题、提供个三个选择按钮，开放按钮点击事件调用的方法
 * 如性别选择、选择操作等
 * @author shenxy
 *
 */
public class ChooseDialog extends Dialog implements OnClickListener {

	private LayoutInflater factory;
	private TextView dialog_title;
	private Button dialog_btn_1;
	private Button dialog_btn_2;
	private Button dialog_btn_3;
	private String titleStr="";
	private String btn1Str="";
	private String btn2Str="";
	private String btn3Str="";
	
	public ChooseDialog(Context context,String title,String btn1Name,String btn2Name) {
		super(context);
		factory = LayoutInflater.from(context);
		this.titleStr = title;
		this.btn1Str = btn1Name;
		this.btn2Str = btn2Name;
	}
	
	public ChooseDialog(Context context, int theme,String title,String btn1Name) {
		super(context, theme);
		factory = LayoutInflater.from(context);
		this.titleStr = title;
		this.btn1Str = btn1Name;
	}
	
	public ChooseDialog(Context context, int theme,String title,String btn1Name,String btn2Name) {
		super(context, theme);
		factory = LayoutInflater.from(context);
		this.titleStr = title;
		this.btn1Str = btn1Name;
		this.btn2Str = btn2Name;
	}

	public ChooseDialog(Context context, int theme,String title,String btn1Name,String btn2Name,String btn3Name) {
		super(context, theme);
		factory = LayoutInflater.from(context);
		this.titleStr = title;
		this.btn1Str = btn1Name;
		this.btn2Str = btn2Name;
		this.btn3Str = btn3Name;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(factory.inflate(R.layout.oa_choose_dialog, null));
		dialog_title = (TextView) this.findViewById(R.id.dialog_title);
		dialog_btn_1 = (Button) this.findViewById(R.id.dialog_btn_1);
		dialog_btn_2 = (Button) this.findViewById(R.id.dialog_btn_2);
		dialog_btn_3 = (Button) this.findViewById(R.id.dialog_btn_3);
		dialog_btn_1.setOnClickListener(this);
		dialog_btn_2.setOnClickListener(this);
		dialog_btn_3.setOnClickListener(this);
		
		dialog_title.setText(titleStr);
		dialog_btn_1.setText(btn1Str);
		dialog_btn_2.setText(btn2Str);
		dialog_btn_3.setText(btn3Str);
		
		if("".equals(btn2Str)){
			dialog_btn_2.setVisibility(View.GONE);
		}
		if("".equals(btn3Str)){
			dialog_btn_3.setVisibility(View.GONE);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.dialog_btn_1:
			doBtn1Click();
			break;
		case R.id.dialog_btn_2:
			doBtn2Click();
			break;
		case R.id.dialog_btn_3:
			doBtn3Click();
			break;
		}
	}
	
	public void doBtn1Click(){
	}
	public void doBtn2Click(){
	}
	public void doBtn3Click(){
	}
}

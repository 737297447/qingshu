package com.lingdian.saylove.pintu;

/**
 * @file PictureLayout.java
 * @brief 重新布置打乱后的拼图
 * @author zhoujun
 * @version V1.0.00
 * @date 2012/09/12
 * Blog: http://blog.csdn.net/jjzhoujun2010
 */

import java.io.File;

import com.lingdian.saylove.R;
import com.lingdian.saylove.tools.FileChange;
import com.lingdian.saylove.util.common.FilePath;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * brief 重新布置打乱后的拼图
 * */
@SuppressLint("NewApi")
public class PictureLayout extends LinearLayout {

	public PictureLayout(Context context, ImageView[][] images) {
		super(context);
		setLayout(context, images);

		File file = new File(FilePath.USER_IMAGE_PATH);
		File[] jpgFiles = file.listFiles(FileChange
				.getFileExtensionFilter(".jpg"));
		if (jpgFiles != null) {
			if (jpgFiles.length == 0) {
				setBackgroundResource(R.drawable.pic1);
			} else {
				setBackground(Drawable.createFromPath(FilePath.USER_IMAGE_PATH
						+ jpgFiles[(int) (Math.random() * jpgFiles.length + 0)]
								.getName()));
			}
		} else {
			setBackgroundResource(R.drawable.pic1);
		}

	}

	/**
	 * @brief 重新布置打乱后的拼图
	 * @param view
	 *            当前被拆分打乱的照片
	 * */
	private void setLayout(Context context, ImageView[][] view) {

		LinearLayout linralayout = new LinearLayout(context);
		linralayout.setOrientation(LinearLayout.VERTICAL);
		linralayout.setBackgroundResource(R.drawable.linearlayout_biankuang);
		for (int i = 0; i < view.length; i++) {
			LinearLayout liner = new LinearLayout(context);
			liner.setOrientation(LinearLayout.HORIZONTAL);
			int leng = view[i].length;
			for (int j = 0; j < leng; j++) {
				ImageView img = (ImageView) view[i][j];
				liner.addView(img);
			}
			linralayout.addView(liner);
			liner = null;
		}
		this.addView(linralayout);
	}
}
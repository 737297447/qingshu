package com.lingdian.saylove.pintu;

/**
 * @file SePintu.java
 * @brief 显示拼图游戏界面，此图片是被打乱了的拼图
 * @author zhoujun
 * @version V1.0.00
 * @date 2012/09/12
 * Blog: http://blog.csdn.net/jjzhoujun2010
 */

import java.io.File;
import java.lang.reflect.Method;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

import com.lingdian.saylove.ILoveYouActivity;
import com.lingdian.saylove.R;
import com.lingdian.saylove.SayLoveActivity;
import com.lingdian.saylove.tools.FileChange;
import com.lingdian.saylove.util.common.FilePath;

/**
 * @brief 显示拼图游戏界面，此图片是被打乱了的拼图
 * */
public class SePintuActivity extends Activity {
	private int mLevelNow = 2;
	private ImageView mImages[][]; // 存放小图片的数组
	private Bitmap mBitmap; // 资源图片
	private int mImageWidth = 0, mImageHeight = 0; // slot的宽高
	private int mImageNum[]; // 图片的顺序
	private int mX = 0, mY = 0; // 图片的起始位置
	private int mClickNum = 0; // 点击参数
	private int mWindowWidth = 0, mWindowHeight = 0; // 屏幕参数

	public int randomInt;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		File file = new File(FilePath.USER_IMAGE_PATH);
		File[] jpgFiles = file.listFiles(FileChange
				.getFileExtensionFilter(".jpg"));
		randomInt = (int) (Math.random() * jpgFiles.length + 0);
		if (jpgFiles != null) {
			if (jpgFiles.length == 0) {
				mBitmap = BitmapFactory.decodeResource(getResources(),
						R.drawable.pic1);
			} else {
				mBitmap = FileChange.getBitmap(this, FilePath.USER_IMAGE_PATH
						+ jpgFiles[randomInt].getName());
			}
		}else{
			mBitmap = BitmapFactory.decodeResource(getResources(),
					R.drawable.pic1);
		}

		// 屏幕参数
		WindowManager w = this.getWindowManager();
		mWindowHeight = w.getDefaultDisplay().getHeight();
		mWindowWidth = w.getDefaultDisplay().getWidth();
		System.out.println("wWidth = " + mWindowWidth + "wHeight = "
				+ mWindowHeight);
		setImage();
		CreateWenjianjia();
	}

	public void CreateWenjianjia() {
		// 语音缓存地址
		File file = new File(FilePath.SAVE_MIC_PATH_TOSD);
		if (!file.exists())
			file.mkdir();
		// 图片缓存地址
		File file1 = new File(FilePath.SAVE_IMAGELOAD_CACHE_PATH);
		if (!file1.exists())
			file1.mkdir();
		// 语音保存的地址
		File file2 = new File(FilePath.USER_MIC_PATH);
		if (!file2.exists())
			file2.mkdir();
		// 图片保存的地址
		File file3 = new File(FilePath.USER_IMAGE_PATH);
		if (!file3.exists())
			file3.mkdir();
	}

	/**
	 * @brief 将一副图片分割成几个小部分打乱
	 */
	public void setImage() {
		mImageWidth = mBitmap.getWidth() / mLevelNow; // 切割图片，每一小块的宽度
		mImageHeight = mBitmap.getHeight() / mLevelNow;
		mImageNum = new int[mLevelNow * mLevelNow];
		System.out.println("mIWidth = " + mImageWidth + "mImageHeight = "
				+ mImageHeight);
		erraLen(mLevelNow * mLevelNow); // 随机组合切碎的小图片
		readyImage();
		setLayout(); // 布局随机组合后的图片
	}

	/**
	 * @brief 在当前的Activity中显示打乱后的拼图以及相应按钮
	 * */
	private void setLayout() {
		PictureLayout lay = new PictureLayout(this, mImages); // 利用带参数的构造函数来布局小图片
		lay.setOrientation(LinearLayout.VERTICAL);
		lay.setGravity(Gravity.CENTER);
		setContentView(lay); // 显示lay布局，SePintu的Activity
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		Button methodButton = new Button(this);
		methodButton.setText("游戏说明");
		methodButton.setTextColor(Color.WHITE);
		methodButton.setOnClickListener(new MethodBtnClick()); // 添加监听器

		Button showSourceImageBtn = new Button(this);
		showSourceImageBtn.setText("查看原图");
		showSourceImageBtn.setTextColor(Color.WHITE);
		showSourceImageBtn.setOnClickListener(new SourceBtnClick());

		LinearLayout linear = new LinearLayout(this);
		// 注意，对于LinearLayout布局来说，设置横向还是纵向是必须的！否则就看不到效果了。
		linear.setOrientation(LinearLayout.HORIZONTAL);
		// 此处相当于布局文件中的Android：gravity属性
		linear.setGravity(Gravity.CENTER_HORIZONTAL);
		methodButton.setWidth(200);
		methodButton.setPadding(0, 15, 0, 0);
		showSourceImageBtn.setWidth(200);
		showSourceImageBtn.setPadding(0, 15, 0, 0);
		linear.addView(methodButton); // 通过addView将两按钮添加到布局中
		linear.addView(showSourceImageBtn);
		lay.addView(linear); // 把linear当作子child添加到lay布局中
	}

	/**
	 * @brief 点击“游戏说明”按钮，跳转到相应的Activity中
	 * */
	class MethodBtnClick implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent();
			intent.setClass(SePintuActivity.this, DetailPintuActivity.class);
			startActivity(intent); // 跳转Activity
		}
	}

	/**
	 * @brief 点击“查看原图”按钮，显示出未打乱的原图
	 * */
	class SourceBtnClick implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(SePintuActivity.this,
					SourceImageActivity.class);
			intent.putExtra("randomInt", randomInt);
			startActivity(intent); // 跳转Activity
		}

	}

	/**
	 * @brief 把一个有序数组通过随机取数打乱
	 * @param lenght
	 *            数组大小，随机数的范围
	 */
	public void erraLen(int lenght) {

		int errInt[] = new int[lenght];
		for (int i = 0; i < lenght; i++) {
			errInt[i] = i;
		}

		int len = lenght;// 设置随机数的范围
		for (int i = 0; i < lenght; i++) {
			int index = (int) Math.floor((Math.random() * len));
			mImageNum[i] = errInt[index];

			for (int j = index; j < errInt.length - 1; j++) {
				// 把选中的数之后的数一次向前移一位，因为index选中的数已经存放在相应的mImageNum里面了，
				errInt[j] = errInt[j + 1];
			}
			len--;// 随机数的范围减一
		}
	}

	/**
	 * @brief 准备图片 把一张图片截成几张小的通过打乱的数组来取cache里的图片放到View里面就成打乱二维数组
	 */
	private void readyImage() {
		Matrix matrix = new Matrix();
		mImages = new ImageView[mLevelNow][mLevelNow];

		// 设置缩放比例
		// float scaleW = ((float) mBitmap.getWidth()) / mBitmap.getWidth();
		// float scaleH = ((float) mBitmap.getHeight()) / mBitmap.getHeight();
		float scaleW = ((float) mBitmap.getWidth()) / (mWindowWidth + 180);
		float scaleH = ((float) mBitmap.getHeight()) / (mWindowHeight + 180);
		System.out.println("scaleW = " + scaleW + " scaleH" + scaleH);

		float scale = scaleW > scaleH ? 1 / scaleW : 1 / scaleH; // scale是缩放比例，取最小比例的进行缩放
		System.out.println("scale = " + scale);
		matrix.postScale(scale, scale);

		Bitmap bitss[][] = new Bitmap[mLevelNow][mLevelNow];
		ImageView[][] cache = new ImageView[mLevelNow][mLevelNow];
		int cont = 1;
		for (int i = 0; i < mLevelNow; i++) {
			for (int j = 0; j < mLevelNow; j++) {
				int mX = i * mImageWidth;
				int mY = j * mImageHeight;
				// 第一个是要在那个图片上截取 mX,y是要在这个图的那个位置截取
				// mImageWidth，mImageHeight是截取的长和宽， matrix是缩放比例
				Bitmap mapi = Bitmap.createBitmap(mBitmap, mX, mY, mImageWidth,
						mImageHeight, matrix, true);

				bitss[i][j] = mapi;
				ImageView img = new ImageView(this);
				BitmapDrawable draw = new BitmapDrawable(bitss[i][j]);
				img.setImageDrawable(draw);
				img.setId(cont);
				img.setScaleType(ScaleType.FIT_XY);
				img.setOnClickListener(OnClickImageView1);
				cache[i][j] = img; // cache存放着整张图切割后的小图片
				cont++;
			}
		}

		for (int i = 0; i < mImageNum.length; i++) {
			int mX = mImageNum[i] / mLevelNow; // 确定第几行
			int mY = mImageNum[i] % mLevelNow; // 确定第几列
			int x1 = i / mLevelNow;
			int y1 = i % mLevelNow;
			mImages[x1][y1] = cache[mX][mY]; // 将cache里面的小图片随机放入mImages数组里面
		}
	}

	/**
	 * @brief 监听点击操作，以便判断是否需要互相交换小图片
	 * */
	private android.view.View.OnClickListener OnClickImageView1 = new ImageView.OnClickListener() {
		@Override
		public void onClick(View v) {
			if (mClickNum == 0) {// 即需要交换的第一个图片
				for (int i = 0; i < mImages.length; i++) {
					boolean f = false;
					for (int j = 0; j < mImages[i].length; j++) {
						ImageView imgg = mImages[i][j];
						if (imgg == v) { // 所点击的刚好是指定的小图片区域
							mX = i;
							mY = j;
							mClickNum++; // 点击了一次
							f = true;
							break;
						}
					}
					if (f) {
						break;
					}
				}
			} else {// 即需要交换的第二个图片
				for (int i = 0; i < mImages.length; i++) {
					for (int j = 0; j < mImages[i].length; j++) {
						ImageView imgg = mImages[i][j];
						if (imgg == v) {
							if (mClickNum == 1) {
								changePosition(i, j, mX, mY);
								mX = 0;
								mY = 0;
								mClickNum = 0;
							}
						}
					}
				}
			}
		}
	};

	/**
	 * @brief 判断是否需要互相交换小图片
	 * @param x1
	 *            第一张图片左上角x坐标的值
	 * @param y1
	 *            第一张图片左上角y坐标的值
	 * @param x2
	 *            第二张图片左上角x坐标的值
	 * @param y2
	 *            第二张图片左上角y坐标的值
	 * */
	private void changePosition(int x1, int y1, int x2, int y2) {
		// 判断宽和高差的绝对值是否是1，如果是1的话交换两张图片，不是1的话提示用户
		if (Math.abs(x1 - x2) + Math.abs(y1 - y2) != 1) {
			System.out.println("not link....");
			Builder bul = new AlertDialog.Builder(this); // 弹出相应对话框
			bul.setTitle("提示");
			bul.setMessage("不相连的不能交换");
			bul.setPositiveButton("确定", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
				}
			}).show();
		} else { // 相邻，两张图片进行交换
			System.out.println("link....");
			String str = "";
			ImageView bitF = null;
			bitF = mImages[x1][y1];
			mImages[x1][y1] = mImages[x2][y2];
			mImages[x2][y2] = bitF;

			for (int i = 0; i < mImages.length; i++) {
				for (int j = 0; j < mImages[i].length; j++) {
					ImageView img = mImages[i][j];
					// 得到ImageView的父控件
					LinearLayout pa = (LinearLayout) img.getParent();
					// 再移除ImageView使其父控件没有，移除父控件，重新用setLayout()进行布局
					pa.removeView(img);
				}
			}
			setLayout(); // 将进行变换操作的图片显示出来

			for (int i = 0; i < mImages.length; i++) {
				for (int j = 0; j < mImages[i].length; j++) {
					str += mImages[i][j].getId(); // 取对应小图片的ID，相当于R.id.mImages[i][j]
				}
			}
			// 根据具体切割次数mLevelNow，判断最后一次变换是否已经拼好
			switch (mLevelNow) {
			case 2:
				if (str.equals("1324")) {
					// “1324”的意思是，将图片2*2地切割，有四块，标号先竖着，然后再横着来1234，根据getId取值互相比较
					this.success();
				}
				break;
			case 3:
				if (str.equals("147258369")) {
					this.success();
				}
				break;
			case 4:
				if (str.equals("15913261014371115481216")) {
					this.success();
				}
				break;
			case 5:
				if (str.equals("16111621271217223813182349141924510152025")) {
					this.success();
				}
				break;
			}
		}
	}

	/**
	 * @brief 完成拼图后，提示成功完成对话框，点击按钮进入下一个Activity界面
	 */
	public void success() {
		Builder bul = new AlertDialog.Builder(this);
		bul.setTitle("提示");
		bul.setMessage("终于等到主角上场了，^_^");
		bul.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				Intent intent = new Intent(SePintuActivity.this,
						ILoveYouActivity.class);
				SePintuActivity.this.startActivity(intent);
			}
		});
		bul.show();
		System.out.println("success");
	}
}
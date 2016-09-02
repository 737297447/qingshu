package com.lingdian.saylove.pintu;

/**
 * @file SePintu.java
 * @brief ��ʾƴͼ��Ϸ���棬��ͼƬ�Ǳ������˵�ƴͼ
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
 * @brief ��ʾƴͼ��Ϸ���棬��ͼƬ�Ǳ������˵�ƴͼ
 * */
public class SePintuActivity extends Activity {
	private int mLevelNow = 2;
	private ImageView mImages[][]; // ���СͼƬ������
	private Bitmap mBitmap; // ��ԴͼƬ
	private int mImageWidth = 0, mImageHeight = 0; // slot�Ŀ��
	private int mImageNum[]; // ͼƬ��˳��
	private int mX = 0, mY = 0; // ͼƬ����ʼλ��
	private int mClickNum = 0; // �������
	private int mWindowWidth = 0, mWindowHeight = 0; // ��Ļ����

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

		// ��Ļ����
		WindowManager w = this.getWindowManager();
		mWindowHeight = w.getDefaultDisplay().getHeight();
		mWindowWidth = w.getDefaultDisplay().getWidth();
		System.out.println("wWidth = " + mWindowWidth + "wHeight = "
				+ mWindowHeight);
		setImage();
		CreateWenjianjia();
	}

	public void CreateWenjianjia() {
		// ���������ַ
		File file = new File(FilePath.SAVE_MIC_PATH_TOSD);
		if (!file.exists())
			file.mkdir();
		// ͼƬ�����ַ
		File file1 = new File(FilePath.SAVE_IMAGELOAD_CACHE_PATH);
		if (!file1.exists())
			file1.mkdir();
		// ��������ĵ�ַ
		File file2 = new File(FilePath.USER_MIC_PATH);
		if (!file2.exists())
			file2.mkdir();
		// ͼƬ����ĵ�ַ
		File file3 = new File(FilePath.USER_IMAGE_PATH);
		if (!file3.exists())
			file3.mkdir();
	}

	/**
	 * @brief ��һ��ͼƬ�ָ�ɼ���С���ִ���
	 */
	public void setImage() {
		mImageWidth = mBitmap.getWidth() / mLevelNow; // �и�ͼƬ��ÿһС��Ŀ��
		mImageHeight = mBitmap.getHeight() / mLevelNow;
		mImageNum = new int[mLevelNow * mLevelNow];
		System.out.println("mIWidth = " + mImageWidth + "mImageHeight = "
				+ mImageHeight);
		erraLen(mLevelNow * mLevelNow); // �����������СͼƬ
		readyImage();
		setLayout(); // ���������Ϻ��ͼƬ
	}

	/**
	 * @brief �ڵ�ǰ��Activity����ʾ���Һ��ƴͼ�Լ���Ӧ��ť
	 * */
	private void setLayout() {
		PictureLayout lay = new PictureLayout(this, mImages); // ���ô������Ĺ��캯��������СͼƬ
		lay.setOrientation(LinearLayout.VERTICAL);
		lay.setGravity(Gravity.CENTER);
		setContentView(lay); // ��ʾlay���֣�SePintu��Activity
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		Button methodButton = new Button(this);
		methodButton.setText("��Ϸ˵��");
		methodButton.setTextColor(Color.WHITE);
		methodButton.setOnClickListener(new MethodBtnClick()); // ��Ӽ�����

		Button showSourceImageBtn = new Button(this);
		showSourceImageBtn.setText("�鿴ԭͼ");
		showSourceImageBtn.setTextColor(Color.WHITE);
		showSourceImageBtn.setOnClickListener(new SourceBtnClick());

		LinearLayout linear = new LinearLayout(this);
		// ע�⣬����LinearLayout������˵�����ú����������Ǳ���ģ�����Ϳ�����Ч���ˡ�
		linear.setOrientation(LinearLayout.HORIZONTAL);
		// �˴��൱�ڲ����ļ��е�Android��gravity����
		linear.setGravity(Gravity.CENTER_HORIZONTAL);
		methodButton.setWidth(200);
		methodButton.setPadding(0, 15, 0, 0);
		showSourceImageBtn.setWidth(200);
		showSourceImageBtn.setPadding(0, 15, 0, 0);
		linear.addView(methodButton); // ͨ��addView������ť��ӵ�������
		linear.addView(showSourceImageBtn);
		lay.addView(linear); // ��linear������child��ӵ�lay������
	}

	/**
	 * @brief �������Ϸ˵������ť����ת����Ӧ��Activity��
	 * */
	class MethodBtnClick implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent();
			intent.setClass(SePintuActivity.this, DetailPintuActivity.class);
			startActivity(intent); // ��תActivity
		}
	}

	/**
	 * @brief ������鿴ԭͼ����ť����ʾ��δ���ҵ�ԭͼ
	 * */
	class SourceBtnClick implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(SePintuActivity.this,
					SourceImageActivity.class);
			intent.putExtra("randomInt", randomInt);
			startActivity(intent); // ��תActivity
		}

	}

	/**
	 * @brief ��һ����������ͨ�����ȡ������
	 * @param lenght
	 *            �����С��������ķ�Χ
	 */
	public void erraLen(int lenght) {

		int errInt[] = new int[lenght];
		for (int i = 0; i < lenght; i++) {
			errInt[i] = i;
		}

		int len = lenght;// ����������ķ�Χ
		for (int i = 0; i < lenght; i++) {
			int index = (int) Math.floor((Math.random() * len));
			mImageNum[i] = errInt[index];

			for (int j = index; j < errInt.length - 1; j++) {
				// ��ѡ�е���֮�����һ����ǰ��һλ����Ϊindexѡ�е����Ѿ��������Ӧ��mImageNum�����ˣ�
				errInt[j] = errInt[j + 1];
			}
			len--;// ������ķ�Χ��һ
		}
	}

	/**
	 * @brief ׼��ͼƬ ��һ��ͼƬ�سɼ���С��ͨ�����ҵ�������ȡcache���ͼƬ�ŵ�View����ͳɴ��Ҷ�ά����
	 */
	private void readyImage() {
		Matrix matrix = new Matrix();
		mImages = new ImageView[mLevelNow][mLevelNow];

		// �������ű���
		// float scaleW = ((float) mBitmap.getWidth()) / mBitmap.getWidth();
		// float scaleH = ((float) mBitmap.getHeight()) / mBitmap.getHeight();
		float scaleW = ((float) mBitmap.getWidth()) / (mWindowWidth + 180);
		float scaleH = ((float) mBitmap.getHeight()) / (mWindowHeight + 180);
		System.out.println("scaleW = " + scaleW + " scaleH" + scaleH);

		float scale = scaleW > scaleH ? 1 / scaleW : 1 / scaleH; // scale�����ű�����ȡ��С�����Ľ�������
		System.out.println("scale = " + scale);
		matrix.postScale(scale, scale);

		Bitmap bitss[][] = new Bitmap[mLevelNow][mLevelNow];
		ImageView[][] cache = new ImageView[mLevelNow][mLevelNow];
		int cont = 1;
		for (int i = 0; i < mLevelNow; i++) {
			for (int j = 0; j < mLevelNow; j++) {
				int mX = i * mImageWidth;
				int mY = j * mImageHeight;
				// ��һ����Ҫ���Ǹ�ͼƬ�Ͻ�ȡ mX,y��Ҫ�����ͼ���Ǹ�λ�ý�ȡ
				// mImageWidth��mImageHeight�ǽ�ȡ�ĳ��Ϳ� matrix�����ű���
				Bitmap mapi = Bitmap.createBitmap(mBitmap, mX, mY, mImageWidth,
						mImageHeight, matrix, true);

				bitss[i][j] = mapi;
				ImageView img = new ImageView(this);
				BitmapDrawable draw = new BitmapDrawable(bitss[i][j]);
				img.setImageDrawable(draw);
				img.setId(cont);
				img.setScaleType(ScaleType.FIT_XY);
				img.setOnClickListener(OnClickImageView1);
				cache[i][j] = img; // cache���������ͼ�и���СͼƬ
				cont++;
			}
		}

		for (int i = 0; i < mImageNum.length; i++) {
			int mX = mImageNum[i] / mLevelNow; // ȷ���ڼ���
			int mY = mImageNum[i] % mLevelNow; // ȷ���ڼ���
			int x1 = i / mLevelNow;
			int y1 = i % mLevelNow;
			mImages[x1][y1] = cache[mX][mY]; // ��cache�����СͼƬ�������mImages��������
		}
	}

	/**
	 * @brief ��������������Ա��ж��Ƿ���Ҫ���ཻ��СͼƬ
	 * */
	private android.view.View.OnClickListener OnClickImageView1 = new ImageView.OnClickListener() {
		@Override
		public void onClick(View v) {
			if (mClickNum == 0) {// ����Ҫ�����ĵ�һ��ͼƬ
				for (int i = 0; i < mImages.length; i++) {
					boolean f = false;
					for (int j = 0; j < mImages[i].length; j++) {
						ImageView imgg = mImages[i][j];
						if (imgg == v) { // ������ĸպ���ָ����СͼƬ����
							mX = i;
							mY = j;
							mClickNum++; // �����һ��
							f = true;
							break;
						}
					}
					if (f) {
						break;
					}
				}
			} else {// ����Ҫ�����ĵڶ���ͼƬ
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
	 * @brief �ж��Ƿ���Ҫ���ཻ��СͼƬ
	 * @param x1
	 *            ��һ��ͼƬ���Ͻ�x�����ֵ
	 * @param y1
	 *            ��һ��ͼƬ���Ͻ�y�����ֵ
	 * @param x2
	 *            �ڶ���ͼƬ���Ͻ�x�����ֵ
	 * @param y2
	 *            �ڶ���ͼƬ���Ͻ�y�����ֵ
	 * */
	private void changePosition(int x1, int y1, int x2, int y2) {
		// �жϿ�͸߲�ľ���ֵ�Ƿ���1�������1�Ļ���������ͼƬ������1�Ļ���ʾ�û�
		if (Math.abs(x1 - x2) + Math.abs(y1 - y2) != 1) {
			System.out.println("not link....");
			Builder bul = new AlertDialog.Builder(this); // ������Ӧ�Ի���
			bul.setTitle("��ʾ");
			bul.setMessage("�������Ĳ��ܽ���");
			bul.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
				}
			}).show();
		} else { // ���ڣ�����ͼƬ���н���
			System.out.println("link....");
			String str = "";
			ImageView bitF = null;
			bitF = mImages[x1][y1];
			mImages[x1][y1] = mImages[x2][y2];
			mImages[x2][y2] = bitF;

			for (int i = 0; i < mImages.length; i++) {
				for (int j = 0; j < mImages[i].length; j++) {
					ImageView img = mImages[i][j];
					// �õ�ImageView�ĸ��ؼ�
					LinearLayout pa = (LinearLayout) img.getParent();
					// ���Ƴ�ImageViewʹ�丸�ؼ�û�У��Ƴ����ؼ���������setLayout()���в���
					pa.removeView(img);
				}
			}
			setLayout(); // �����б任������ͼƬ��ʾ����

			for (int i = 0; i < mImages.length; i++) {
				for (int j = 0; j < mImages[i].length; j++) {
					str += mImages[i][j].getId(); // ȡ��ӦСͼƬ��ID���൱��R.id.mImages[i][j]
				}
			}
			// ���ݾ����и����mLevelNow���ж����һ�α任�Ƿ��Ѿ�ƴ��
			switch (mLevelNow) {
			case 2:
				if (str.equals("1324")) {
					// ��1324������˼�ǣ���ͼƬ2*2���и���Ŀ飬��������ţ�Ȼ���ٺ�����1234������getIdȡֵ����Ƚ�
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
	 * @brief ���ƴͼ����ʾ�ɹ���ɶԻ��򣬵����ť������һ��Activity����
	 */
	public void success() {
		Builder bul = new AlertDialog.Builder(this);
		bul.setTitle("��ʾ");
		bul.setMessage("���ڵȵ������ϳ��ˣ�^_^");
		bul.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {

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
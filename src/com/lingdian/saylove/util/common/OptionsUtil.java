package com.lingdian.saylove.util.common;


import android.graphics.Bitmap;

import com.lingdian.saylove.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;

/** imageload的工具类 */
public class OptionsUtil {

	private static DisplayImageOptions TaskMemberRounded = null;
	private static DisplayImageOptions TaskMember = null;
	private static DisplayImageOptions Find = null;
	private static DisplayImageOptions PicNormal = null;
	private static DisplayImageOptions PicNormalNoLoadingPic = null;
	private static DisplayImageOptions msg = null;
	private static DisplayImageOptions mainOptions = null;
	private static DisplayImageOptions assisOptions = null;
	private static DisplayImageOptions picRoundedRounded = null;
	private static DisplayImageOptions DeptOptions = null;
	private static DisplayImageOptions ContactOptions = null;
	private static DisplayImageOptions MeetingIndxOpt = null;
	private static DisplayImageOptions MeetingUserRounded = null;
	private static DisplayImageOptions MeetingCalingUserRounded = null;
	private static DisplayImageOptions MeetingImageOptions = null;
	private static DisplayImageOptions MeetingCommentPic = null;

	/** 成员头像圆角 */
	public static DisplayImageOptions TaskMemberRounded() {
		if (TaskMemberRounded == null) {
			TaskMemberRounded = new DisplayImageOptions.Builder().showStubImage(R.drawable.oa_selecter_user_icon)
			// 设置图片下载期间显示的图片
				.showImageForEmptyUri(R.drawable.oa_selecter_user_icon)
				// 设置图片Uri为空或是错误的时候显示的图片
				.showImageOnFail(R.drawable.oa_selecter_user_icon)
				// 设置图片加载或解码过程中发生错误显示的图片
				.cacheInMemory(true)
				// 设置下载的图片是否缓存在内存中
				.cacheOnDisc(true)
				// 设置下载的图片是否缓存在SD卡中
				.displayer(new RoundedBitmapDisplayer(12)).imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)// 降低图像质量
				.bitmapConfig(Bitmap.Config.RGB_565) // 设置图片色彩格式
				.build();
		}// 创建配置过得DisplayImageOption对象

		return TaskMemberRounded;
	}

	/** 成员头像圆角 */
	public static DisplayImageOptions picRounded() {
		if (picRoundedRounded == null) {
			picRoundedRounded = new DisplayImageOptions.Builder()
			// 设置图片下载期间显示的图片
				.showImageForEmptyUri(R.drawable.oa_selecter_media_pic_item)
				// 设置图片Uri为空或是错误的时候显示的图片
				.showImageOnFail(R.drawable.oa_selecter_media_pic_item)
				// 设置图片加载或解码过程中发生错误显示的图片
				.cacheInMemory(true)
				// 设置下载的图片是否缓存在内存中
				.cacheOnDisc(true)
				// 设置下载的图片是否缓存在SD卡中
				.displayer(new RoundedBitmapDisplayer(12)).imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)// 降低图像质量
				.bitmapConfig(Bitmap.Config.RGB_565) // 设置图片色彩格式
				.build();
		}// 创建配置过得DisplayImageOption对象

		return picRoundedRounded;
	}



	

	/** 图片未加载图像 */
	public static DisplayImageOptions PicNormal() {
		if (PicNormal == null) {
			PicNormal = new DisplayImageOptions.Builder().showStubImage(R.drawable.oa_selecter_media_pic_item) // 设置图片下载期间显示的图片
				.showImageForEmptyUri(R.drawable.oa_selecter_media_pic_item) // 设置图片Uri为空或是错误的时候显示的图片
				.showImageOnFail(R.drawable.oa_selecter_media_pic_item) // 设置图片加载或解码过程中发生错误显示的图片
				.cacheInMemory(true) // 设置下载的图片是否缓存在内存中
				.cacheOnDisc(true) // 设置下载的图片是否缓存在SD卡中
				.imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)// 降低图像质量
				.bitmapConfig(Bitmap.Config.RGB_565).build(); // 创建配置过得DisplayImageOption对象
		}
		return PicNormal;
	}

	/** 图片未加载图像 */
	public static DisplayImageOptions PicNormalNoLoadingPic() {
		if (PicNormalNoLoadingPic == null) {
			PicNormalNoLoadingPic = new DisplayImageOptions.Builder() // 设置图片下载期间显示的图片
				.showImageForEmptyUri(R.drawable.oa_selecter_media_pic_item) // 设置图片Uri为空或是错误的时候显示的图片
				.showImageOnFail(R.drawable.oa_selecter_media_pic_item) // 设置图片加载或解码过程中发生错误显示的图片
				.cacheInMemory(true) // 设置下载的图片是否缓存在内存中
				.cacheOnDisc(true) // 设置下载的图片是否缓存在SD卡中
				.imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)// 降低图像质量
				.bitmapConfig(Bitmap.Config.RGB_565).build(); // 创建配置过得DisplayImageOption对象
		}
		return PicNormalNoLoadingPic;
	}









	




}

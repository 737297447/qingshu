package com.lingdian.saylove.util.common;


import android.graphics.Bitmap;

import com.lingdian.saylove.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;

/** imageload�Ĺ����� */
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

	/** ��Աͷ��Բ�� */
	public static DisplayImageOptions TaskMemberRounded() {
		if (TaskMemberRounded == null) {
			TaskMemberRounded = new DisplayImageOptions.Builder().showStubImage(R.drawable.oa_selecter_user_icon)
			// ����ͼƬ�����ڼ���ʾ��ͼƬ
				.showImageForEmptyUri(R.drawable.oa_selecter_user_icon)
				// ����ͼƬUriΪ�ջ��Ǵ����ʱ����ʾ��ͼƬ
				.showImageOnFail(R.drawable.oa_selecter_user_icon)
				// ����ͼƬ���ػ��������з���������ʾ��ͼƬ
				.cacheInMemory(true)
				// �������ص�ͼƬ�Ƿ񻺴����ڴ���
				.cacheOnDisc(true)
				// �������ص�ͼƬ�Ƿ񻺴���SD����
				.displayer(new RoundedBitmapDisplayer(12)).imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)// ����ͼ������
				.bitmapConfig(Bitmap.Config.RGB_565) // ����ͼƬɫ�ʸ�ʽ
				.build();
		}// �������ù���DisplayImageOption����

		return TaskMemberRounded;
	}

	/** ��Աͷ��Բ�� */
	public static DisplayImageOptions picRounded() {
		if (picRoundedRounded == null) {
			picRoundedRounded = new DisplayImageOptions.Builder()
			// ����ͼƬ�����ڼ���ʾ��ͼƬ
				.showImageForEmptyUri(R.drawable.oa_selecter_media_pic_item)
				// ����ͼƬUriΪ�ջ��Ǵ����ʱ����ʾ��ͼƬ
				.showImageOnFail(R.drawable.oa_selecter_media_pic_item)
				// ����ͼƬ���ػ��������з���������ʾ��ͼƬ
				.cacheInMemory(true)
				// �������ص�ͼƬ�Ƿ񻺴����ڴ���
				.cacheOnDisc(true)
				// �������ص�ͼƬ�Ƿ񻺴���SD����
				.displayer(new RoundedBitmapDisplayer(12)).imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)// ����ͼ������
				.bitmapConfig(Bitmap.Config.RGB_565) // ����ͼƬɫ�ʸ�ʽ
				.build();
		}// �������ù���DisplayImageOption����

		return picRoundedRounded;
	}



	

	/** ͼƬδ����ͼ�� */
	public static DisplayImageOptions PicNormal() {
		if (PicNormal == null) {
			PicNormal = new DisplayImageOptions.Builder().showStubImage(R.drawable.oa_selecter_media_pic_item) // ����ͼƬ�����ڼ���ʾ��ͼƬ
				.showImageForEmptyUri(R.drawable.oa_selecter_media_pic_item) // ����ͼƬUriΪ�ջ��Ǵ����ʱ����ʾ��ͼƬ
				.showImageOnFail(R.drawable.oa_selecter_media_pic_item) // ����ͼƬ���ػ��������з���������ʾ��ͼƬ
				.cacheInMemory(true) // �������ص�ͼƬ�Ƿ񻺴����ڴ���
				.cacheOnDisc(true) // �������ص�ͼƬ�Ƿ񻺴���SD����
				.imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)// ����ͼ������
				.bitmapConfig(Bitmap.Config.RGB_565).build(); // �������ù���DisplayImageOption����
		}
		return PicNormal;
	}

	/** ͼƬδ����ͼ�� */
	public static DisplayImageOptions PicNormalNoLoadingPic() {
		if (PicNormalNoLoadingPic == null) {
			PicNormalNoLoadingPic = new DisplayImageOptions.Builder() // ����ͼƬ�����ڼ���ʾ��ͼƬ
				.showImageForEmptyUri(R.drawable.oa_selecter_media_pic_item) // ����ͼƬUriΪ�ջ��Ǵ����ʱ����ʾ��ͼƬ
				.showImageOnFail(R.drawable.oa_selecter_media_pic_item) // ����ͼƬ���ػ��������з���������ʾ��ͼƬ
				.cacheInMemory(true) // �������ص�ͼƬ�Ƿ񻺴����ڴ���
				.cacheOnDisc(true) // �������ص�ͼƬ�Ƿ񻺴���SD����
				.imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)// ����ͼ������
				.bitmapConfig(Bitmap.Config.RGB_565).build(); // �������ù���DisplayImageOption����
		}
		return PicNormalNoLoadingPic;
	}









	




}

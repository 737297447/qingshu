package com.lingdian.saylove;

import java.io.File;

import com.lingdian.saylove.util.common.FilePath;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.FIFOLimitedMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import android.app.Application;

public class SysApp extends Application {

	private static SysApp app = null;
	public ImageLoader imageLoader;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		app = this;
		// ��ʼ��ͼƬ���ز���
		initImageLoader();
	}

	public static SysApp getApp() {
		return app;
	}

	/** ��ʼ��ͼƬ������������Ϣ **/
	public void initImageLoader() {
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				SysApp.getApp())
				.threadPriority(Thread.NORM_PRIORITY)
				// ���õ�ǰ�߳����ȼ�
				.threadPoolSize(5)
				// �̳߳�����
				.denyCacheImageMultipleSizesInMemory()
				// ����ͼ��Ĵ�ߴ罫���ڴ��л�����ǰ����ͼ���С�ߴ硣
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				// ���ô��̻����ļ�����
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				// ���ü�����ʾͼƬ���н���
				.discCache(
						new UnlimitedDiscCache(new File(
								FilePath.SAVE_IMAGELOAD_CACHE_PATH)))
				// default
				.discCacheSize(50 * 1024 * 1024)
				// �����С
				.discCacheFileCount(100)
				.memoryCache(new FIFOLimitedMemoryCache(10 * 1024 * 1024))// �Ƚ��ȳ����ԣ������ڴ滺�棬�����Ƚ��ȳ��ͷ�
				.memoryCacheSize(10 * 1024 * 1024)// �ڴ滺���С
				.discCacheSize(200 * 1024 * 1024)// ��󱾵ػ���
				.build();
		ImageLoader.getInstance().init(config);
	}

	// ��ȡͼƬ�������
	public ImageLoader getImageLoader() {
		if (imageLoader == null) {
			imageLoader = ImageLoader.getInstance();
		}
		return imageLoader;
	}

	// �ͷ�ͼƬռ���ڴ�
	public void recyleImageLoader() {
		if (imageLoader == null) {
			imageLoader.clearMemoryCache();
		}
	}

}

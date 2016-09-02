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
		// 初始化图片加载策略
		initImageLoader();
	}

	public static SysApp getApp() {
		return app;
	}

	/** 初始化图片加载类配置信息 **/
	public void initImageLoader() {
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				SysApp.getApp())
				.threadPriority(Thread.NORM_PRIORITY)
				// 设置当前线程优先级
				.threadPoolSize(5)
				// 线程池设置
				.denyCacheImageMultipleSizesInMemory()
				// 解码图像的大尺寸将在内存中缓存先前解码图像的小尺寸。
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				// 设置磁盘缓存文件名称
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				// 设置加载显示图片队列进程
				.discCache(
						new UnlimitedDiscCache(new File(
								FilePath.SAVE_IMAGELOAD_CACHE_PATH)))
				// default
				.discCacheSize(50 * 1024 * 1024)
				// 缓冲大小
				.discCacheFileCount(100)
				.memoryCache(new FIFOLimitedMemoryCache(10 * 1024 * 1024))// 先进先出策略，超出内存缓存，按照先进先出释放
				.memoryCacheSize(10 * 1024 * 1024)// 内存缓存大小
				.discCacheSize(200 * 1024 * 1024)// 最大本地缓存
				.build();
		ImageLoader.getInstance().init(config);
	}

	// 获取图片处理对象
	public ImageLoader getImageLoader() {
		if (imageLoader == null) {
			imageLoader = ImageLoader.getInstance();
		}
		return imageLoader;
	}

	// 释放图片占用内存
	public void recyleImageLoader() {
		if (imageLoader == null) {
			imageLoader.clearMemoryCache();
		}
	}

}

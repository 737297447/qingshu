package com.lingdian.saylove.tools;

import java.io.File;
import java.io.FilenameFilter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public class FileChange {

	
	public static Bitmap getBitmap(Context context, String resName) { 
        try {
        	 return BitmapFactory.decodeFile(resName);
        } catch (Exception e) {
           return null;
        }
    }
	
	public static Drawable getDrawable(Context context, String resName){
		Bitmap bitmap = getBitmap(context, resName);
		return new BitmapDrawable(bitmap);
	}
	
	public static FilenameFilter getFileExtensionFilter(String extension) {   
	    final String _extension = extension;   
	    return new FilenameFilter() {   
	        public boolean accept(File file, String name) {   
	            boolean ret = name.endsWith(_extension);    
	            return ret;   
	        }   
	    };   
	}
	
}

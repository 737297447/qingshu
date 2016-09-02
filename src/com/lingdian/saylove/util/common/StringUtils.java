package com.lingdian.saylove.util.common;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import android.annotation.SuppressLint;
import android.content.ClipboardManager;
import android.content.Context;
import android.util.Log;

public class StringUtils {

	/**
	 * 字符串转整数
	 * 
	 * @param str
	 * @param defValue
	 * @return
	 */
	public static int toInt(String str, int defValue) {
		try {
			return Integer.parseInt(str);
		} catch (Exception e) {
		}
		return defValue;
	}

	/**
	 * 对象转整数
	 * 
	 * @param obj
	 * @return 转换异常返回 0
	 */
	public static int toInt(Object obj) {
		if (obj == null)
			return 0;
		return toInt(obj.toString(), 0);
	}

	/**
	 * 判断给定字符串是否空白串。 空白串是指由空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串，返回true
	 * 
	 * @param input
	 * @return boolean
	 */
	public static boolean isEmpty(String input) {
		if (input == null || "".equals(input))
			return true;

		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
				return false;
			}
		}
		return true;
	}

	public static String clearString(String content) {
		if (content.indexOf("<img") != -1) {
			int i = content.indexOf("<img");
			int j = content.indexOf("/>");
			content = content.substring(0, i) + content.substring(j + 2, content.length());
		}

		if (content.indexOf("<div") != -1) {
			int i = content.indexOf("<div");
			int j = content.indexOf("</div>");
			content = content.substring(0, i) + content.substring(j + 6, content.length());
		}

		return content;
	}

	public static Long getlongDate() {
		long str = System.currentTimeMillis() / 1000;
		return str;
	}

	public static String getDate() {
		SimpleDateFormat sDateFormat = new SimpleDateFormat("hh:mm");
		String date = sDateFormat.format(new java.util.Date());
		return date;
	}

	/**
	 * URL编码转string字符串
	 * 
	 * @param URL编码
	 */
	public static String URLDecoder(String code) {
		if (code == null || code.equals("")) {
			return null;
		}
		String urlStr = null;
		try {
			urlStr = URLDecoder.decode(code, "gb2312");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return urlStr;
	}

	/**
	 * URL编码转string字符串
	 * 
	 * @param URL编码
	 */
	public static String TimeProcess(String time) {
		if (time == null || time.equals("")) {
			return null;
		}
		time = time.replace("T", " ");
		int point = time.lastIndexOf('.');
		time = time.substring(0, point);
		return time;
	}

	/**
	 * 时间处理
	 * 
	 * @param
	 */

	public static String getTime(String timeStr) {
		if (StringUtils.isEmpty(timeStr)) {
			return "";
		}
		timeStr = timeStr.replace("T", " ");
		int point = timeStr.lastIndexOf(':');
		if (point != -1) {
			return timeStr.substring(0, point);
		} else {
			return timeStr;
		}
	}

	public static String TimeProcessTODAY(String timeStr) {
		if (StringUtils.isEmpty(timeStr)) {
			return "";
		}
		return timeStr.substring(0, 10);
	}

	/**
	 * 获取日期类型的字符串表示
	 * 
	 * @param date
	 * @param dateformat
	 * @return
	 */
	@SuppressLint("SimpleDateFormat")
	public static String getDateStr(Date date, String dateformat) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(dateformat);
		String hehe = dateFormat.format(date);
		return hehe;
	}

	/**
	 * 　是否是IP地址
	 * 
	 * @param addr
	 * @return
	 */
	public static boolean isIP(String addr) {
		if (addr.length() < 7 || addr.length() > 15 || "".equals(addr)) {
			return false;
		}
		/** 判断IP格式和范围 */
		String rexp =
				"([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";
		Pattern pat = Pattern.compile(rexp);
		Matcher mat = pat.matcher(addr);
		boolean ipAddress = mat.find();
		return ipAddress;
	}

	/**
	 * 手机号验证
	 * 
	 * @param str
	 * @return 验证通过返回true
	 */
	public static boolean isMobile(String str) {
		Pattern p = null;
		Matcher m = null;
		boolean b = false;
		p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号
		m = p.matcher(str);
		b = m.matches();
		return b;
	}

	/**
	 * 电话号码验证
	 * 
	 * @param str
	 * @return 验证通过返回true
	 */
	public static boolean isPhone(String str) {
		Pattern p1 = null;
		Matcher m = null;
		boolean b = false;
		p1 = Pattern.compile("^[0][0-9]{2,3}[0-9]{5,10}$"); // 验证带区号的
		if (str.length() > 9) {
			m = p1.matcher(str);
			b = m.matches();
		}
		return b;
	}

	/**
	 * 阿拉伯数字转为中文小写
	 * 
	 * @param si
	 */
	public static String transition(String si) {
		String[] aa = { "", "十", "百", "千", "万", "十万", "百万", "千万", "亿", "十亿" };
		String[] bb = { "一", "二", "三", "四", "五", "六", "七", "八", "九" };
		char[] ch = si.toCharArray();
		int maxindex = ch.length;
		// 字符的转换
		// 两位数的特殊转换
		if (maxindex == 2) {
			for (int i = maxindex - 1, j = 0; i >= 0; i--, j++) {
				if (ch[j] != 48) {
					if (j == 0 && ch[j] == 49) {
						return aa[i];
					} else {
						return bb[ch[j] - 49] + aa[i];
					}
				}
			}
			// 其他位数的特殊转换，使用的是int类型最大的位数为十亿
		} else {
			for (int i = maxindex - 1, j = 0; i >= 0; i--, j++) {
				if (ch[j] != 48) {
					return bb[ch[j] - 49] + aa[i];
				}
			}
		}
		return si;
	}

	/**
	 * ʵ复制 add by wangqianzhou
	 * 
	 * @param content
	 */
	@SuppressLint("NewApi")
	@SuppressWarnings("deprecation")
	public static void copy(String content, Context context) {
		ClipboardManager cmb =
				(ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
		Log.d("nzl","copy:" + content);
		cmb.setText(content.trim());
	}

	// 判断是否复制了字符串
	@SuppressWarnings("deprecation")
	@SuppressLint("NewApi")
	public static boolean isHasStr(Context context) {
		ClipboardManager cmb =
				(ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
		return cmb.hasText();
	}

	/**
	 * 粘贴 
	 * 
	 * @param context
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@SuppressLint("NewApi")
	public static String paste(Context context) {
		ClipboardManager cmb =
				(ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
		return cmb.getText().toString().trim();
	}
}

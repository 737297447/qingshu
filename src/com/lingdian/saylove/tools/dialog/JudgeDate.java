package com.lingdian.saylove.tools.dialog;

import java.text.SimpleDateFormat;

public class JudgeDate {

	/**
	 * 判断是否为合法的日期时间字符�?
	 * 
	 * @param str_input
	 * @param str_input
	 * @return boolean;符合为true,不符合为false
	 */
	public static boolean isDate(String str_input, String rDateFormat) {
		if (!isNull(str_input)) {
			SimpleDateFormat formatter = new SimpleDateFormat(rDateFormat);
			formatter.setLenient(false);
			try {
				formatter.format(formatter.parse(str_input));
			} catch (Exception e) {
				return false;
			}
			return true;
		}
		return false;
	}

	public static boolean isNull(String str) {
		if (str == null)
			return true;
		else
			return false;
	}
}
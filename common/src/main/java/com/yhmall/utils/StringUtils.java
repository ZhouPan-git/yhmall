package com.yhmall.utils;

/**
 * @author Nick
 * @Classname StringUtils
 * @Date 2023/09/14 19:51
 * @Description
 */
public class StringUtils {
	public static boolean contains(String str, String searchStr) {
		if (str == null || searchStr == null) {
			return false;
		}
		return str.indexOf(searchStr) >= 0;
	}
}
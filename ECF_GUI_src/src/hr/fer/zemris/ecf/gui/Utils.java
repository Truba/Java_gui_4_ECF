package hr.fer.zemris.ecf.gui;

import hr.fer.zemris.ecf.param.Entry;

import java.util.List;

/**
 * Collection of static methods that are often used.
 * @author Domagoj
 *
 */
public class Utils {

	public static final String LOG_EXT = ".logext";

	public static String addBeforeExtension(String dest, String src) {
		int dotIndex = dest.lastIndexOf(".");
		int separatorIndex = Math.max(dest.lastIndexOf("\\"), dest.lastIndexOf("/"));
		if (separatorIndex > dotIndex) {
			return dest + src;
		}
		return dest.substring(0, dotIndex) + src + dest.substring(dotIndex);
	}
	
	public static String addBeforeExtension(String dest, int num, int len) {
		StringBuilder sb = new StringBuilder("_");
		int n = len - String.valueOf(num).length();
		for (int i = 0; i < n; i++) {
			sb.append('0');
		}
		sb.append(num);
		return addBeforeExtension(dest, sb.toString());
	}
	
//	public static int extractRepeatsNum(String str) {
//		int minusIndex = str.lastIndexOf("-r_");
//		if (minusIndex < 0) {
//			return -1;
//		}
//		int separatorIndex = Math.max(str.lastIndexOf("\\"), str.lastIndexOf("/"));
//		if (minusIndex < separatorIndex) {
//			return -1;
//		}
//		String repStr = str.substring(minusIndex + 3);
//		int dotIndex = repStr.lastIndexOf(".");
//		if (dotIndex < 0) {
//			return Integer.parseInt(repStr);
//		}
//		String temp = repStr.substring(0, dotIndex);
//		return Integer.parseInt(temp);
//	}
	
	public static Entry findEntry(List<Entry> list, String key) {
		for (Entry e : list) {
			if (e.key.equals(key)) {
				return e;
			}
		}
		return null;
	}
	
}

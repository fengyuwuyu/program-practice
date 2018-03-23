package com.ll.program.practice.utils;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

public class StringUtil {
	public static final String EMPTY = "";
	public static final String COMMA = ",";
	public final static String UNDERLINE = "_";
	public final static String t = "t";
	public final static String T = "T";
	public final static String NULL = "null";
	public final static String COLON = ":";
	protected final static char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
	protected final static String HEX_CHARS = "0123456789abcdefABCDEF";
	protected final static byte[] HEX_VALUES = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 10, 11, 12, 13,
			14, 15 };

	protected final static byte HEX_BYTES[] = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0,
			0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	protected final static byte NUM_BYTES[] = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0,
			0, 0, 0, 0 };

	public static boolean isDecString(String string) {
		if (isNullEmpty(string)) {
			return false;
		}
		for (int i = 0; i < string.length(); i++) {
			char ch = string.charAt(i);
			if (ch > 63 || NUM_BYTES[ch] == 0) {
				return false;
			}
		}
		return true;
	}

	public static boolean isHexString(String string) {
		if (isNullEmpty(string)) {
			return false;
		}
		int len = string.length();
		if (len % 2 == 0) {
			for (int i = 0; i < string.length(); i++) {
				char ch = string.charAt(i);
				if (ch > 127 || HEX_BYTES[ch] == 0) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	public static String bytesToHexString(byte[] bytes) {
		if (bytes == null) {
			return "";
		}
		char[] hexChars = new char[bytes.length * 2];
		for (int j = 0; j < bytes.length; j++) {
			int v = bytes[j] & 0xFF;
			hexChars[j * 2] = HEX_ARRAY[v >>> 4];
			hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
		}
		return new String(hexChars);
	}

	public static String LongToHexString(long value) {
		int len = 16;
		char[] hexChars = new char[len];
		for (int j = 0; j < 8; j++) {
			int v = (int) value & 0xFF;
			hexChars[(7 - j) << 1] = HEX_ARRAY[v >>> 4];
			hexChars[((7 - j) << 1) + 1] = HEX_ARRAY[v & 0x0F];
			value = (value >>> 8);
		}
		return new String(hexChars);
	}

	public static String LongToHexString(long high, long low) {
		int len = 32;
		char[] hexChars = new char[len];

		for (int j = 0; j < 8; j++) {
			int hv = (int) high & 0xFF;
			int lv = (int) low & 0xFF;
			hexChars[(7 - j) << 1] = HEX_ARRAY[hv >>> 4];
			hexChars[((7 - j) << 1) + 1] = HEX_ARRAY[hv & 0x0F];
			hexChars[((7 - j) << 1) + 16] = HEX_ARRAY[lv >>> 4];
			hexChars[((7 - j) << 1) + 1 + 16] = HEX_ARRAY[lv & 0x0F];
			high = (high >>> 8);
			low = (low >>> 8);
		}
		return new String(hexChars);
	}

	public static String LongToHexString(long high,long middle,long low){
		int len = 48;
		char[] hexChars = new char[len];

		for (int j = 0; j < 8; j++) {
			int hv = (int) high & 0xFF;
			int mv = (int) middle & 0xFF;
			int lv = (int) low & 0xFF;
			hexChars[(7 - j) << 1] = HEX_ARRAY[hv >>> 4];
			hexChars[((7 - j) << 1) + 1] = HEX_ARRAY[hv & 0x0F];
			hexChars[((7 - j) << 1) + 16] = HEX_ARRAY[mv >>> 4];
			hexChars[((7 - j) << 1) + 1 + 16] = HEX_ARRAY[mv & 0x0F];
			hexChars[((7 - j) << 1) + 32] = HEX_ARRAY[lv >>> 4];
			hexChars[((7 - j) << 1) + 1 + 32] = HEX_ARRAY[lv & 0x0F];
			high = (high >>> 8);
			middle = (middle >>> 8);
			low = (low >>>8);
		}
		return new String(hexChars);
	}
	
	public static byte[] hexStringToBytes(String hexString) {
		if (hexString == null) {
			return null;
		}

		if (hexString.length() % 2 != 0) {
			return null;
		}
		try {
			byte[] bytes = new byte[hexString.length() / 2];
			for (int i = 0; i < hexString.length() / 2; i++) {
				char ch = hexString.charAt(i * 2);
				bytes[i] = (byte) (HEX_VALUES[HEX_CHARS.indexOf(ch)] << 4);

				ch = hexString.charAt(i * 2 + 1);
				bytes[i] |= (byte) (HEX_VALUES[HEX_CHARS.indexOf(ch)]);
			}

			return bytes;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 
	 * @Description 鎶奡tring绫诲瀷鐨勫�艰浆鍖栦负int绫诲瀷
	 * @param str
	 * @return 濡傛灉杞崲澶辫触锛岃繑鍥�0锛�
	 * @author Terry
	 * @time 2016骞�5鏈�11鏃�
	 */
	public static int valueOfInt(String str) {
		int result = 0;
		if (!isNullEmpty(str)) {
			try {
				result = Integer.parseInt(str);
			} catch (Exception e) {
				e.printStackTrace();
				result = 0;
			}
		}
		return result;
	}

	public static Integer valueOfInteger(String str) {
		Integer result = null;
		if (!isNullEmpty(str)) {
			result = Integer.parseInt(str);
		}
		return result;
	}

	/**
	 * @Description 鍒ゆ柇瀛楃涓叉槸鍚︿负null鎴杄mpty
	 * @param str
	 * @return
	 * @author Terry
	 * @time 2016骞�5鏈�11鏃�
	 */
	public static boolean isNullEmpty(String str) {
		return str == null || str.isEmpty();
	}

	/**
	 * 
	 * 姝ゆ柟娉曟槸灏嗗瓧绗︿覆杞寲涓簂ong绫诲瀷鐨勬暟
	 *
	 * @return 濡傛灉杞崲澶辫触锛岃繑鍥�0锛�
	 * @author Terry
	 * @time 2016骞�5鏈�11鏃�
	 */
	public static long valueOfLong(String str) {
		long result = 0;
		if (!isNullEmpty(str)) {
			try {
				result = Long.parseLong(str);
			} catch (Exception e) {
				result = 0;
			}
		}
		return result;
	}

	public static Short valueOfShort(String str) {
		Short result = 0;
		if (!isNullEmpty(str)) {
			result = Short.parseShort(str);
		}
		return result;
	}

	/**
	 * 
	 * 姝ゆ柟娉曟槸鏍规嵁瀛楃涓插垽鏂槸false寮�杩樻槸true,浼犲叆鐨勫瓧绗︿覆濡傛灉鏄互t,T锛屽紑澶存垨绛変簬 1锛岄兘瑙嗕负true
	 *
	 * @param str
	 * @return
	 * @author Terry
	 * @time 2016骞�5鏈�11鏃�
	 */
	public static boolean valueOfBoolean(String str) {
		boolean flag = false;
		if (!isNullEmpty(str)) {
			if (str.startsWith(t) || str.startsWith(T) || str.equals("1")) {
				flag = true;
			}
		}
		return flag;
	}

	public static byte valueOfByte(String str) {
		byte result = 0;
		if (!isNullEmpty(str)) {
			result = Byte.parseByte(str);
		}
		return result;
	}

	public static double valueOfDouble(String str) {
		double result = 0;
		if (!isNullEmpty(str)) {
			result = Double.parseDouble(str);
		}
		return result;
	}

	/**
	 *
	 * <p>
	 * Title: firstToUpper
	 * </p>
	 * <p>
	 * Description:鎶婂瓧绗︿覆杞寲涓洪瀛楁瘝澶у啓鐨勫瓧绗︿覆
	 * </p>
	 *
	 * @param str
	 * @return
	 * @author guangshuai.wang
	 */
	public static String firstToUpper(String str) {
		return str.replaceFirst(str.substring(0, 1), str.substring(0, 1).toUpperCase());
	}

	public static String firstToLower(String str) {
		return str.replaceFirst(str.substring(0, 1), str.substring(0, 1).toLowerCase());
	}

	/**
	 * 
	 * 姝ゆ柟娉曟槸鑾峰彇涓�涓敮涓�鐨剈uid
	 *
	 * @return
	 * @author Terry
	 * @time 2016骞�6鏈�6鏃�
	 */
	public static String getUUid() {
		return UUID.randomUUID().toString();
	}

	/**
	 * 
	 * 鎻忚堪:鑾峰彇涓�涓ぇ鍐欑殑娌℃湁鐭嚎鐨�32浣嶅彲瑙嗗寲UUID
	 *
	 * @return
	 * @author Cai
	 * @time 2016骞�9鏈�8鏃�-涓嬪崍5:36:21
	 */
	public static String getUuidUpper() {
		return getUUid().replaceAll("-", "").toUpperCase();
	}

	public static Map<String, String> splitStrToMap(String[] args) {
		Map<String, String> map = CollectionUtil.createHashMap();
		if (args != null && args.length > 0) {
			for (String arg : args) {
				String[] keyValues = arg.split(":");
				if (keyValues.length == 2) {
					map.put(keyValues[0], keyValues[1]);
				}
			}
		}
		return map;
	}

	/**
	 * 
	 * 鎻忚堪:杩欎釜鏂规硶鏄皢涓�涓笅鍒掔嚎鍒嗛殧鐨勫瓧绗︿覆杞寲涓洪瀛楁瘝澶у啓鐨勯┘宄板懡鍚嶅瓧绗︿覆
	 *
	 * @param tableName
	 * @return
	 * @author Terry
	 * @time 2016骞�8鏈�6鏃�-涓嬪崍4:46:53
	 */
	public static String toCamelCase(String field) {
		field = field.toLowerCase();
		String[] strs = field.split(UNDERLINE);
		StringBuilder className = new StringBuilder();
		if (strs.length >= 2) {

			for (String str : strs) {

				className.append(StringUtil.firstToUpper(str));
			}
		} else {

			className.append(StringUtil.firstToUpper(field));
		}
		return className.toString();
	}

	public static byte[] toBytes(String str) {
		return str.getBytes();
	}

	public static String fromBytes(byte[] str) {
		return new String(str);
	}

	/**
	 * 
	 * @Desc 描述：检测某个字符串是否都是数字
	 * @param value
	 * @return
	 * @author 王广帅
	 * @Date 2017年6月8日 下午7:49:27
	 *
	 */
	public static boolean checkNumber(String value) {
		return isDecString(value);
	}

	public static boolean isHttpUrl(String value) {
		if (value == null || value.isEmpty()) {
			return false;
		}

		Pattern pattern = Pattern
				.compile("^([hH][tT]{2}[pP]://|[hH][tT]{2}[pP][sS]://)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~\\/])+$");
		return pattern.matcher(value).matches();
	}
	public static String byteToUnit(long value) {
		String result = null;
		int k = 1024;
		long temp = value / (k * k * k);
		if (temp != 0) {
			double d = (value * 1.0d) / (k * k * k);
			BigDecimal   b   =   new   BigDecimal(d);
			d = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			result = String.valueOf(d);
			return result + "G";
		}
		temp = value / (k * k);
		if (temp != 0) {
			double d = (value * 1.0d) / (k * k);
			BigDecimal   b   =   new   BigDecimal(d);
			d = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		
			result = String.valueOf(d);
			return result + "M";
		}
		temp = value / k;
		if (temp != 0) {
			double d = (value * 1.0d) / k;
			BigDecimal   b   =   new   BigDecimal(d);
			d = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		
			result = String.valueOf(d);
			return result + "K";
		}
		return value + "B";
	}

	public static long getByteUnit(String value) {
		if (value == null || value.isEmpty()) {
			return 0;
		}
		value = value.toLowerCase();
		long result = 0;
		result = getNumValue(value);
		if (value.endsWith("k")) {
			result = result * 1024;
		} else if (value.endsWith("m")) {
			result = result * 1024 * 1024;
		} else if (value.endsWith("g")) {
			result = result * 1024 * 1024 * 1024;
		}
		return result;
	}

	private static long getNumValue(String value) {
		if (value.length() == 1) {
			return StringUtil.valueOfLong(value);
		}
		String numbValue = value.substring(0, value.length() - 1);
		return StringUtil.valueOfLong(numbValue);
	}

	public static void main(String[] args) {
		System.out.println(checkNumber("123a"));
	}

}

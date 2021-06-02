package com.breezelin.freshmarket.utils;

import android.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import java.security.MessageDigest;

/**
 * 负责加密解密、计算签名的工具类。
 */
public final class SignUtil {

	private SignUtil() {
		// 私有化构造
	}

	/**
	 * 用于加密数字签名时进行字符比对的集合，盛装16进制中的字符。
	 */
	private static final char alphas[];
	/**
	 * 盛装了加密后的共有密钥的数组
	 */
	private static final byte keyXor[];

	/**
	 * 消息加密密钥的解码字串
	 */
	public static final String STR_KEY_SIGN_HEX =
			"879BCDCC81918B9099C4CADF9F979E9F859ECDC984918E9099C1CFDA9A979E9A8599CCC882948A9198C7" +
					"CBDE9D979899879BC8CE83938A9192C0C9D99F979F9585";
	/**
	 * 用于加密解密用户名和密码的密钥
	 */
	public static final String STR_KEY_CRYPT_HEX =
			"879FC8CB80958B9599C1CADF9F979B9A8190CBCC8E9F8B9198C0CADE9F939E9B8F";
	/**
	 * 用于生成密码的向量
	 */
	public static final String STR_KEY_IV =
			"879BCDCC84918B9099C4CADF9A979E9F859AC7C98F978C9098C7C8D99E9D959F84";

	/**
	 * 用于计算数字签名的方法。
	 *
	 * @param string 请求用的json字符串
	 * @return string 数字签名
	 */
	public static String calcSign(String string) {

		String s2 = null;
		try {

			// 获取消息摘要算法
			MessageDigest messagedigest = MessageDigest.getInstance("MD5");

			// 利用内置算法计算出消息摘要需要的密钥
			String key = a(STR_KEY_SIGN_HEX, new String(keyXor));

			// 将密钥置于JSON字串后，并将数据转换为byte数组
			byte[] bytes = (new StringBuilder(String.valueOf(string))).append(key).toString()
					.getBytes("utf-8");

			// 进行消息摘要
			messagedigest.update(bytes);
			byte[] digest = messagedigest.digest();

			// 将消息摘要转换为16进制字符串，并小写化以便写入GET请求头中
			s2 = toHex(digest).toLowerCase();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return s2;

	}

	/**
	 * 用于计算消息摘要的密钥的算法。
	 *
	 * @param string 解密字串
	 * @param aesKey 加密后的密钥
	 * @return 返回解密后的密钥
	 */
	private static String a(String string, String aesKey) {

		byte[] ret;

		int i = 1;
		int j;
		int k;
		int i1;

		byte[] byte0;
		byte[] key;
		byte[] byte2;
		byte[] byte3;
		byte[] byte4;

		try {
			if (string.length() > 0) {

				int b0Len = string.length() / 2;
				byte0 = new byte[b0Len];
				for (k = 0; k < b0Len; k++) {

					int l = Integer.parseInt(string.substring(k * 2, 1 + k * 2), 16);
					byte0[k] = (byte) (Integer.parseInt(string.substring(1 + k * 2, 2 + k * 2), 16)
							+ (l << 4));
				}
				key = byte0;

				byte2 = aesKey.getBytes("utf-8");
				byte3 = new byte[key.length];
				for (i1 = 0; i1 < key.length; i1++) {

					int j1 = i1 % byte2.length;
					//noinspection PointlessBitwiseExpression
					byte3[i1] = (byte) (-1 ^ (key[i1] ^ byte2[j1]));
				}
				if (byte3[0] != 0) {

					byte4 = new byte[-1 + byte3.length];
					while (i < byte3.length) {
						byte4[i - 1] = byte3[i];
						i++;
					}
				} else {

					byte[] byte6;
					int k1;
					byte6 = new byte[2 * (-1 + byte3.length)];
					for (k1 = i; k1 < byte3.length; k1++) {

						byte6[2 * (k1 - 1)] = (byte) ((0xf0 & byte3[k1]) >> 4);
						byte6[-1 + k1 * 2] = (byte) (0xf & byte3[k1]);
					}
					byte4 = byte6;
				}

				ret = new byte[byte4.length / 2];
				for (j = 0; j < ret.length; j++)
					ret[j] = (byte) ((byte4[j] << 4) + byte4[-1 + (byte4.length - j)]);

				return new String(ret, "utf-8");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * 转换数据为16进制
	 *
	 * @param bytes 比特数组
	 * @return 返回转换好的数据
	 */
	private static String toHex(byte[] bytes) {
		StringBuilder stringbuilder = new StringBuilder(2 * bytes.length);
		int i = 0;
		do {
			if (i >= bytes.length)
				return stringbuilder.toString();
			stringbuilder.append(alphas[(0xf0 & bytes[i]) >>> 4]);
			stringbuilder.append(alphas[0xf & bytes[i]]);
			i++;
		} while (true);
	}

	/**
	 * 加密用户名和密码的方法
	 *
	 * @param s 用户名或密码的字符串
	 * @return 返回加密后的字符串
	 */
	public static String encrypt(String s) {
		String s1 = null;
		if (s == null || s.equals("")) {
			s1 = "";
		} else {
			try {

				Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
				//noinspection ConstantConditions
				cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(a(STR_KEY_CRYPT_HEX,
								new String(keyXor)).getBytes("utf-8"), "AES"),
						new IvParameterSpec(a(STR_KEY_IV, new String(keyXor)).getBytes("utf-8")));

				// Base64 编码返回值
				s1 = Base64.encodeToString(cipher.doFinal(s.getBytes("UTF-8")), Base64.NO_WRAP);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return s1;
	}

	/**
	 * 解密用户名和密码的方法
	 *
	 * @param s 用户名或密码加密后的字符串
	 * @return 返回解密后的字符串
	 */
	public static String decrypt(String s) {
		String s1 = null;
		if (s == null || s.equals("")) {
			s1 = "";
		} else {
			try {

				Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
				//noinspection ConstantConditions
				cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(a(STR_KEY_CRYPT_HEX,
						new String(keyXor)).getBytes("utf-8"), "AES"), new
						IvParameterSpec(a(STR_KEY_IV, new String(keyXor)).getBytes("utf-8")));

				// Base64 解码返回值
				s1 = new String(cipher.doFinal(Base64.decode(s, Base64.NO_WRAP)), "UTF-8");
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return s1;
	}

	/**
	 * 用于初始化两个数组的静态代码
	 */
	static {
		char[] ac = new char[16];
		ac[0] = '0';
		ac[1] = '1';
		ac[2] = '2';
		ac[3] = '3';
		ac[4] = '4';
		ac[5] = '5';
		ac[6] = '6';
		ac[7] = '7';
		ac[8] = '8';
		ac[9] = '9';
		ac[10] = 'A';
		ac[11] = 'B';
		ac[12] = 'C';
		ac[13] = 'D';
		ac[14] = 'E';
		ac[15] = 'F';
		alphas = ac;

		//  yg10xmwle86#fkbc 密钥本体
		byte[] bytes = new byte[16];
		bytes[0] = 121;
		bytes[1] = 103;
		bytes[2] = 49;
		bytes[3] = 48;
		bytes[4] = 120;
		bytes[5] = 109;
		bytes[6] = 119;
		bytes[7] = 108;
		bytes[8] = 101;
		bytes[9] = 56;
		bytes[10] = 54;
		bytes[11] = 35;
		bytes[12] = 102;
		bytes[13] = 107;
		bytes[14] = 98;
		bytes[15] = 99;
		keyXor = bytes;
	}
}


package com.breezelin.fmlib.utils;

/**
 * Created by Breeze Lin
 * 18:42 2016/4/5
 * 1linyufeng1@gmail.com
 */

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

/**
 * 数据流操作类
 */
public final class StreamUtils {

	/**
	 * 日志用标签
	 */
	private static final String TAG = "StreamUtils";

	private StreamUtils() {
		// 私有构造
	}

	/**
	 * 关闭流的方法
	 *
	 * @return 关闭结果。如果关闭发生错误或传入非流则返回失败
	 */
	public static boolean closeStream(Object stream) {
		if (stream != null) {
			try {
				// 检验流类型，分别关闭
				if (stream instanceof InputStream) {
					((InputStream) stream).close();
				} else if (stream instanceof OutputStream) {
					((OutputStream) stream).close();
				} else if (stream instanceof Reader) {
					((Reader) stream).close();
				} else if (stream instanceof Writer) {
					((Writer) stream).close();
				} else {
					// 并非流，返回失败
					return false;
				}
			} catch (IOException e) {
				FMLog.e(TAG, "Stream closing failed. " + e);
				e.printStackTrace();
				return false;
			}
		} else {
			FMLog.e(TAG, "Stream closing failed, a null reference. ");
			// 内容为空，返回关闭失败
			return false;
		}
		return true;
	}

	/**
	 * @param inputStream 输入流
	 * @return 读取到的内容长度
	 */
	public static byte[] readStream(InputStream inputStream) {

		// 初始化返回值
		byte[] ret = null;

		// 判空
		if (inputStream != null) {
			// 创建中转
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			// 进行读写
			try {
				readStream(inputStream, outputStream);
			} catch (IOException e) {
				FMLog.e(TAG, "Stream reading failed. " + e);
				e.printStackTrace();
			}
			ret = outputStream.toByteArray();
		}
		return ret;
	}

	/**
	 * 将一个输入流写入一个输出流
	 *
	 * @param inputStream  输入流
	 * @param outputStream 输出流
	 * @return 读取到的内容长度
	 * @throws IOException 有可能IO异常
	 */
	public static long readStream(InputStream inputStream, OutputStream outputStream) throws IOException {

		// 初始化返回值
		long ret = 0;
		// 判空
		if (inputStream != null && outputStream != null) {

			// 初始化转换用变量
			byte[] buffer = new byte[128];
			int length;

			// 进行读写
//			while (length != -1) {
//				length = inputStream.read(buffer);
//				outputStream.write(buffer, 0, length);
//				ret += length;
//			}
			while (true) {

				length = inputStream.read(buffer);
				// 没读到数据，跳出
				if (length == -1)
					break;

				outputStream.write(buffer, 0, length);
				ret += length;
			}
		}
		return ret;
	}
}
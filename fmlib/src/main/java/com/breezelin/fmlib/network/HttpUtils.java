package com.breezelin.fmlib.network;

/**
 * Created by Breeze Lin
 * 18:35 2016/4/5
 * 1linyufeng1@gmail.com
 */

import com.breezelin.fmlib.utils.FMLog;
import com.breezelin.fmlib.utils.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.zip.GZIPInputStream;

/**
 * 网络请求工具类
 */
public final class HttpUtils {

	/**
	 * 日志用标签
	 */
	private static final String TAG = "HttpUtils";

	/**
	 * 网络连接超时限制
	 */
	private static final int TIMEOUT_CONNECT = 5000;
	/**
	 * 网络读取超时限制
	 */
	private static final int TIMEOUT_READ = 60000;

	private HttpUtils() {
		// 私有化构造
	}

	// TODO: 2016/4/5 完成功能

	/**
	 * 进行get请求。<br/>
	 *
	 * @param url    访问域名
	 * @param params 参数
	 * @return 网络返回的数据
	 */
	public static byte[] getForByte(String url, HashMap<String, String> params) {

		// 初始化返回值
		byte[] ret = null;

		// 拼接地址
		//noinspection StringBufferMayBeStringBuilder
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(url).append('?');
		for (String key : params.keySet()) {
			stringBuffer.append(key).append('=').append(params.get(key)).append('&');
		}
		String path = stringBuffer.substring(0, stringBuffer.length() - 2);

		// 日志打印访问的网址
		FMLog.i(TAG, "GET request to " + path);

		// 开始请求
		HttpURLConnection connection = null;
		InputStream inputStream = null;
		try {
			// 创建URL
			URL targetUrl = new URL(path);
			// 开启链接
			connection = (HttpURLConnection) targetUrl.openConnection();
			// 设置连接属性
			connection.setRequestMethod("GET");
			initRequest(connection);

			// 进行连接
			connection.connect();
			// 确认成功响应码
			int responseCode = connection.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {

				// 进行相应
				inputStream = connection.getInputStream();
				// 查看是否为进行了压缩的数据
				String encoding = connection.getContentEncoding();
				if (encoding.equals("gzip")) {
					inputStream = new GZIPInputStream(inputStream);
				}
				// 读取输入流
				ret = StreamUtils.readStream(inputStream);
			} else {
				// 错误响应码打印
				FMLog.e("HttpUtils", "Illegal response code " + responseCode + ". ");
			}
		} catch (IOException e) {
			e.printStackTrace();
			FMLog.e("HttpUtils", "Request error. " + e);
		} finally {
			StreamUtils.closeStream(inputStream);
			if (connection != null) {
				connection.disconnect();
			}
		}

		return ret;
	}

	/**
	 * 进行post请求
	 *
	 * @param url     地址
	 * @param headers 头部参数
	 * @param params  用于post的参数
	 * @return 返回数据
	 */
	public static byte[] postForByte(String url, HashMap<String, String> headers,
	                                 HashMap<String, String> params) {

		byte[] data = null;
		if (params != null && params.size() > 0) {
			try {
				//noinspection StringBufferMayBeStringBuilder
				StringBuffer buffer = new StringBuffer();
				for (String key : params.keySet()) {
					buffer.append(key).append('=').append(params.get(key)).append("&");
				}
				data = buffer.substring(0, buffer.length() - 2).getBytes("utf-8");
			} catch (UnsupportedEncodingException e) {
				FMLog.e(TAG, "Wrong encoding in header. " + e);
				e.printStackTrace();
			}
		}
		return postForByte(url, headers, data);
	}

	/**
	 * 进行post请求
	 *
	 * @param url     地址
	 * @param headers 头部参数
	 * @param data    用于post的参数
	 * @return 返回数据
	 */
	public static byte[] postForByte(String url, HashMap<String, String> headers, byte[] data) {

		// 初始化返回值
		byte[] ret = null;

		// 拼接地址
		//noinspection StringBufferMayBeStringBuilder
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(url).append('?');
		for (String key : headers.keySet()) {
			stringBuffer.append(key).append('=').append(headers.get(key)).append('&');
		}
		String path = stringBuffer.substring(0, stringBuffer.length() - 1);

		// 日志打印访问的网址
		FMLog.i(TAG, "POST request to " + path);
		// 日志打印请求内容
		if (data != null) {
			try {
				FMLog.i(TAG, "POST data " + new String(data, "utf-8"));
			} catch (UnsupportedEncodingException e) {
				FMLog.e(TAG, "Wrong encoding in data. " + e);
				e.printStackTrace();
			}
		}

		// 开始请求
		HttpURLConnection connection = null;
		InputStream inputStream = null;
		OutputStream outputStream = null;
		try {

			// 创建URL
			URL targetUrl = new URL(path);
			// 开启链接
			connection = (HttpURLConnection) targetUrl.openConnection();
			// 设置连接属性
			connection.setRequestMethod("POST");
			initRequest(connection);

			// 开始连接
			connection.connect();
			// 输出数据
			if (data != null) {
				outputStream = connection.getOutputStream();
				outputStream.write(data);
				outputStream.flush();
			}

			// 确认成功响应码
			int responseCode = connection.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {

				// 进行响应
				inputStream = connection.getInputStream();
				// 查看是否为进行了压缩的数据
				String encoding = connection.getContentEncoding();
				if (encoding.equals("gzip")) {
					inputStream = new GZIPInputStream(inputStream);
				}
				// 读取输入流
				ret = StreamUtils.readStream(inputStream);
			}

		} catch (IOException e) {
			e.printStackTrace();
			FMLog.e("HttpUtils", "Request error. " + e);
		} finally {
			StreamUtils.closeStream(outputStream);
			StreamUtils.closeStream(inputStream);
			if (connection != null) {
				connection.disconnect();
			}
		}

		return ret;
	}

	/**
	 * 初始化连接参数
	 *
	 * @param connection 连接
	 */
	private static void initRequest(HttpURLConnection connection) {

		// 连接超时
		connection.setConnectTimeout(TIMEOUT_CONNECT);
		// 读取超时
		connection.setReadTimeout(TIMEOUT_READ);
		// 设置开启输入输出（post用）
		connection.setDoInput(true);
		connection.setDoOutput(true);

		// TODO: 2016/4/7 应当在抓包后进行设置的详细编写

		// TODO: 2016/4/17 待了解设置
		connection.setRequestProperty("Content-Type", "text/xml;charset=utf-8");
		// TODO: 2016/4/7 待了解设置
		connection.setRequestProperty("connection", "Keep-Alive");
		// 设置Host
		connection.setRequestProperty("Host", "mapi.yiguo.com");
		// 客户端的终端类型
		connection.setRequestProperty("User-Agent",
				"Dalvik/2.1.0 (Linux; U; Android 5.1.1; E6883 Build/32.0.A.6.209)");
		// 代表客户端能够接收服务器传回内容类型的格式
		connection.setRequestProperty("Accept", "text/xml;text/html;*/*");
		// 设置客户端支持的压缩格式（内容编码，对应服务器返回的字段）
		connection.setRequestProperty("Accept-Encoding", "gzip");
	}
}

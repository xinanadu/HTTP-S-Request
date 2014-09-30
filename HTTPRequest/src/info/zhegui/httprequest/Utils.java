package info.zhegui.httprequest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.content.Context;
import android.util.Log;

public class Utils {

	public static String httpsGet(Context context, String urlStr) {
		HttpURLConnection conn = null;
		InputStream in = null;
		ByteArrayOutputStream baos = null;
		String result = null;
		try {
			URL url = new URL(urlStr);
			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(10000);
			conn.setReadTimeout(10000);

			in = conn.getInputStream();
			baos = new ByteArrayOutputStream();
			byte[] buffer = new byte[512];
			int read = 0;
			while ((read = in.read(buffer, 0, buffer.length)) != -1) {
				baos.write(buffer, 0, read);
			}
			buffer = baos.toByteArray();
			result = new String(buffer, "utf-8");

			baos.close();
			baos = null;

			in.close();
			in = null;

			conn.disconnect();
			conn = null;

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (baos != null) {
				try {
					baos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				baos = null;
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				in = null;
			}

			if (conn != null) {
				conn.disconnect();
				conn = null;
			}
		}

		log(result);
		return result;
	}

	public static String httpGet(Context context, String urlStr) {
		HttpURLConnection conn = null;
		InputStream in = null;
		ByteArrayOutputStream baos = null;
		String result = null;
		try {
			URL url = new URL(urlStr);
			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(10000);
			conn.setReadTimeout(10000);

			in = conn.getInputStream();
			baos = new ByteArrayOutputStream();
			byte[] buffer = new byte[512];
			int read = 0;
			while ((read = in.read(buffer, 0, buffer.length)) != -1) {
				baos.write(buffer, 0, read);
			}
			buffer = baos.toByteArray();
			result = new String(buffer, "utf-8");

			baos.close();
			baos = null;

			in.close();
			in = null;

			conn.disconnect();
			conn = null;

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (baos != null) {
				try {
					baos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				baos = null;
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				in = null;
			}

			if (conn != null) {
				conn.disconnect();
				conn = null;
			}
		}

		log(result);
		return result;
	}
	
	private static void log(String msg){
		Log.e("Utils", msg);
	}
}

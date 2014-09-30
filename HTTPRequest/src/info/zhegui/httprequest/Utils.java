package info.zhegui.httprequest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import android.content.Context;
import android.util.Log;

public class Utils {

	public static String httpsGet(Context context, String urlStr) {
		HttpsURLConnection conn = null;
		InputStream in = null;
		ByteArrayOutputStream baos = null;
		String result = null;
		try {
			HttpsURLConnection
					.setDefaultHostnameVerifier(new HostnameVerifier() {

						@Override
						public boolean verify(String hostname,
								SSLSession session) {
							// TODO Auto-generated method stub
							return true;
						}

					});

			X509TrustManager tm = new X509TrustManager() {
				@Override
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}

				@Override
				public void checkClientTrusted(
						java.security.cert.X509Certificate[] chain,
						String authType)
						throws java.security.cert.CertificateException {

				}

				@Override
				public void checkServerTrusted(
						java.security.cert.X509Certificate[] chain,
						String authType)
						throws java.security.cert.CertificateException {

				}
			};

			SSLContext sslContext = SSLContext.getInstance("TLS");
			sslContext.init(null, new TrustManager[] { tm }, null);

			URL url = new URL(urlStr);
			conn = (HttpsURLConnection) url.openConnection();
			conn.setSSLSocketFactory(sslContext.getSocketFactory());
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
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyManagementException e) {
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

	private static void log(String msg) {
		Log.e("Utils", msg);
	}
}

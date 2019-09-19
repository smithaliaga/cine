package com.teamwork.cineperu.Util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.URL;
import java.util.HashMap;
import java.util.Map.Entry;

import org.springframework.http.HttpMethod;

import java.net.Proxy;

public class UtilHttp {

	private static boolean USE_PROXY = false;
	private static String PROXY_HOST = "127.0.0.1";
	private static int PROXY_PORT = 8080;

	public static String sendMessage(String url, String method, String parameters, String contentType,
			HashMap<String, String> parametersHeader, int timeOut) {
		String output = null;
		HttpURLConnection conn = null;
		try {
			URL obj = null;
			Proxy proxy = null;
			if (USE_PROXY && !(url.contains("localhost") || url.contains("127.0.0.1"))) {
				proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(PROXY_HOST, PROXY_PORT));
			}
			if (HttpMethod.POST.name() == method) {
				obj = new URL(url);
				if (proxy != null) {
					conn = (HttpURLConnection) obj.openConnection(proxy);
				} else {
					conn = (HttpURLConnection) obj.openConnection();
				}
				conn.setReadTimeout(timeOut);
				conn.setConnectTimeout(timeOut);
				conn.setRequestMethod(HttpMethod.POST.name());
				conn.setRequestProperty("Content-Type", contentType);
				if (parametersHeader != null) {
					for (Entry<String, String> item : parametersHeader.entrySet()) {
						conn.setRequestProperty(item.getKey(), item.getValue());
					}
				}
				conn.setDoOutput(true);

				OutputStream wr = null;
				try {
					wr = conn.getOutputStream();
					wr.write(parameters.getBytes());
					wr.flush();
				} catch (Exception ex) {
					ex.printStackTrace();
				} finally {
					if (wr != null) {
						wr.close();
					}
				}
			} else {
				obj = new URL(url + "?" + parameters);
				if (proxy != null) {
					conn = (HttpURLConnection) obj.openConnection(proxy);
				} else {
					conn = (HttpURLConnection) obj.openConnection();
				}
				conn.setReadTimeout(timeOut);
				conn.setConnectTimeout(timeOut);
				conn.setRequestMethod(HttpMethod.GET.name());
				conn.setRequestProperty("Content-Type", contentType);
				if (parametersHeader != null) {
					for (Entry<String, String> item : parametersHeader.entrySet()) {
						conn.setRequestProperty(item.getKey(), item.getValue());
					}
				}
			}

			int responseCode = conn.getResponseCode();
			// String responseMessage = con.getResponseMessage();

			if (responseCode == 200) {
				output = "";
				InputStreamReader isr = null;
				BufferedReader br = null;
				try {
					isr = new InputStreamReader(conn.getInputStream());
					br = new BufferedReader(isr);

					String fila = "";
					while ((fila = br.readLine()) != null) {
						output += fila;
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				} finally {
					if (br != null) {
						br.close();
					}
					if (isr != null) {
						isr.close();
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
		return output;
	}
}

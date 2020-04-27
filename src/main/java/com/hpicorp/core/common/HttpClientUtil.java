package com.hpicorp.core.common;

import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.http.MediaType;

import com.hpicorp.core.dto.ResponseBody;
import com.hpicorp.core.enums.LineApiUrl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HttpClientUtil {
	
	private static final Object INIT_FLAG = "INIT_FLAG";
	
	private static CloseableHttpClient httpClient;
	
	private static int timeout = 3;

	private HttpClientUtil() {}
	
	public static HttpClient generateClient(boolean isUseProxy) throws KeyManagementException, NoSuchAlgorithmException {
		synchronized (INIT_FLAG) {
			if(httpClient == null){
				SSLContext sslContext = SSLContext.getInstance("TLSv1.2");

				// set up a TrustManager that trusts everything
				sslContext.init(null, new TrustManager[] { new X509TrustManager() {
					public X509Certificate[] getAcceptedIssuers() {
						log.info("getAcceptedIssuers => {}", Calendar.getInstance().getTime());
						return null;
					}
					
					public void checkClientTrusted(X509Certificate[] certs, String authType) {
						log.info("checkClientTrusted => {}", Calendar.getInstance().getTime());
					}
					
					public void checkServerTrusted(X509Certificate[] certs, String authType) {
						log.info("checkServerTrusted => {}", Calendar.getInstance().getTime());
					}
				} }, new SecureRandom());
				
				SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
				
				Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
					    .register("http", PlainConnectionSocketFactory.getSocketFactory())
					    .register("https", sslsf)
					    .build();
				
				PoolingHttpClientConnectionManager pm = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
				pm.setMaxTotal(1200);
				pm.setDefaultMaxPerRoute(300);
				
				RequestConfig defaultRequestConfig;
				
				log.info("generateClient : isUseProxy = {}", isUseProxy);
				
				if (isUseProxy) {
					HttpHost proxy = new HttpHost(LineApiUrl.PROXY_URL.getValue(), LineApiUrl.PROXY_PORT.getIntValue());
					
					log.info("generateClient : proxy = {}", proxy);
					
					defaultRequestConfig = RequestConfig.custom()
						    .setProxy(proxy)
						    .setSocketTimeout(timeout  * 1000)
						    .setConnectTimeout(timeout  * 1000)
						    .setConnectionRequestTimeout(timeout  * 1000)
						    .build();
				}
				else {
					defaultRequestConfig = RequestConfig.custom()
						    .setSocketTimeout(timeout  * 1000)
						    .setConnectTimeout(timeout  * 1000)
						    .setConnectionRequestTimeout(timeout  * 1000)
						    .build();
				}
				
				
				httpClient = HttpClients.custom().setConnectionManager(pm).setDefaultRequestConfig(defaultRequestConfig).build();
			}
		}
		
		return httpClient; 
	}
	
	public static ResponseBody execute(HttpUriRequest request, boolean isUseProxy) {
		Integer statusCode = 400;
		InputStream inputStream = null;
		StringWriter writer = new StringWriter();
		try {
			HttpClient client = generateClient(isUseProxy);
			HttpResponse rsp =  client.execute(request);
			statusCode = rsp.getStatusLine().getStatusCode();
			HttpEntity rspEntity = rsp.getEntity();
			inputStream = rspEntity.getContent();
			IOUtils.copy(inputStream, writer, "utf-8");
			EntityUtils.consume(rspEntity);
		} catch (Exception e) {
			try {
				HttpClient client = generateClient(isUseProxy);
				HttpResponse rsp =  client.execute(request);
				statusCode = rsp.getStatusLine().getStatusCode();
				HttpEntity rspEntity = rsp.getEntity();
				inputStream = rspEntity.getContent();
				IOUtils.copy(inputStream, writer, "utf-8");
				EntityUtils.consume(rspEntity);
			} catch (Exception e1) {
				log.error("Execute Error twice => ", e1);
			}
		}
		return new ResponseBody(statusCode, writer.toString());
	}

	public static ResponseBody get(String url, Header[] headers, boolean isUseProxy) {
		HttpGet requestGet = new HttpGet(url);
		requestGet.setHeaders(headers);
		return execute(requestGet, isUseProxy);
	}
	
	public static ResponseBody postJSON(String url, String body, boolean isUseProxy) {
		Header[] headers = { new BasicHeader("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE) };
		return post(url, headers, new StringEntity(body, Charset.forName("utf-8")), isUseProxy);
	}
	
	public static ResponseBody postJSON(String url, String body, Header[] headers, boolean isUseProxy) {
		return post(url, headers, new StringEntity(body, Charset.forName("utf-8")), isUseProxy);
	}
	
	public static ResponseBody post(String url, Header[] headers, HttpEntity body, boolean isUseProxy) {
		log.info("準備發送的 url => {}, 發送時間為 => {}", url, Calendar.getInstance().getTime());
		Integer statusCode = 400;
		
		try {
			HttpPost requestPost = new HttpPost(url);
			requestPost.setHeaders(headers);
			requestPost.setEntity(body);
			
			return execute(requestPost, isUseProxy);
		} catch (Exception e) {
			log.error("Execute Error", e);
		}
		
		return new ResponseBody(statusCode, ""); 
	}
	
	public static ResponseBody put(String url, Header[] headers, String body, boolean isUseProxy) {
		HttpPut requestPut = new HttpPut(url);
		requestPut.setHeaders(headers);
		requestPut.setEntity(new StringEntity(body, Charset.forName("utf-8")));
		return execute(requestPut, isUseProxy);
	}

	public static ResponseBody delete(String url, Header[] headers, boolean isUseProxy) {
		HttpDelete requestDelete = new HttpDelete(url);
		requestDelete.setHeaders(headers);
		return execute(requestDelete, isUseProxy);
	}
	
	public static HttpEntity toUrlEncodeForm(Map<String, String> map) {
		try {
			List <NameValuePair> nvps = new ArrayList <>();
			for(Entry<String, String> entry: map.entrySet()) {
				nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
			return new UrlEncodedFormEntity(nvps, "utf-8");
		} catch (Exception e) {
			log.error("Convert To URL Encode Form Error", e);
		}
		return null;
	}
	
}

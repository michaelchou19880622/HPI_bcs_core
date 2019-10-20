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

import com.hpicorp.core.dto.ResponseDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HttpClientUtil {
	
	private static final Object INIT_FLAG = "INIT_FLAG";
	
	private static CloseableHttpClient httpClient;
	
	private static int timeout = 3;

	private HttpClientUtil() {}
	
	public static HttpClient generateClient() throws KeyManagementException, NoSuchAlgorithmException {
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
				
				RequestConfig defaultRequestConfig = RequestConfig.custom()
					    .setSocketTimeout(timeout  * 1000)
					    .setConnectTimeout(timeout  * 1000)
					    .setConnectionRequestTimeout(timeout  * 1000)
					    .build();
				
				httpClient = HttpClients.custom().setConnectionManager(pm).setDefaultRequestConfig(defaultRequestConfig).build();
			}
		}
		
		return httpClient; 
	}
	
	public static ResponseDto execute(HttpUriRequest request) {
		Integer statusCode = 400;
		InputStream inputStream = null;
		StringWriter writer = new StringWriter();
		try {
			HttpClient client = generateClient();
			HttpResponse rsp =  client.execute(request);
			statusCode = rsp.getStatusLine().getStatusCode();
			HttpEntity rspEntity = rsp.getEntity();
			inputStream = rspEntity.getContent();
			IOUtils.copy(inputStream, writer, "utf-8");
			EntityUtils.consume(rspEntity);
		} catch (Exception e) {
			try {
				HttpClient client = generateClient();
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
		return new ResponseDto(statusCode, writer.toString());
	}

	public static ResponseDto get(String url, Header[] headers) {
		HttpGet requestGet = new HttpGet(url);
		requestGet.setHeaders(headers);
		return execute(requestGet);
	}
	
	public static ResponseDto postJSON(String url, String body) {
		Header[] headers = { new BasicHeader("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE) };
		return post(url, headers, new StringEntity(body, Charset.forName("utf-8")));
	}
	
	public static ResponseDto postJSON(String url, String body, Header[] headers) {
		return post(url, headers, new StringEntity(body, Charset.forName("utf-8")));
	}
	
	public static ResponseDto post(String url, Header[] headers, HttpEntity body) {
		log.info("準備發送的 url => {}, 發送時間為 => {}", url, Calendar.getInstance().getTime());
		Integer statusCode = 400;
		try {
			HttpPost requestPost = new HttpPost(url);
			requestPost.setHeaders(headers);
			requestPost.setEntity(body);
			return execute(requestPost);
		} catch (Exception e) {
			log.error("Execute Error", e);
		}
		return new ResponseDto(statusCode, ""); 
	}
	
	public static ResponseDto put(String url, Header[] headers, String body) {
		HttpPut requestPut = new HttpPut(url);
		requestPut.setHeaders(headers);
		requestPut.setEntity(new StringEntity(body, Charset.forName("utf-8")));
		return execute(requestPut);
	}

	
	public static ResponseDto delete(String url, Header[] headers) {
		HttpDelete requestDelete = new HttpDelete(url);
		requestDelete.setHeaders(headers);
		return execute(requestDelete);
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

package com.hpicorp.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/*
 * 此檔案對應以下檔案
 * application-staging.properties
 */
@Component
@ConfigurationProperties(prefix = "environmentproperty")
public class EnvironmentProperties {

	private String finishOrderAddress;
	private String memberCenterAddress;
	private String paymentAddress;
	private String host;
	private String lineLoginCallbackPath;
	private String marketingSite;
	private String marketingSiteLogin;
	private String serverLoginSite;
	private String serverAddress;
	private String uploadDir;
	private String webhookServerAddress;

	public String getFinishOrderAddress() {
		return finishOrderAddress;
	}
	public void setFinishOrderAddress(String finishOrderAddress) {
		this.finishOrderAddress = finishOrderAddress;
	}
	

	public String getMemberCenterAddress() {
		return memberCenterAddress;
	}
	public void setMemberCenterAddress(String memberCenterAddress) {
		this.memberCenterAddress = memberCenterAddress;
	}
	public String getPaymentAddress() {
		return paymentAddress;
	}
	public void setPaymentAddress(String paymentAddress) {
		this.paymentAddress = paymentAddress;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}

	public String getLineLoginCallbackPath() {
		return lineLoginCallbackPath;
	}
	public void setLineLoginCallbackPath(String lineLoginCallbackPath) {
		this.lineLoginCallbackPath = lineLoginCallbackPath;
	}

	public String getMarketingSite() {
		return marketingSite;
	}
	public void setMarketingSite(String s) {
		this.marketingSite = s;
	}

	public String getMarketingSiteLogin() {
		return marketingSiteLogin;
	}
	public void setMarketingSiteLogin(String marketingSiteLogin) {
		this.marketingSiteLogin = marketingSiteLogin;
	}
	
	public String getServerLoginSite() {
		return serverLoginSite;
	}
	public void setServerLoginSite(String serverLoginSite) {
		this.serverLoginSite = serverLoginSite;
	}
	public String getServerAddress() {
		return serverAddress;
	}
	public void setServerAddress(String serverAddress) {
		this.serverAddress = serverAddress;
	}

	public String getUploadDir() {
		return uploadDir;
	}
	public void setUploadDir(String uploadDir) {
		this.uploadDir = uploadDir;
	}

	public String getWebhookServerAddress() {
		return webhookServerAddress;
	}
	public void setWebhookServerAddress(String webhookServerAddress) {
		this.webhookServerAddress = webhookServerAddress;
	}

}

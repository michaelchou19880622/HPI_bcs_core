package com.hpicorp.core.config;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tsib.RunBat;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class JpaConfig {

	@Value("${proj.environment}")
	private String projEnvironmenu;
	
	@Value("${db.connect.url}")
	private String db_connect_url;
	
	@Value("${db.connect.username}")
	private String db_connect_username;
	
	@Value("${db.connect.password}")
	private String db_connect_password;
	
	@Value("${db.connect.databaseName}")
	private String db_connect_databaseName;
	
	@Value("${ldapHost}")
	private String ldapHost;
	
	@Value("${apName}")
	private String apName;
	
	@Value("${searchBase}")
	private String searchBase;
	
	@Value("${apGroup}")
	private int apGroup;
	
	@Bean
    public DataSource getDataSource() 
    {
		log.info("JpaConfig getDataSource");
		
		log.info("projEnvironmenu = {}", projEnvironmenu);
		log.info("db_connect_url = {}", db_connect_url);
		log.info("db_connect_username = {}", db_connect_username);
		log.info("db_connect_password = {}", db_connect_password);
		log.info("db_connect_databaseName = {}", db_connect_databaseName);
		log.info("ldapHost = {}", ldapHost);
		log.info("apName = {}", apName);
		log.info("searchBase = {}", searchBase);
		log.info("apGroup = {}", apGroup);
		
		String connection = getDBConnectionInfo(ldapHost, apName, apGroup, searchBase);
		log.info("connection = {}", connection);
		
		String[] split = connection.split(";");
		
		for (String str : split) {
			if (StringUtils.isNotBlank(str)) {
				String[] keyvalue = str.split("=");

				if (keyvalue != null && keyvalue.length == 2) {
					if ("uid".equals(keyvalue[0])) {
						db_connect_username = keyvalue[1];
					}
					
					if ("pwd".equals(keyvalue[0])) {
						db_connect_password = keyvalue[1];
					}
					
					if ("database".equals(keyvalue[0])) {
						db_connect_databaseName = keyvalue[1];
					}
				}
			}
		}

        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dataSourceBuilder.url(db_connect_url);
        
		if (StringUtils.isNotBlank(db_connect_username)) {
			log.info("db_connect_username = {}", db_connect_username);
			
			dataSourceBuilder.username(db_connect_username);
		}

		if (StringUtils.isNotBlank(db_connect_password)) {
			log.info("db_connect_password = {}", db_connect_password);
			
			dataSourceBuilder.password(db_connect_password);
		}

		if (StringUtils.isNotBlank(db_connect_databaseName)) {
			log.info("db_connect_databaseName = {}", db_connect_databaseName);
			
			db_connect_url = db_connect_url.replace("{DATABASENAME}", db_connect_databaseName);
			log.info("db_connect_url = {}", db_connect_url);
			
			dataSourceBuilder.url(db_connect_url);
		}
		
        return dataSourceBuilder.build();
    }
	
	private String getDBConnectionInfo(String ldapHost, String apName, int apGroup, String searchBase) {
		try {
			RunBat ap1 = new RunBat();
			ap1.SSL = false;
			ap1.ldapHost = ldapHost;
			ap1.searchBase = searchBase;
			return ap1.GetRunBat(apName, apGroup);
		} catch (Exception e) {
			log.error("Exception = {}", e);
		}
		return "";
	}
	
}

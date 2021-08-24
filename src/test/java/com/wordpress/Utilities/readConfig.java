package com.wordpress.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class readConfig {
	
	Properties pro;
	public readConfig() {
		
		File src = new File("./Configuration/Config.properties");
		
		try {
			FileInputStream fis = new FileInputStream(src);
			pro = new Properties();
			pro.load(fis);
		} catch (FileNotFoundException e) {
			System.out.println("Exception is -> " + e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	/*** Methods ***/
	
	public String getUrl() {
		String url = pro.getProperty("url");
		return url;
	}

	public String getUsername() {
		String username = pro.getProperty("username");
		return username;
	}
	
	public String getPassword() {
		String password = pro.getProperty("password");
		return password;
	}
	
	public String getChromePath() {
		String chromePath = pro.getProperty("chromePath");
		return chromePath;
	}
	
	public String getLoginTitle() {
		String loginTitle = pro.getProperty("title");
		return loginTitle;
	}
}

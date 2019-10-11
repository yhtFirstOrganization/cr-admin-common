package com.crAdmin.util;

import java.io.InputStream;
import java.util.Properties;

public class MyConfig {
	public static String base;
	
	static {
		Properties props = new Properties();

		try {
			InputStream in = MyConfig.class
					.getResourceAsStream("/myconfig.properties");
			props.load(in);
			in.close();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		base = props.getProperty("base");
	}
}

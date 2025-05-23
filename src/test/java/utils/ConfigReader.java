package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;


import base.ProjectSpecificationMethods;

public class ConfigReader extends ProjectSpecificationMethods {
	static Properties properties = new Properties();
	static String projectPath = System.getProperty("user.dir");
	
	public static String get(String key) {
	try {
		FileInputStream file = new FileInputStream(projectPath+"\\Resources\\config.properties");
		properties.load(file);
	} catch (IOException e) {
		e.printStackTrace();
	}
	return (String) properties.get(key);
}
	public static void set(String key,String value) {
		try {
			FileOutputStream file = new FileOutputStream(projectPath+"\\Resources\\config.properties");
			properties.setProperty(key, value);
			properties.store(file, null);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

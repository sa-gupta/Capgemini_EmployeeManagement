package com.cg.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

public class ReadDbDetails {
	Properties props;
	String propFile = "lib/db.properties";
	Logger log = Logger.getLogger(ReadDbDetails.class);
	public ReadDbDetails() {
	}
	public ReadDbDetails(String propFile) {
		this.propFile = propFile;
	}
	public Map<String, String> getDbProp () throws EmployeeException{
		Map<String, String> map = new HashMap<>();
		props = new Properties();
		try {
			props.load(new FileInputStream(propFile));
			Enumeration en = props.propertyNames();
			while (en.hasMoreElements()) {
				String key = (String) en.nextElement();
				map.put(key, props.getProperty(key));
			}
		} catch (FileNotFoundException e) {
			log.error(EmpMessages.DB_PROP_FILE_NOT_FOUND);
			throw new EmployeeException(EmpMessages.DB_PROP_FILE_NOT_FOUND);
		} catch (IOException e) {
			log.error(EmpMessages.NOT_ABLE_TO_READ_FILE);
			throw new EmployeeException(EmpMessages.NOT_ABLE_TO_READ_FILE);
		}
		
		
		return map;
	}
	
}

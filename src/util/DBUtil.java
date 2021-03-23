package com.cg.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

import org.apache.log4j.Logger;

public class DBUtil {
	private static Connection conn;
	static Logger log = Logger.getLogger(DBUtil.class);
	public static Connection createConnection() throws EmployeeException {
		try {
			if (conn == null || conn.isClosed()) {
				Map<String, String> map = new ReadDbDetails().getDbProp();
				Class.forName(map.get("DRIVER"));
				conn = DriverManager.getConnection(map.get("URL"), map.get("USER"), map.get("PASSWORD"));
			}
		} catch (SQLException e) {
			log.error(EmpMessages.DB_ERROR);
			throw new EmployeeException(EmpMessages.DB_ERROR);
		} catch (ClassNotFoundException e) {
			log.error(EmpMessages.CLASS_NOT_FOUND);
			throw new EmployeeException(EmpMessages.CLASS_NOT_FOUND);
		}
		return conn;
	}

	public static void closeConnection() throws EmployeeException {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				log.error(EmpMessages.DB_ERROR);
				throw new EmployeeException(EmpMessages.DB_ERROR);
			}
		}
	}

}

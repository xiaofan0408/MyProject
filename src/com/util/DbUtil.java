package com.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbUtil {
	
	private static  String DRIVER = "com.mysql.jdbc.Driver";
    
	private static	String URL = "jdbc:mysql://localhost:3306/db_picmana?useUnicode=true&characterEncoding=utf-8";
	
	private static	String USERNAME = "root";
	
	private static	String PASSWORD = "xiaofan";
	
	public static Connection getConnection(){
		
		Connection connection = null;
		
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
			return connection;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}

}

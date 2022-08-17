package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * 專案上線使用 JNDI
 */
public class JdbcUtil {
	public static final String URL 
	= "jdbc:mysql://localhost:3306/cga103g1?useSSL=false&serverTimezone=UTC";
	public static final String USERNAME = "root";
	public static final String PASSWORD = "密碼";

}

	

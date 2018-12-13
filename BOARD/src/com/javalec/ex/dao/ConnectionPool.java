//package com.javalec.ex.dao;
//
//import java.sql.Connection;
//import javax.naming.*;
//import javax.sql.DataSource;
//
//public class ConnectionPool {
//	
//	private static Connection conn = null;
//	
//	public static Connection getConnection() {
//		if(conn != null) {
//			return conn;
//		} else {
//			try {
//				Context initContext = (Context)new InitialContext().lookup("java:comp/env/");
//				DataSource ds = (DataSource)initContext.lookup("jdbc/mysql");
//				conn = ds.getConnection();
//			} catch(Exception e) {
//				e.printStackTrace();
//			}
//			return conn;
//		}
//	}
//}

package com.ll.program.practice.thread;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ThreadLoacl {

	private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>(){

		@Override
		protected Connection initialValue() {
			Connection conn = null;
			try {
				conn = DriverManager.getConnection("", "", "");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return conn;
		}
		
	};
	
	public static Connection getConnection(){
		return threadLocal.get();
	}
}

package com.hk.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {
	
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver" ;
	private static final String USERS = "unionhb" ;
	private static final String PASSWORD = "hbwmunion";
	private static final String URL = "jdbc:oracle:thin@221.192.141.197:1521:orcl" ;
	private Connection conn = null ;
	
	static{
		try {
			Class.forName(DRIVER) ;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	} 
	
	public Connection getConn(){
		try {
			conn = DriverManager.getConnection(URL, USERS, PASSWORD) ;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn ;
	}
	
	public static void Close(Connection conn,PreparedStatement ps,ResultSet rs){
		try {
			if(rs != null){
				rs.close();
			}
			if(ps != null){
				ps.close();
			}
			if(conn != null && conn.isClosed()){
				conn.close();
			}
		}catch (SQLException e) {
				e.printStackTrace();
		}

	}

}

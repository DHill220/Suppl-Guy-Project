package com.aca.supplyguy.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MariaDBUtil {
	private static String connectionUrl = 
			"jdbc:mariadb://localhost:3306/supplyguylist?user=root&password=H3rsheyd@g";
	
	public static Connection getConnection() {
		Connection connection = null;
		
			try {
				Class.forName("org.mariadb.jdbc.Driver");
				connection = DriverManager.getConnection(connectionUrl);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		return connection;
	}

	public static void main(String[] args) {
		Connection connection = getConnection();
		if( null == connection) {
			System.out.println("Help. Connection is null");
		} else {
			System.out.println("A real connection!!");
		}
				
	}
	


}

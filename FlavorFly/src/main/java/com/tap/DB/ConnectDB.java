package com.tap.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
	public static Connection con=null;
	public static String url="jdbc:mysql://localhost:3306/restaurant";
	public static String uname="root";
	public static String password="bhashvika@2004";
       public static Connection getConnection() {
    	  try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(url, uname, password);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
		
    	  
      }
}

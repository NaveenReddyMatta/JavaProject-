package com.atm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AtmApp {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// 1.loading class driver
		System.out.println("loading !!");
		Class.forName("oracle.jdbc.driver.OracleDriver");
		//2.creating connection object
	   Connection connection=  DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","oracle1");
		
	  AtmService as=new AtmService(connection);
	  AtmUserInterface ai=new AtmUserInterface(as);
	  ai.startatm();
	  
	  
	  
//	   //3.creating ststement object
//		Statement stmt=connection.createStatement();
//		//4.executr query
//		String sql = "SELECT * FROM Users";
//	ResultSet rs=	stmt.executeQuery(sql);
//       
//	  while(rs.next()) {
//		  System.out.println( rs.getInt(1) + rs.getString(2) +  rs.getString(3)+ rs.getInt(4)+ rs.getInt(5) );
//	  }
	}

}

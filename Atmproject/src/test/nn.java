package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
class user{
	int accountId;
	int balance;

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	
	
}
public class nn {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// 1.loading class driver
		System.out.println("loading !!");
		Class.forName("oracle.jdbc.driver.OracleDriver");
		//2.creating connection object
	   Connection connection=  DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","oracle1");
		
//	  AtmService as=new AtmService(connection);
//	  AtmUserInterface ai=new AtmUserInterface(as);
//	  ai.startatm();
	  
	  
	  
//	   //3.creating ststement object
//		Statement stmt=connection.createStatement();
//		//4.executr query
//		String sql = "SELECT * FROM Users";
//	ResultSet rs=	stmt.executeQuery(sql);
//       
//	if(rs.next()) {
//		  System.out.println(  rs.getInt(5) );
//	}
	   
	   user u=new user();
	u.setAccountId(1);
	   String sql="select * from Users  WHERE accountId = ? ";
	PreparedStatement  pstm =connection.prepareStatement(sql);
	pstm.setInt(1,u.getAccountId());
ResultSet rs=pstm.executeQuery();
if(rs.next()) {
	System.out.println("Total Balance"+ rs.getInt(5));
	System.out.println(u.getBalance());
}else {
	System.out.println("invalid account/pin");
}
	}

}


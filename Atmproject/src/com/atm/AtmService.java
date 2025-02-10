package com.atm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collector;

public class AtmService implements ATM {
    String str;
	Connection connection;
	public AtmService(Connection connection) {
		this.connection =connection;
	}

	Map <Integer,Object[]> n = new HashMap<>();
	
	
	int count=1;

	public boolean VerifyAccount(UserDto user) {
		boolean status=false;
		String sql="select * from Users where accountId = ? and pin = ?";
		try {
			PreparedStatement pstm=	connection.prepareStatement(sql);
			pstm.setInt(1, user.getAccountId());
			pstm.setInt(2, user.getPin());
			ResultSet rs=	pstm.executeQuery();
			
			  if(rs.next()) {
				 // UserDto user1=new UserDto();
				 user.setBalance(rs.getInt(5));
				 status=true;
			  }
		} catch (SQLException e) {	
			e.printStackTrace();
		}
		
		return status;
		
	}
	
	
	@Override
	public void deposit(UserDto user, double amount) {
		String sql = "UPDATE Users SET balance = balance + ? WHERE accountId = ?";
		try {
			PreparedStatement pstm =connection.prepareStatement(sql);
			pstm.setDouble(1,amount);
			pstm.setInt(2, user.getAccountId());
			pstm.executeUpdate();
			//user.setBalance(user.getBalance()+ amount); //To update in pojo class
			System.out.println("Deposited : " + amount);
			
			 str=String.valueOf(amount);
		//	n.put(count, str);
			  n.put(user.getAccountId(), new Object[]{count, " Deposite Amount: " + str});
			count++;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
	}

	@Override
	public void withdraw(UserDto user, double amount) {
	String sql = "UPDATE Users SET balance = balance - ? WHERE accountId = ?";
	  try {
		PreparedStatement pstm=connection.prepareStatement(sql);
		pstm.setDouble(1, amount);
		pstm.setInt(2, user.getAccountId());
		pstm.executeUpdate();
		user.setBalance(user.getBalance()-amount);
		System.out.println("Withdraw :" + amount);
		
		System.out.println("balance :" + user.getBalance() );
		//Map <Integer,Object> n = new HashMap<>();
		 str=String.valueOf(amount);
		//n.put(user.getAccountId(),count, str);
		  n.put(user.getAccountId(), new Object[]{count," WithDraw Amount: "+ str  });
		count++;
		
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
	}

	@Override
	public void checkBalance(UserDto user) {
		String sql="select * from Users WHERE accountId = ?";
		try {
			PreparedStatement  pstm =connection.prepareStatement(sql);
	        	pstm.setInt(1,user.getAccountId());
			ResultSet rs=pstm.executeQuery();
			if(rs.next()) {
			 System.out.println("Total Balance: "+ rs.getInt("balance")) ;
			}else {
				System.out.println("invalid account/pin");
			}
			
			} catch (SQLException e) {
			e.printStackTrace();
		}
			
	}

	@Override
	public void exit()  {
		
		     System.out.println("Exiting from atm");
			
	    }


	@Override
	public void miniStatement(UserDto user) {
	    String sql2 = "SELECT * FROM Mini WHERE accountId = ?";
	    
	    try (PreparedStatement pstm = connection.prepareStatement(sql2)) {
	        pstm.setInt(1, user.getAccountId());
	        ResultSet rs = pstm.executeQuery();
	        
	        // If mini-statement exists, update it
	        if (rs.next()) {
	            String sql3 = "UPDATE Mini SET statement = CONCAT(statement, ?) WHERE accountId = ?";
	            
	            try (PreparedStatement pstm3 = connection.prepareStatement(sql3)) {
	                // Using a stream to append the transaction details to the statement
	                n.entrySet().forEach(entry -> {
	                    try {
	                        pstm3.setString(1, (String) entry.getValue()[1]);
	                        pstm3.setInt(2, user.getAccountId());
	                        pstm3.executeUpdate();
	                    } catch (SQLException e) {
	                        e.printStackTrace();
	                    }
	                });
	            }
	        } else {
	            // Insert into Mini if no record exists
	            String sql1 = "INSERT INTO Mini (accountId, trnumber, statement) VALUES (?, ?, ?)";
	            
	            try (PreparedStatement pstm1 = connection.prepareStatement(sql1)) {
	                n.entrySet().forEach(entry -> {
	                    try {
	                        pstm1.setInt(1, user.getAccountId());
	                        pstm1.setInt(2, count);
	                        pstm1.setString(3, (String) entry.getValue()[1]);
	                        pstm1.executeUpdate();
	                    } catch (SQLException e) {
	                        e.printStackTrace();
	                    }
	                });
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    // Fetch and display the updated mini statement
	    String sql = "SELECT statement FROM Mini WHERE accountId = ?";
	    try (PreparedStatement pstm2 = connection.prepareStatement(sql)) {
	        pstm2.setInt(1, user.getAccountId());
	        ResultSet rs1 = pstm2.executeQuery();
	        if (rs1.next()) {
	            user.setStatement(rs1.getString("statement"));
	            System.out.println(user.getStatement());
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}


	}


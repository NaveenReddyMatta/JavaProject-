package com.atm;

public interface ATM {
	  void deposit(UserDto user, double amount);
	  void withdraw(UserDto user, double amount);
	  void checkBalance(UserDto user);
	  void miniStatement(UserDto user);
	  void exit();
	  
}

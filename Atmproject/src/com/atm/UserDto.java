package com.atm;

//public record UserDto(  int accountId, String username,  String password,  int pin, double balance) {}
public class UserDto {

	    private int accountId;
	    private String username;
	    private String password;
	    private int pin;  // Changed to int
	    private double balance;
	    private int trnumber;
	    private String statement;
	 
		public int getTrnumber() {
			return trnumber;
		}
		public void setTrnumber(int trnumber) {
			this.trnumber = trnumber;
		}
		public String getStatement() {
			return statement;
		}
		public void setStatement(String statement) {
			this.statement = statement;
		}
		public int getAccountId() {
			return accountId;
		}
		public void setAccountId(int accountId) {
			this.accountId = accountId;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public int getPin() {
			return pin;
		}
		public void setPin(int pin) {
			this.pin = pin;
		}
		public double getBalance() {
			return balance;
		}
		public void setBalance(double balance) {
			this.balance = balance;
		}
		@Override
		public String toString() {
			return "UserDto [accountId=" + accountId + ", username=" + username + ", password=" + password + ", pin="
					+ pin + ", balance=" + balance + ", trnumber=" + trnumber + ", statement=" + statement + "]";
		}
		
	    
}

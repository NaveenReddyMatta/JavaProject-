package com.atm;

import java.util.Scanner;

public class AtmUserInterface {
    
	private AtmService atmService;
	
	public AtmUserInterface(AtmService atmService) {
		this.atmService = atmService;
	}
	      Scanner sc = new Scanner(System.in);
	      
	      
	      public void startatm() {
	    	  System.out.println("Enter the account Id: ");
	    	  int accountnum=sc.nextInt();
	    	  System.out.println("Enter the PIN: ");
	    	  int pin=sc.nextInt();
	    	  UserDto u=new UserDto();
	    	  u.setAccountId(accountnum);
	    	  u.setPin(pin);
	    	
	    	 boolean status= atmService.VerifyAccount(u);
	    	  
	    	 
	    	 if(status) {
	    		 
	    		 usermenu(u);
	    		 	    		 
	    	 }else {
	    		 System.out.println("Invalid account num and pin");
	    		 return;
	    	 }	    	  	  
	      }
	
	      public void usermenu(UserDto user) {
	    	  
	    	  
	    	  while(true) {
	    		  
	    		  System.out.println("\nATM Menu:");
	              System.out.println("1. Deposit Money");
	              System.out.println("2. Withdraw Money");
	              System.out.println("3. View Balance");
	              System.out.println("4. Mini Statement");
	              System.out.println("5. Exit");
	              System.out.print("Choose an option: ");
	    		 int choice=sc.nextInt();
	    		  
	    		  switch(choice) {
	    		             
	    		  case 1 -> { System.out.println("Enter deposit Money:");
	    		            double amount=sc.nextDouble();
	    		            atmService.deposit(user,amount );
	    		           //break;
	    		            }
	    		  case 2 -> {
	    			         System.out.println ("Enter Withdraw Money : ");
	    		  			 double WTamount=sc.nextDouble();
	    		  		     atmService.withdraw(user, WTamount);
	    		  		   //  break;
	    		  			 }
	    		  
	    		  case 3 -> {
	    		             atmService.checkBalance(user);
	    		           //  break;
	    		  			}
	    		  case 4 -> {
	    				     atmService.miniStatement(user);
	    		            }
	    		  case 5 -> {
 		  			 	
 		  			 	     atmService.exit();
	  			             return;
 		            }
	    		  default -> {
	    			  		 System.out.println("Invalid option");
	    		  			// break;
	    		             }
	   		  }
	    }    	  
	}
}




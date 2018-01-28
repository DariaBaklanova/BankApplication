package test.btp400.a1;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

public class Account {
	 //------------------------------------------------
	 // Defines the class variables
	 //------------------------------------------------	
	private String fullName;
	private String firstName;
	private String lastName;
	private String accountNumber;
	private BigDecimal accountBalance;
	
	  //------------------------------------------------
	  // Default constructor
	  //------------------------------------------------
	  public Account() {this("", null, 1000.00); };
	  
	   //------------------------------------------------
	   // Constructor -- initializes owner's name, balance and account number
	   //------------------------------------------------
	  public Account(String fullName ,String accNumber, double accBalance) { 
			  if(accBalance < 0) 
			  { 
			  BigDecimal balance = new BigDecimal(0); 
			  accountBalance = balance; 
			  } 
			  else 
			  { 
			  BigDecimal balance = new BigDecimal(accBalance); 
			  accountBalance = balance; 
			  } 
			  this.fullName = fullName; 
			  accountNumber = accNumber; 
	}


	   //-----------------------------------------------------------------
	   //  Returns description of the account as a string.
	   //-----------------------------------------------------------------	  
	  public String toString() {
		  NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US);
		  StringBuffer buffer = new StringBuffer ("Name: "); 
		  buffer.append(fullName); 
		  buffer.append("\n"); 
		  buffer.append("Number: "); 
		  buffer.append(accountNumber); 
		  buffer.append("\n"); 
		  buffer.append("Current Balance: "); 
		  buffer.append(n.format(accountBalance)); 
		  buffer.append("\n"); 
		  return buffer.toString(); 
	  }
	  
	   //-----------------------------------------------------------------
	   //  Gets full name of the owner
	   //-----------------------------------------------------------------	
	  public String getFullName() {
		  if (fullName != null) { 
		  return fullName;
		  } else
			  return null;
	  };
	  
	   //-----------------------------------------------------------------
	   //  Gets first name of the owner
	   //-----------------------------------------------------------------	
	  public String getFirstName() { 
		  if (fullName != null) { 
		   firstName=fullName.split(", ")[1];
		  return firstName;
		  } else {
			  return null;
		  }
	  }
	  
	   //-----------------------------------------------------------------
	   //  Gets last name of the owner
	   //-----------------------------------------------------------------	
	  public String getLastName() { 
		  if (fullName != null) { 
		   lastName=fullName.split(", ")[0];
		  return lastName;
		  } else {
			  return null;
		  }
	  }
	   //-----------------------------------------------------------------
	   //  Gets account number
	   //-----------------------------------------------------------------	
	  public String getAccountNumber() {
			return accountNumber;
		}
	  
	   //-----------------------------------------------------------------
	   //  Returns the current balance of the account.
	   //-----------------------------------------------------------------
	  public double getAccountBalance() {
		  double balance = this.accountBalance.doubleValue(); 
		  return balance;
		}
	  
		public BigDecimal setAccountBalance(double accountBalance) {
			BigDecimal balance = new BigDecimal(accountBalance); 
			this.accountBalance = balance; 
			return this.accountBalance; 
		}
	  
		public int hashCode(){
			  return Objects.hash(this.fullName, this.accountNumber, this.accountBalance);
		}
	   
	  // equals method determines whether the Number object that invokes the method is equal to the object that is passed as an argument
	    public boolean equals( Object obj ) {

			boolean result = false;

			if ( obj instanceof Account ) {

				Account obj2 = (Account) obj;

				if ( ( (obj2.fullName.equals(fullName)) &&
				     (obj2.accountNumber.equals(accountNumber)) && 
				     obj2.accountBalance == accountBalance) )

				     result = true;
			}

			return result;
		}
	 
	    //-----------------------------------------------------------------
	    //  Deposits the specified amount into the account. Returns the
	    //  new balance.
	    //-----------------------------------------------------------------
	    public void deposit (double amount) {
	    	double balance = this.accountBalance.doubleValue();
			if(amount > 0) {
				BigDecimal deposit = new BigDecimal(balance + amount);
				this.accountBalance = deposit;
			}
	    }

	    //-----------------------------------------------------------------
	    //  Withdraws the specified amount from the account. Returns the new balance.
	    //-----------------------------------------------------------------
	    public boolean withdraw (double amount) {
	    	boolean result=true;
	    	
	    	double balance = this.accountBalance.doubleValue();
			if(amount < 0 || balance < amount) {
				result=false; 
			} else {
				BigDecimal withdraw = new BigDecimal(balance - amount);
				this.accountBalance = withdraw;
				result = true; 
			}
			return result;
	    }
	    
}



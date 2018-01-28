/**
 *<h1>Chequing class</h1>
* Subclass of Account class.
*
* @author  Daria Baklanova
* @version 1.0
* @since   2017-03-04 
*/

package com.java.accounts;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

public class Chequing extends Account {
	/**------------------------------------------------
	* Defines the class variables
	* ------------------------------------------------	
	*/
	
	private BigDecimal chargePerTransaction;
	private int maxNumTransaction;
	private double [] transaction;
	private int countTransaction=0;
	private BigDecimal totalAmountOfCharges;
	
	/**------------------------------------------------
	* Default constructor
	* ------------------------------------------------	
	*/	
	public Chequing() { 
	
		super();//the default constructor of the Account class
		BigDecimal charge = new BigDecimal(0.25);
		chargePerTransaction= charge;
		maxNumTransaction=3;
	};
	
	/**------------------------------------------------
	* When Chequing object is created, it is provided with a full name, an account number, the starting balance, service charge per transaction and maximum number of transactions allowed
	* ------------------------------------------------	
	*/
	public Chequing(String name ,String accNum, double accBal, double chargePerTrans, int maxNumTransaction){
		super(name, accNum, accBal);//the default constructor of the Account class
		chargePerTransaction = new BigDecimal(chargePerTrans);
		this.maxNumTransaction=maxNumTransaction;
		transaction = new double[maxNumTransaction];
	}
		
	/**------------------------------------------------
	* Equals method determines whether the Number object that invokes the method is equal to the object that is passed as an argument.
	* ------------------------------------------------	
	*/
	public boolean equals( Object obj ) {
		boolean result = false;		
		if ( obj instanceof Chequing ) {
			Chequing obj2 = (Chequing) obj;
			if (  (obj2.chargePerTransaction == chargePerTransaction)&& 
				(obj2.maxNumTransaction == maxNumTransaction) ) {
					result = true;
			}				
		}
		return result;
	}
	
	/**------------------------------------------------
	* Returns description of the account as a string.
	* ------------------------------------------------	
	*/
	public String toString() {	    
		totalAmountOfCharges=chargePerTransaction.multiply(new BigDecimal(countTransaction));
		 NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US);
			  StringBuffer buffer = new StringBuffer ("Type: CHQ\n"); 
			  buffer.append("Service Charge: "); 
			  buffer.append(n.format(chargePerTransaction)); 
			  buffer.append("\n"); 
			  buffer.append("Total Service Charge: "); 
			  buffer.append(n.format(totalAmountOfCharges));
			  buffer.append("\nNumber of Transactions Allowed: "); 
			  buffer.append(maxNumTransaction); 
			  buffer.append("\n"); 
			  buffer.append("List of Transactions: ");
			  for(int i=0;i<transaction.length;i++){
				  buffer.append(transaction[i]);
				  buffer.append(", ");
			  }	 
			  buffer.append("\n");
			  buffer.append("Final Balance: "); 
			  buffer.append(n.format(getAccountBalance())); 
			  buffer.append("\n");
		      return super.toString()+buffer.toString(); 
	}
 
	/**------------------------------------------------
	* The default implementation of hashCode() provided by Object is derived by mapping the memory address to an integer value.
	* ------------------------------------------------	
	*/
	public int hashCode(){
		  return Objects.hash(this.chargePerTransaction,this.maxNumTransaction);
	}
		
	/**
	 *<h1> The transactions will not be completed successfully if </h1>
	 * <p>i) negative numbers are passed into these methods, </p>
	 * <p>ii) the maximum number of transactions will be exceeded, or </p>
	 * <p>iii) the balances could become negative if the transactions were completed.</p>
	 * <p>The withdraw( ) method will return false as a result.</p>
	 * 
	 * 
	 *  Stores the amounts of transactions in the array
	//	Then they will update current balance and the total amount of service charges if transactions are made successfully. However, they will not deduct the amount of service charges from the current balance.
	*/
	@Override
	public void deposit (double amount) {
		double currentBalance = super.getAccountBalance() + amount; 
		if (amount>0 || countTransaction<maxNumTransaction){
			transaction[countTransaction]=+amount;
			super.setAccountBalance(currentBalance);
			countTransaction++;
			 totalAmountOfCharges=chargePerTransaction.multiply(new BigDecimal(countTransaction));
		}
	}

	/**------------------------------------------------
	*  Stores the amounts of transactions in the array
	*  Then they will update current balance and the total amount of service charges if transactions are made successfully. 
	* However, they will not deduct the amount of service charges from the current balance.
	* ------------------------------------------------	
	*/
	@Override
	public boolean withdraw (double amount) {
	   	boolean result=true;
	   	double currentBalance = super.getAccountBalance() - amount; 
		if(amount>0 || countTransaction<=maxNumTransaction) {
			transaction[countTransaction]=-amount;
			super.setAccountBalance(currentBalance);
			countTransaction++;
			 totalAmountOfCharges=chargePerTransaction.multiply(new BigDecimal(countTransaction));
			if(currentBalance<0) {
				result=false;
			}
		} else {
		    result = false;
		}
		return result;
	}
	
	/**------------------------------------------------
	* returns the final balance of the account. The final balance is calculated by subtracting the total amount of service charges from the current balance
	* ------------------------------------------------	
	*/
	@Override
	public double getAccountBalance(){
		totalAmountOfCharges=chargePerTransaction.multiply(new BigDecimal(countTransaction));
		double finalBalance=super.getAccountBalance()-totalAmountOfCharges.doubleValue();
		return finalBalance;
	}
	
}

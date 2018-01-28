/**
 *<h1>Savings class</h1>
* Subclass of Account, implements interface Taxable.
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

public class Savings extends Account implements Taxable {
	/**------------------------------------------------
	* Defines the class variables
	* ------------------------------------------------	
	*/
	private double annualInterestRate;
	private BigDecimal interestIncome;
	private BigDecimal amountOfTax;
	private double taxRate=15;

	/**------------------------------------------------
	* Default constructor
	* ------------------------------------------------	
	*/
	public Savings() { 
		super();
		annualInterestRate=0.10;
	};
	
	/**------------------------------------------------
	* Constructor -- initializes owner's name, account number and current balance
	* ------------------------------------------------	
	*/
	public Savings(String name ,String accNum, double accBal, double interestRate){
		super(name, accNum, accBal);
		annualInterestRate = interestRate;
	}
	   
	/**------------------------------------------------
	*  Equals method determines whether the Number object that invokes the method is equal to the object that is passed as an argument.
	* ------------------------------------------------	
	*/
	public boolean equals( Object obj ) {
		boolean result = false;
		if (obj == this) {
			return true;
		}		
		if ( obj instanceof Savings ) {
			Savings obj2 = (Savings) obj;
			if (obj2.annualInterestRate == annualInterestRate){
				result = true;
			}
		}
		return result;
	}

	/**------------------------------------------------
	*  Returns description of the account as a stringBuffer.
	* ------------------------------------------------	
	*/
	public String toString() {		

		interestIncome = new BigDecimal(super.getAccountBalance() * annualInterestRate / 100);
		NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US);
		
		  StringBuffer buffer = new StringBuffer ("Type: SAV\n"); 
		  buffer.append("Interest Rate: "); 
		  buffer.append(annualInterestRate); 
		  buffer.append("%\n"); 
		  buffer.append("Interest Income: "); 
		  buffer.append(n.format(interestIncome)); 
		  buffer.append("\n"); 
		  buffer.append("Final Balance: "); 
		  buffer.append(n.format(getAccountBalance())); 
		  buffer.append("\n"); 
	      return super.toString()+buffer.toString(); 
	}

	/**------------------------------------------------
	*  The default implementation of hashCode() provided by Object is derived by mapping the memory address to an integer value.
	* ------------------------------------------------	
	*/
	public int hashCode(){
		  return Objects.hash(this.annualInterestRate);
	}
	
	/**------------------------------------------------
	* Returns the final balance of the account.
	* The final balance is the sum of the current balance and the interest income.  
	* ------------------------------------------------	
	*/
	@Override
	public double getAccountBalance(){		
		BigDecimal intIncome = new BigDecimal(super.getAccountBalance() * annualInterestRate / 100); 
		interestIncome = intIncome; 
		double finalBalance = super.getAccountBalance() + interestIncome.doubleValue(); 
		return finalBalance; 
	}
		
	/**------------------------------------------------
	* Calculates interest income on the basis of the current balance
	* Calculates the amount of tax on the basis of interest income 
	* ------------------------------------------------	
	*/
	public void calculateTax( double taxRate ){
		this.taxRate = taxRate;
		interestIncome = new BigDecimal(super.getAccountBalance() * annualInterestRate / 100);
		if(interestIncome.doubleValue() > 50){
			amountOfTax = interestIncome.multiply(new BigDecimal(taxRate));	
		} else {
			amountOfTax = new BigDecimal(0);
		}
	}

	/**------------------------------------------------
	*  Returns that amount of tax that has been calculated.
	* ------------------------------------------------	
	*/
	public double getTaxAmount(){
		double taxAmount = this.amountOfTax.doubleValue();
		return taxAmount;
	}
 
	/**------------------------------------------------
	*	returns a string
	* ------------------------------------------------	
	*/
	public String createTaxStatement(){
		this.calculateTax(15);
		interestIncome = new BigDecimal(super.getAccountBalance() * annualInterestRate / 100);
		NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US);
		StringBuffer buffer = new StringBuffer ("Tax rate: 15%\n"); 
		  buffer.append("Account Number: "); 
		  buffer.append(super.getAccountNumber()); 
		  buffer.append("\n"); 
		  buffer.append("Interest Income: "); 
		  buffer.append(n.format(interestIncome)); 
		  buffer.append("\n"); 
		  buffer.append("Amount of Tax: "); 
		  buffer.append(n.format(getTaxAmount())); 
		  buffer.append("\n"); 
		return buffer.toString(); 
	}
}

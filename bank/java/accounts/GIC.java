/**
 *<h1>GIC class</h1>
* Subclass of Account class, implements Taxable Interface.
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

public class GIC extends Account implements Taxable {

	/**------------------------------------------------
	* Defines the class variables
	* ------------------------------------------------	
	*/
	private int periodOfInvestment;
	private double annualInterestRate;
	private BigDecimal interestIncome;
	private BigDecimal amountOfTax;
	private double taxRate=15;
	
	/**------------------------------------------------
	* Default constructor
	* ------------------------------------------------	
	*/	
	public GIC(){
		super();//the default constructor of the Account class
		periodOfInvestment=1;
		annualInterestRate=1.25;
	}
	
	/**------------------------------------------------
	 * When a GIC object is created, it is provided with a full name, an account number, the starting balance (i.e. the principal amount of investment), the period of investment (in years) and annual interest rate.
	* ------------------------------------------------	
	*/
	public GIC(String name ,String accNum, double accBal, int periodOfInvestment, double annualInterestRate){
		super(name, accNum, accBal);//the default constructor of the Account class
		this.annualInterestRate = annualInterestRate;
		this.periodOfInvestment=periodOfInvestment;
		
	}

	/**------------------------------------------------
	* Equals method determines whether the Number object that invokes the method is equal to the object that is passed as an argument.
	* ------------------------------------------------	
	*/
	public boolean equals( Object obj ) {
		boolean result = false;		
		if ( obj instanceof GIC ) {
			GIC obj2 = (GIC) obj;
			if (  (obj2.periodOfInvestment == periodOfInvestment)&& 
				(obj2.annualInterestRate == annualInterestRate) ) {
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
		interestIncome = new BigDecimal(super.getAccountBalance() * annualInterestRate / 100);
		 NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US);
		  StringBuffer buffer = new StringBuffer ("Type: "); 
		  buffer.append("GIC"); 
		  buffer.append("\n"); 
		  buffer.append("Annual Interest Rate: "); 
		  buffer.append(annualInterestRate); 
		  buffer.append("%\n"); 
		  buffer.append("Period of Investment: "); 
		  buffer.append(periodOfInvestment);
		  if(periodOfInvestment==1){
			  buffer.append(" year\n");
		  } else {
			  buffer.append(" years\n");
		  }
		  buffer.append("Interest Income at Maturity: "); 
		  buffer.append(n.format(interestIncome)); 
		  buffer.append("\n"); 
		  buffer.append("Balance at Maturity: "); 
		  buffer.append(n.format(getAccountBalance())); 
		  buffer.append("\n");
	      return super.toString()+buffer.toString(); 
	}

	/**------------------------------------------------
	* The default implementation of hashCode() provided by Object is derived by mapping the memory address to an integer value.
	* ------------------------------------------------	
	*/
	public int hashCode(){
		  return Objects.hash(this.periodOfInvestment,this.annualInterestRate);
	}
	
	/**------------------------------------------------
	* Empty method
	* ------------------------------------------------	
	*/	
	@Override
	public void deposit (double amount) {}

	/**------------------------------------------------
	* Empty method
	* Return false
	* ------------------------------------------------	
	*/	
	@Override
	public boolean withdraw (double amount) {
		return false;
	}
	
	/**------------------------------------------------
	* returns the final balance of the account (i.e. balance at maturity).
	* ------------------------------------------------	
	*/	
	@Override
	public double getAccountBalance(){
		
		double result=Math.pow((1+annualInterestRate), periodOfInvestment);
		double balanceAtMaturity=super.getAccountBalance()*result/100;
		return balanceAtMaturity;
	}
	

	/**------------------------------------------------
	* Calculates interest income on the basis of the current balance
	* Calculates the amount of tax on the basis of interest income 
	* ------------------------------------------------	
	*/	
	public void calculateTax( double taxRate ){
		this.taxRate=taxRate;
		interestIncome = new BigDecimal(super.getAccountBalance() * annualInterestRate / 100);
		double interestIncome=super.getAccountBalance()*annualInterestRate/100;		
		BigDecimal taxAmount = new BigDecimal(interestIncome*taxRate);
		amountOfTax=taxAmount;
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
	public String createTaxStatement( ){
		this.calculateTax(15);
		NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US);
		interestIncome = new BigDecimal(super.getAccountBalance() * annualInterestRate / 100);
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

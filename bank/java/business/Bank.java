/**
 *<h1>Bank class</h1>
* 
* @author  Daria Baklanova
* @version 1.0
* @since   2017-03-04 
*/
package com.java.business;
import java.util.ArrayList;
import java.util.Objects;

import com.java.accounts.Account;

public class Bank
{
	/**------------------------------------------------
	* Defines the class variables
	* ------------------------------------------------	
	*/
	private String bankName;
	private static ArrayList<Account> accounts;
	
	/**------------------------------------------------
	* Default constructor
	* ------------------------------------------------	
	*/
	public Bank() {}

	
	/**------------------------------------------------
	* Constructor -- initializes bank name
	* ------------------------------------------------	
	*/
	public Bank(String bankName) {
		this.bankName=bankName;
	   	accounts = new ArrayList<Account>();
	};		

	/**------------------------------------------------
	*  Equals method determines whether the Number object that invokes the method is equal to the object that is passed as an argument.
	* ------------------------------------------------	
	*/
	@Override
	   public boolean equals( Object obj ) {

			boolean result = false;		
			if ( obj instanceof Account ) 
			{
				Bank obj2 = (Bank) obj;
				if (obj2.bankName.equals(bankName))
				{
				     result = true;
				}
				
				if (Bank.accounts.size() != accounts.size()) 
				{
					result = false;
				} 
				else 
				{
					for(int i = 0; i < accounts.size(); i++)
					{
							result = Bank.accounts.get(i).equals(accounts.get(i));
					}
				}
			}
			return result;
		}
	
	/**------------------------------------------------
	*  Returns description of the account as a stringBuffer.
	* ------------------------------------------------	
	*/
	public String toString() 
	{
		 StringBuffer buffer = new StringBuffer (getBankName()); 
		  buffer.append(" has "); 
		  buffer.append(accounts.size()); 
		  buffer.append(" accounts opened.\n\n"); 
	      return buffer.toString(); 
	}
	
	/**------------------------------------------------
	*  The default implementation of hashCode() provided by Object is derived by mapping the memory address to an integer value.
	* ------------------------------------------------	
	*/	
	public int hashCode(){
		  return Objects.hash(this.bankName);
	}
	
	/**------------------------------------------------
	* Adds an item(Account) to the array
	* ------------------------------------------------	
	*/
	public boolean addAccount(Account obj) {
		boolean result = false, check=false;
		if(obj != null){	   
				for(int i = 0; i < accounts.size(); i++){
					if(obj.equals(accounts.get(i)) && accounts.get(i)!=null){
						check = false;
					} 
				}
				if(!check)
				{
					accounts.add(obj);
					result = true;
				}
								
			
		}
		return result;		
	}
	 
	/**------------------------------------------------
	* Removes the item (Account) from the array
	* ------------------------------------------------	
	*/
	public Account removeAccount(String AccountNumber) {
		if(AccountNumber != null) {
			for(int i = 0; i < accounts.size(); i++) {
				if(accounts.get(i) != null && accounts.get(i).getAccountNumber().equals(AccountNumber)) {
					//Account to_ret = accounts.get(i);
					Account to_ret;
					to_ret = accounts.remove(i);
					System.out.println("Successfully removed: \n"+ to_ret);
					return to_ret;
				}
			}
		}
		return null;
	}


	/**------------------------------------------------
	* Search for the specific Account with the same AccountBalance
	* ------------------------------------------------	
	*/
	  public static Account[] search(double AccountBalance)
	   {
		   int results_count = 0;
		   Account [] found_results = new Account[accounts.size()]; // worst case scenario if all accounts match
		   Account [] filtered_results;
		   for (int search_index = 0; search_index < accounts.size(); search_index++) {
			   if(accounts.get(search_index) != null) {
				   if(accounts.get(search_index).getAccountBalance() == AccountBalance){					  
					   found_results[results_count] = accounts.get(search_index);
					   results_count ++;
					   System.out.println("\nBalance was found: \n" + found_results[search_index] + "\n");
				   } 
			   } else { // end of Accounts array, null found
				  break;
			   }
		   }
		   if(results_count != 0) {
			  filtered_results = new Account[results_count];
			  for (int array_index = 0; array_index < results_count; array_index++) {
				  filtered_results[array_index] = found_results[array_index];
			  }
			  return filtered_results;
		  }
		  return new Account[0];
	   }
	
	/**------------------------------------------------
	* Search for the specific Account with the same Account Name
	* ------------------------------------------------	
	*/
	public static Account[] searchByAccountName(String accountName) { 
		int res = 0; 
		Account [] found = new Account[accounts.size()]; 
			for(int i = 0; i < accounts.size(); i++){ 
				try { 
					if(accounts.get(i).getFullName().equals(accountName)){ 
						// System.out.println("[Founded name for account]: " + accounts.get(i)); 
						res++; 
						found[i] = accounts.get(i); 
					} 
				} catch(Exception e){ } 
			} 
			if(res == 0){ 
				found[0] = null; 
			} 
		return found; 
	}
	
	/**------------------------------------------------
	* Search for the specific Account with the same Account Number
	* ------------------------------------------------	
	*/
	public static Account searchByAccountNumb(String accountNumb) { 
		int res = 0; 
		Account found = new Account(); 
		for(int i = 0; i < accounts.size(); i++){ 
			try { 
				if(accounts.get(i).getAccountNumber().equals(accountNumb)){ 
					res++; 
					found = accounts.get(i); 
				} 
			} catch(Exception e){ } 
		} 
		if(res == 0){ 
			found = null; 
		} 
	return found; 
	}
	
	/**------------------------------------------------
	* Returns the name of the bank.
	* ------------------------------------------------	
	*/
	public String getBankName()
	{
		return bankName;
	}
	
	/**------------------------------------------------
	* Returns the size of the arrayList.
	* ------------------------------------------------	
	*/
	public int getAccountSize(){
		return accounts.size();
	}

	/**------------------------------------------------
	* Returns all accounts in the arrayList.
	* ------------------------------------------------	
	*/
	  public static Account [] getAllAccounts(){
	  		 return accounts.toArray(new Account[accounts.size()]);
	  }
	 
	
}

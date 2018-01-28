package com.btp400;

/**
 *<h1>FinancialApp class</h1>
 * Tester.
 *
 * @author  Daria Baklanova
 * @version 1.0
 * @since   2017-03-04 
 */

import java.util.Scanner;
import com.java.accounts.*;
import com.java.business.Bank;

public class FinancialApp {
	
	static Scanner in_stream = new Scanner(System.in);
	
	public static void loadBank(Bank bank) {
		Savings acc1 = new Savings("Doe, John", "123", 0, 0.15);
		bank.addAccount(acc1);

		Savings acc2 = new Savings("Ryan, Mary", "1234", 0, 0.25);
		bank.addAccount(acc2);

		Chequing acc3 = new Chequing("Doe, John", "12345", 0, 0.25, 4);
		bank.addAccount(acc3);

		Chequing acc4 = new Chequing("Ryan, Mary", "543", 0, 0.25, 4);
		bank.addAccount(acc4);

		GIC acc5 = new GIC("Doe, John", "5432", 6000.00, 2, 1.50);
		bank.addAccount(acc5);

		GIC acc6 = new GIC("Ryan, Mary", "54321", 15000.00, 4, 2.50);
		bank.addAccount(acc6);
	}

	public static void displayMenu(String bankName) {
		System.out.println("\n----------------------------------------------\n"
				+ "Welcome to" + bankName + "Bank!\n"
				+ "1. Open an account.\n"
				+ "2. Close an account.\n"
				+ "3. Deposit money.\n"
				+ "4. Withdraw money.\n"
				+ "5. Display accounts.\n"
				+ "6. Display a tax statement.\n"
				+ "7. Exit\n"
		);
	}

	public static int menuChoice() {
		System.out.print("Please enter your menu choice: ");
		int n = in_stream.nextInt();
		in_stream.nextLine(); // consume new line
		return n;
	}

	public static void displayAccount(Account account) {
		account.toString();
	}

	public static void main(String[] args) {
		Bank obj1 = new Bank("Seneca@York Bank");
		loadBank(obj1);
		
		while (true) {
			displayMenu(obj1.getBankName());
			int choice = menuChoice();
			if (choice == 1) {
				
				System.out.print("Please enter the account type (SAV/CHQ/GIC): ");
				String type = in_stream.nextLine();
				String fullName, accountNumber;
				double accountBalance, annualInterestRate, chargePerTransaction;
				int periodOfInvestment, maxNumberOfTransactions;

				if (type.equals("SAV")) {
					System.out.println("Please enter account information at one line (e.g. Doe,John; A1234; 1000.00; 3.65): ");
					String information = in_stream.nextLine();
					String[] splitStrings = information.split(";\\s*");
					fullName = splitStrings[0];
					accountNumber = splitStrings[1];
					accountBalance = Double.parseDouble(splitStrings[2]);
					annualInterestRate = Double.parseDouble(splitStrings[3]);
					Savings savAccount1 = new Savings(fullName, accountNumber, accountBalance, annualInterestRate);
					obj1.addAccount(savAccount1);
				} else if (type.equals("CHQ")) {
					System.out.print("Please enter account information at one line (e.g. Doe, John (full name); A1234 (Account Number); 1000.00 (Account Balance); 3.65 (Charge per transaction); 5 (Maximum number of transactions)): ");
					String information = in_stream.nextLine();
					String[] splitStrings = information.split(";\\s*");
					fullName = splitStrings[0];
					accountNumber = splitStrings[1];
					accountBalance = Double.parseDouble(splitStrings[2]);
					chargePerTransaction = Double.parseDouble(splitStrings[3]);
					maxNumberOfTransactions = Integer.parseInt(splitStrings[4]);
					Chequing chqAccount1 = new Chequing(fullName, accountNumber, accountBalance, chargePerTransaction, maxNumberOfTransactions);
					obj1.addAccount(chqAccount1);
				} else if (type.equals("GIC")) {
					System.out.print("Please enter account information at one line (e.g. Doe, John (full name); A1234 (Account Number); 1000.00 (Account Balance); 1 (Period of investment); 3.65 (Annual Interest Rate): ");
					String information = in_stream.nextLine();
					String[] splitStrings = information.split(";\\s*");
					fullName = splitStrings[0];
					accountNumber = splitStrings[1];
					accountBalance = Double.parseDouble(splitStrings[2]);
					annualInterestRate = Double.parseDouble(splitStrings[4]);
					periodOfInvestment = Integer.parseInt(splitStrings[3]);
					GIC gicAccount1 = new GIC(fullName, accountNumber, accountBalance, periodOfInvestment, annualInterestRate);
					obj1.addAccount(gicAccount1);
				}

			} else if (choice == 2) {
				
				System.out.print("Please enter the number of account you want to close: ");
				String accountNumber = in_stream.nextLine();
				obj1.removeAccount(accountNumber);
				
			} else if (choice == 3) {
				
				System.out.print("Please enter account number: ");
				String acc_num = in_stream.nextLine();

				System.out.print("How much money do you want to deposit? ");
				double deposit_value = Double.parseDouble(in_stream.nextLine());

				Account found_account = Bank.searchByAccountNumb(acc_num);
				found_account.deposit(deposit_value);

				System.out.print("Funds successfully added. Your new balance: ");
				System.out.print(found_account.getAccountBalance());

			} else if (choice == 4) {
				
				System.out.print("Please enter account number: ");
				String acc_num = in_stream.nextLine();

				System.out.print("How much money do you want to withdraw? ");
				double withdraw_value = Double.parseDouble(in_stream.nextLine());

				Account found_account = Bank.searchByAccountNumb(acc_num);
				found_account.withdraw(withdraw_value);

				System.out.println("Funds successfully withdrawn. Your new balance: ");
				System.out.println(found_account.getAccountBalance());

			} else if (choice == 5) {
				
				System.out.println(""
						+ "\nDisplay accounts:\n"
						+ "1. Display all open accounts\n"
						+ "2. Display accounts with same account names\n"
						+ "3. Display accounts with same final balances\n"
				);
				
				System.out.print("Please make a choice: ");
				int num = in_stream.nextInt();
				in_stream.nextLine(); // consume new line
				System.out.print("\n");
				
				if (num == 1) {
					Account[] accounts = Bank.getAllAccounts();
					for (Account acct: accounts) {
						System.out.println(acct.toString() + "\n");
					}
				} else if (num == 2) {
					System.out.print("Please enter account name: ");
					String name = in_stream.nextLine();
					Bank.searchByAccountName(name);
				} else if (num == 3) {
					System.out.print("Please enter final balance: ");
					double balance = in_stream.nextInt();
					in_stream.nextLine(); // consume new line
					Bank.search(balance);
				}

			} else if (choice == 6) {
				int count=1;
				System.out.print("Please enter name: ");
				String name = in_stream.nextLine();
				Account[] found_accounts = Bank.searchByAccountName(name);
				for (Account found_account : found_accounts) {
					if (found_account != null && found_account instanceof Taxable) {
						System.out.println(count+". "+found_account.createTaxStatement());
						count++;
					}
				}
				
			} else if (choice == 7) {
				
				in_stream.close();
				System.out.println("Thank you!");
				System.exit(0);
				
			}
		}
	}
}

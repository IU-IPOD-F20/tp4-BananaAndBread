

import java.util.Locale;
import java.util.Scanner;

import application.AccesBankAgency;
import bank.BankAgency;
import bank.Account;
import bank.exception.ABAccountAlreadyExistingException;
import bank.exception.ABInexistingAccountException;
import bank.exception.ABNullAccountException;
import bank.exception.AccountException;


public class BankAgencyApp {

	public static void printHeader(BankAgency ag, String menuType) {
		System.out.println("--");
		System.out.println(ag.getAgencyName() + " (" + ag.getAgencyLoc() + ")");
		System.out.println(menuType);
		System.out.println("--");
		System.out.println("  Choose:");
	}

	public static void printFooter(){
		System.out.println("\n    0 - To quit this menu");
		System.out.print("Choice ?\n");
	}
	/**
	 * UI of the App
	 * @param ag	BankAgency to get name and location
	 */
	public static boolean executeGeneralMenu(BankAgency ag) throws AccountException, ABAccountAlreadyExistingException, ABNullAccountException, ABInexistingAccountException {
		Boolean proceed = true;
		printHeader(ag, "General Menu");
		System.out.println("    1 - List of the Agency accounts");
		System.out.println("    2 - See an account (by its number)");
		System.out.println("    3 - Operation on an account");
		System.out.println("    4 - Accounts management");
		printFooter();
		String choice;
		Scanner scanner = new Scanner(System.in);
		choice = scanner.next();
		choice = choice.toLowerCase();
		Boolean proceedInternalMenu = true;
		switch (choice) {
			case "0" :
				System.out.println("End of Menu Operation on an account");
				proceed = false;
				break;
			case "1" :
				ag.print();
				break;
			case "2" :
				System.out.print("Account Number -> ");
				String number = scanner.next();
				Account c = ag.getAccount(number);
				if (c==null) {
					System.out.println("Account non existing ...");
				} else {
					c.print();
				}
				break;
			case "3":
				while (proceedInternalMenu) {
					proceedInternalMenu = executeOperationMenu(ag);
				}
				break;
			case "4":
				while (proceedInternalMenu) {
					proceedInternalMenu = executeAccountsManagementMenu(ag);
				}
				break;

			default :
				System.out.println("Problem ...");
				break;
		}
		return proceed;
	}


	public static boolean executeOperationMenu(BankAgency ag) throws AccountException {
		printHeader(ag, "Menu Operation on an account");
		System.out.println("    1 - Deposit money on an account");
		System.out.println("    2 - Withdraw money from an account");
		printFooter();
		Boolean proceed = true;
		String choice;
		Scanner scanner = new Scanner(System.in);
		choice = scanner.next();
		choice = choice.toLowerCase();
		String number = null;
		Double amount = null;
		if (!choice.equals("0")) {
			System.out.print("Account Number -> ");
			number = scanner.next();
			Account c = ag.getAccount(number);
			System.out.print("Amount of money -> ");
			amount = Double.parseDouble(scanner.next());
			if (c == null) {
				System.out.println("Account non existing ...");
			}
		}
		else {
			switch (choice) {
				case "0":
					proceed = false;
					break;
				case "1":
					depositOnAccount(ag, number, amount);
					break;
				case "2":
					withdrawFromAccount(ag, number, amount);
					break;
				default:
					System.out.println("Problem ...");
					break;
			}
		}
		return proceed;
	}

	public static boolean executeAccountsManagementMenu(BankAgency ag) throws ABAccountAlreadyExistingException, ABNullAccountException, ABInexistingAccountException {
		printHeader(ag, "Menu Accounts management");
		Boolean proceed = true;
		System.out.println("    1 - Add an account");
		System.out.println("    2 - Delete an account");
		printFooter();
		Scanner scanner = new Scanner(System.in);
		String choice = scanner.next();
		choice = choice.toLowerCase();
		String accountNumber;
		switch (choice) {
			case "0":
				proceed = false;
				break;
			case "1":
				System.out.println("--> Owner Name");
				String ownerName = scanner.next();
				System.out.println("--> Account Number");
				accountNumber = scanner.next();
				ag.addAccount(new Account(accountNumber, ownerName));
				break;
			case "2":
				System.out.println("--> Account Number");
				accountNumber = scanner.next();
				ag.removeAccount(accountNumber);
				break;
		}
		return proceed;
	}

	public static void main(String argv[]) throws AccountException, ABAccountAlreadyExistingException, ABNullAccountException, ABInexistingAccountException {
		boolean proceed ;
		Scanner lect;
		BankAgency myAgency ;

		lect = new Scanner( System.in );
		lect.useLocale(Locale.US);

		myAgency = AccesBankAgency.getBankAgency();

		proceed = true;
		while (proceed) {
			proceed = BankAgencyApp.executeGeneralMenu(myAgency);
		}

	}

	public static void depositOnAccount(BankAgency ag, String accountNumber, double amount) {
		Account c;

		c = ag.getAccount(accountNumber);
		if (c==null) {
			System.out.println("Account not existing ...");
		} else {
			System.out.println("Balance before deposit: "+c.balance());
			try {
				c.deposit(amount);
				System.out.println("Balance after deposit: "+c.balance());
			} catch (AccountException e) {
				System.out.println("Deposit error, Balance unchanged: " + c.balance());
				System.out.println(e.getMessage());
			}
		}
	}

	public static void withdrawFromAccount(BankAgency ag, String accountNumber, double amount) {
		Account c;

		c = ag.getAccount(accountNumber);
		if (c==null) {
			System.out.println("Account not existing ...");
		} else {
			System.out.println("Balance before withdrawal: " + c.balance());
			try {
				c.withdraw(amount);
				System.out.println("Balance after withdrawal: "+c.balance());
			} catch (AccountException e) {
				System.out.println("Withdraw error, Balance unchanged: " + c.balance());
				System.out.println(e.getMessage());
			}
		}

	}
}
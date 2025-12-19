package hcl_training.assignment_1;

import java.util.Scanner;

public class Solution5 {
	static String[] options = {"Deposit", "Withdraw", "Check Balance", "Account Information",
			"Exit" };

	static BankAccount acc;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		createAccount(sc);

		while (true) {
			if (acc != null)
				System.out.println("ACCOUNT: " + acc.accountNumber);

			printOptions();
			System.out.print("Select Option: ");
			int option = sc.nextInt();

			switch (option) {

			case 1: // deposit
				System.out.print("Enter amount to deposit: ");
				int amm = sc.nextInt();
				acc.deposit(amm);

				break;
			case 2: // withdraw
				System.out.print("Enter amount to withdraw: ");
				int withdrawAmm = sc.nextInt();
				String pin = authenticate(sc);
				acc.withdraw(withdrawAmm, pin);
				break;
			case 3: // Check balance
				String pin1 = authenticate(sc);
				acc.checkBalance(pin1);
				break;
			case 4: // Account Information
				String pin2 = authenticate(sc);
				acc.accountInformation(pin2);
				break;
			case 5:
				System.exit(0);
				break;
			default:
				System.out.println("Please select valid option");
			}
			System.out.println();
			waitAfterResult(sc);

		}

	}

	public static String authenticate(Scanner sc) {
		System.out.print("Enter pin: ");
		sc.nextLine();

		return sc.nextLine();
	}

	public static void createAccount(Scanner sc) {
		System.out.print("Enter your name: ");
		String name = sc.nextLine();
		String pin;

		pinSetup: while (true) {
			System.out.print("Set pin (4 digit): ");
			pin = sc.nextLine();

			if (pin.length() != 4) {
				System.err.println("Please choose 4-digit pin");
				continue;
			}

			for (char c : pin.toCharArray()) {
				if (!Character.isDigit(c)) {
					System.err.println("Pin should contain digits only");
					continue pinSetup;
				}
			}
			System.out.print("Retype pin (4 digit): ");
			String retry = sc.nextLine();

			if (pin.equals(retry)) {
				break;
			}
			System.err.println("Pin didn't match");
			System.err.flush();
		}

		acc = new BankAccount(name, pin);
		System.out.println("Bank Account created successfully");
	}

	public static void waitAfterResult(Scanner sc) {
		System.out.println("Press enter key to continue...");
		sc.nextLine();
		sc.nextLine();
	}

	public static void printOptions() {
		System.out.println("===========| Menu |============");

		for (int i = 0; i < options.length; i++) {
			System.out.format("| %2d. %-23s |\n", (i + 1), options[i]);
		}
		System.out.println("===============================");
	}
}

class BankAccount {
	static int lastAccountNumber = 1864384;
	String userName;
	int balance;
	int withdrawLimit;
	int accountNumber;

	String pin;

	BankAccount(String userName, String pin) {
		this(userName, pin, 1000);
	}

	BankAccount(String userName, String pin, int withdrawLimit) {
		this(userName, pin, withdrawLimit, 0);
	}

	BankAccount(String userName, String pin, int withdrawLimit, int initialBalance) {
		this.userName = userName;
		this.pin = pin;
		this.withdrawLimit = withdrawLimit;
		this.balance = initialBalance;
		this.accountNumber = ++lastAccountNumber;
	}

	void deposit(int amount) {
		if (amount <= 0) {
			System.err.format("Can't deposit $%d, minimum amount should be 1\n", amount);
			return;
		}
		this.balance += amount;
		System.out.format("Deposited: $%d\n", amount);
	}

	void withdraw(int withdraw, String pin) {
		if (withdraw <= 0) {
			System.err.format("Can't withdraw $%d, minimum amount should be 1\n", withdraw);
			return;
		}

		if (!isAuthenticated(pin))
			return;

		if (withdraw > this.withdrawLimit) {
			System.err.println("Can't withdraw: Withdraw amount exceeded your withdraw limit.");
			return;
		}

		if (withdraw > balance) {
			System.err.println("Can't withdraw: Insufficient balance");

		}

		this.balance -= withdraw;
		System.out.format("Withdrawn: $%d\n", withdraw);
	}

	void checkBalance(String pin) {
		if (!isAuthenticated(pin))
			return;

		System.out.format("Current balance: $%d\n", this.balance);
	}

	void accountInformation(String pin) {
		if (!isAuthenticated(pin))
			return;
		System.out.format("%-20s : %d\n", "Account no.", accountNumber);
		System.out.format("%-20s : %s\n", "Account holder", userName);
		System.out.format("%-20s : %d\n", "Withdraw limit", withdrawLimit);
	}

	private boolean isAuthenticated(String pin) {
		if (!this.pin.equals(pin)) {
			System.err.println("Authentication failed: Pin is incorrect");
			return false;
		}
		System.out.println("Authenticated successfully");

		return true;
	}

}

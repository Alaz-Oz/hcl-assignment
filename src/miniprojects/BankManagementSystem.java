package miniprojects;

import java.io.*;
import java.util.*;
import java.io.*;
import java.util.*;

public class BankManagementSystem {
	static ArrayList<Account> accounts = new ArrayList<>();
	static Scanner sc = new Scanner(System.in);
	static File file = new File("bank_data.obj");

	public static void main(String[] args) {
		loadData();
		while (true) {
			System.out.println("\n1. Create Account");
			System.out.println("2. Deposit");
			System.out.println("3. Withdraw");
			System.out.println("4. Balance Enquiry");
			System.out.println("5. Show All Accounts");
			System.out.println("6. Exit");
			System.out.print("Enter choice: ");

			int choice = 0;
			try {
				choice = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Invalid input");
				continue;
			}

			switch (choice) {
			case 1:
				createAccount();
				break;
			case 2:
				performTransaction(1);
				break;
			case 3:
				performTransaction(2);
				break;
			case 4:
				checkBalance();
				break;
			case 5:
				displayAll();
				break;
			case 6:
				saveData();
				return;
			default:
				System.out.println("Invalid choice");
			}
		}
	}

	public static void createAccount() {
		System.out.print("Enter Acc No: ");
		int acc = Integer.parseInt(sc.nextLine());
		System.out.print("Enter Name: ");
		String name = sc.nextLine();
		System.out.print("Enter Initial Balance: ");
		double bal = Double.parseDouble(sc.nextLine());
		System.out.print("Type (1.Savings 2.Current): ");
		int type = Integer.parseInt(sc.nextLine());

		Account newAcc;
		if (type == 1)
			newAcc = new SavingsAccount(acc, name, bal);
		else
			newAcc = new CurrentAccount(acc, name, bal);

		accounts.add(newAcc);
		System.out.println("Account Created");
		saveData();
	}

	public static Account findAccount(int accNo) {
		for (Account a : accounts) {
			if (a.getAccNo() == accNo)
				return a;
		}
		return null;
	}

	public static Account findAccount(String name) {
		for (Account a : accounts) {
			if (a.getName().equalsIgnoreCase(name))
				return a;
		}
		return null;
	}

	public static void performTransaction(int type) {
		System.out.print("Enter Acc No: ");
		int acc = Integer.parseInt(sc.nextLine());
		Account a = findAccount(acc);

		if (a != null) {
			System.out.print("Enter Amount: ");
			double amt = Double.parseDouble(sc.nextLine());
			if (type == 1)
				a.deposit(amt);
			else
				a.withdraw(amt);
			saveData();
		} else {
			System.out.println("Account not found");
		}
	}

	public static void checkBalance() {
		System.out.print("Enter Acc No: ");
		int acc = Integer.parseInt(sc.nextLine());
		Account a = findAccount(acc);
		if (a != null)
			System.out.println("Current Balance: " + a.getBalance());
		else
			System.out.println("Account not found");
	}

	public static void displayAll() {
		for (Account a : accounts) {
			a.displayDetails();
			System.out.println("----------------");
		}
	}

	public static void loadData() {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
			accounts = (ArrayList<Account>) ois.readObject();
		} catch (Exception e) {
			accounts = new ArrayList<>();
		}
	}

	public static void saveData() {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
			oos.writeObject(accounts);
		} catch (IOException e) {
			System.out.println("Error saving data");
		}
	}
}

abstract class Account implements Serializable {
	private int accNo;
	private String name;
	protected double balance;

	public Account(int accNo, String name, double balance) {
		this.accNo = accNo;
		this.name = name;
		this.balance = balance;
	}

	public int getAccNo() {
		return accNo;
	}

	public String getName() {
		return name;
	}

	public double getBalance() {
		return balance;
	}

	public abstract void deposit(double amount);

	public abstract void withdraw(double amount);

	public void displayDetails() {
		System.out.println("Acc No: " + accNo);
		System.out.println("Name: " + name);
		System.out.println("Balance: " + balance);
	}
}

class SavingsAccount extends Account {
	public SavingsAccount(int accNo, String name, double balance) {
		super(accNo, name, balance);
	}

	@Override
	public void deposit(double amount) {
		balance += amount;
		System.out.println("Deposited to Savings. New Balance: " + balance);
	}

	@Override
	public void withdraw(double amount) {
		if (balance - amount >= 500) {
			balance -= amount;
			System.out.println("Withdrawn from Savings. Remaining: " + balance);
		} else {
			System.out.println("Insufficient funds (Min Balance 500 required)");
		}
	}
}

class CurrentAccount extends Account {
	public CurrentAccount(int accNo, String name, double balance) {
		super(accNo, name, balance);
	}

	@Override
	public void deposit(double amount) {
		balance += amount;
		System.out.println("Deposited to Current. New Balance: " + balance);
	}

	@Override
	public void withdraw(double amount) {
		if (balance - amount >= -1000) {
			balance -= amount;
			System.out.println("Withdrawn from Current. Remaining: " + balance);
		} else {
			System.out.println("Overdraft limit reached");
		}
	}
}
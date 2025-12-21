package assignment_2;

public class CustomerBank {
	public static void main(String[] args) {
		Account myAcc = new Account("Afroz", 1000);

		try {
			myAcc.showDetails();

			myAcc.deposit(500);
			myAcc.withdraw(200);

			System.out.println("Attempting to withdraw 5000...");
			myAcc.withdraw(5000);

		} catch (InsufficientBalException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}

class InsufficientBalException extends Exception {
	public InsufficientBalException(String message) {
		super(message);
	}
}

interface ATM {
	void deposit(double amount);

	void withdraw(double amount) throws InsufficientBalException;
}

interface UserProfile {
	void showDetails();
}

class Account implements ATM, UserProfile {
	String name;
	double balance;

	public Account(String name, double balance) {
		this.name = name;
		this.balance = balance;
	}

	public void deposit(double amount) {
		balance += amount;
		System.out.println("Deposited: " + amount);
	}

	public void withdraw(double amount) throws InsufficientBalException {
		if (amount > balance) {
			throw new InsufficientBalException("Insufficient balance!");
		}
		balance -= amount;
		System.out.println("Withdrawn: " + amount);
	}

	public void showDetails() {
		System.out.println("User: " + name + " | Current Balance: " + balance);
	}
}

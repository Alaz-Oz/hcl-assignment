package assignment_2;

import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.InputMismatchException;

public class InputValidator {

	// Exactly 10 digits
	private static final String MOBILE_REGEX = "^[\\d]{10}$";

	// alpha + @ + alpha + . + alpha
	private static final String EMAIL_REGEX = "^[\\w]+@[\\w]+\\.[\\w]+$";

	// Alphanumeric and _, 3 to 15 characters long
	private static final String USERNAME_REGEX = "^[\\w]{3,15}$";

	// 8 or more (space or any symbol)
	private static final String PASSWORD_REGEX = "^[\\S ]{8,}$";

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		loop: while (true) {
			System.out.println("\n--- Input Validation System ---");
			System.out.println("1. Validate Mobile Number");
			System.out.println("2. Validate Email ID");
			System.out.println("3. Validate Username");
			System.out.println("4. Validate Password");
			System.out.println("5. Exit");
			System.out.print("Enter your choice: ");

			int choice = 0;

			try {
				choice = scanner.nextInt();
				scanner.nextLine();
			} catch (InputMismatchException e) {
				System.out.println("Error: Please enter a valid number (1-5).");
				scanner.nextLine();
				continue;
			}

			if (choice == 5) {
				break;
			}

			System.out.print("Enter Input to Validate: ");
			String input = scanner.nextLine();
			boolean result = false;

			switch (choice) {
			case 1:
				result = validate(input, MOBILE_REGEX);
				break;
			case 2:
				result = validate(input, EMAIL_REGEX);
				break;
			case 3:
				result = validate(input, USERNAME_REGEX);
				break;
			case 4:
				result = validate(input, PASSWORD_REGEX);
				break;
			case 5:
				break loop;
			default:
				System.out.println("Invalid Menu Choice.");
				continue;
			}

			// Output Message
			if (result) {
				System.out.println(">> SUCCESS: Welcome! Input is valid.");
			} else {
				System.out.println(">> FAILED: Invalid input format.");
			}
		}
		scanner.close();
	}

	public static boolean validate(String input, String regex) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		return matcher.matches();
	}
}

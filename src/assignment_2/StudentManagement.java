package assignment_2;

import java.util.*;
import java.util.regex.*;

public class StudentManagement {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StudentManager manager = new StudentManager(new ArrayList());

		loop: while (true) {
			System.out.println("\n1. Add Student");
			System.out.println("2. Display All");
			System.out.println("3. Remove Student");
			System.out.println("4. Search Student");
			System.out.println("5. Exit");
			System.out.print("Enter Choice: ");

			int choice = 0;
			try {
				choice = sc.nextInt();
				sc.nextLine();
			} catch (InputMismatchException e) {
				System.out.println("Invalid input! Please enter a number.");
				sc.nextLine();
				continue;
			}

			switch (choice) {
			case 1:
				System.out.print("Enter Name: ");
				String name = sc.nextLine();
				System.out.print("Enter Roll No (3-5 digits): ");
				String roll = sc.nextLine();
				System.out.print("Enter Email: ");
				String email = sc.nextLine();
				manager.addStudent(name, roll, email);
				break;
			case 2:
				manager.displayStudents();
				break;
			case 3:
				System.out.print("Enter Roll No to remove: ");
				manager.removeStudent(sc.nextLine());
				break;
			case 4:
				System.out.print("Enter Roll No to search: ");
				manager.searchStudent(sc.nextLine());
				break;
			case 5:
				System.out.println("Exiting...");
				break loop;
			default:
				System.out.println("Invalid choice.");
			}
		}
		sc.close();
	}
}

class InvalidException extends Exception {
	public InvalidException(String e) {
		super(e);
	}
}

class Student {
	private static final String PATTERN_ROLL = "^\\d{3,5}$";
	private static final String PATTERN_NAME = "^[\\w ]{3,}$";
	private static final String PATTERN_EMAIL = "^[\\w]+@[\\w]+\\.[\\w]+$";

	private String name;
	private String rollNo;
	private String email;

	public Student(String name, String rollNo, String email) throws InvalidException {
		if (!Pattern.matches(PATTERN_NAME, name)) {
			throw new InvalidException("Invalid Name");

		}
		if (!Pattern.matches(PATTERN_ROLL, rollNo)) {
			throw new InvalidException("Invalid Roll Number");
		}
		if (!Pattern.matches(PATTERN_EMAIL, email)) {
			throw new InvalidException("Invalid email");
		}

		this.name = name;
		this.rollNo = rollNo;
		this.email = email;
	}

	public String getRollNo() {
		return rollNo;
	}

	@Override
	public String toString() {
		return "Roll No: " + rollNo + " | Name: " + name + " | Email: " + email;
	}
}

interface StudentOperations {
	void addStudent(UniversityStudent student);

	default void addStudent(String name, String rollNo, String email) {
		try {
			addStudent(new UniversityStudent(name, rollNo, email));
		} catch (InvalidException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	void displayStudents();

	void removeStudent(String rollNo);

	void searchStudent(String rollNo);
}

class StudentManager implements StudentOperations {
	private List<UniversityStudent> students;

	public StudentManager(List<UniversityStudent> list) {
		this.students = list;
	}

	@Override
	public void addStudent(UniversityStudent student) {

		// Check for duplicate
		for (UniversityStudent s : students) {
			if (s.getRollNo().equals(student.getRollNo())) {
				System.out.println("Error: Roll Number already exists.");
				return;
			}
		}

		students.add(student);
		System.out.println("Success: Student added.");

	}

	@Override
	public void displayStudents() {
		if (students.isEmpty()) {
			System.out.println("No records found.");
			return;
		}
		System.out.println("--- Student List ---");
		for (UniversityStudent s : students) {
			System.out.println(s);
		}
	}

	@Override
	public void removeStudent(String rollNo) {
		Iterator<UniversityStudent> iterator = students.iterator();
		while (iterator.hasNext()) {
			UniversityStudent s = iterator.next();
			if (s.getRollNo().equals(rollNo)) {
				iterator.remove();
				System.out.println("Success: Student removed.");
				return;
			}
		}
		System.err.println("Error: Student not found.");
	}

	@Override
	public void searchStudent(String rollNo) {
		for (UniversityStudent s : students) {
			if (s.getRollNo().equals(rollNo)) {
				System.out.println("Found: " + s);
				return;
			}
		}
		System.out.println("Error: Student not found.");
	}

}
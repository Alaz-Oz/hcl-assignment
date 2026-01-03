package miniprojects;

import java.util.*;

public class StudentManagementSystem {
	static ArrayList<Student> students = new ArrayList<>();
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.print("Username: ");
		String user = sc.nextLine();
		System.out.print("Password: ");
		String pass = sc.nextLine();

		if (user.equals("afroz") && pass.equals("123")) {
			System.out.println("Login Successful");
			runMenu();
		} else {
			System.out.println("Invalid Credentials");
		}
	}

	public static void runMenu() {
		while (true) {
			System.out.println(
					"\n1. Add Student\n2. Display All\n3. Search\n4. Update Branch\n5. Delete\n6. Sort\n7. Exit");
			System.out.print("Enter choice: ");

			try {
				int choice = Integer.parseInt(sc.nextLine());
				switch (choice) {
				case 1:
					addStudent();
					break;
				case 2:
					displayAll();
					break;
				case 3:
					searchStudent();
					break;
				case 4:
					updateBranch();
					break;
				case 5:
					deleteStudent();
					break;
				case 6:
					displaySorted();
					break;
				case 7:
					return;
				default:
					System.out.println("Invalid choice");
				}
			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage());
			}
		}
	}

	public static void addStudent() {
		try {
			System.out.print("Enter Eno: ");
			int eno = Integer.parseInt(sc.nextLine());

			for (Student s : students) {
				if (s.getEno() == eno)
					throw new InvalidInputException("Eno exists");
			}

			System.out.print("Enter Name: ");
			String name = sc.nextLine();
			System.out.print("Enter Branch: ");
			String branch = sc.nextLine();
			if (branch.isEmpty())
				throw new InvalidInputException("Branch empty");

			System.out.print("Enter Sem: ");
			int sem = Integer.parseInt(sc.nextLine());
			System.out.print("Enter Percentage: ");
			double per = Double.parseDouble(sc.nextLine());
			if (per < 0)
				throw new InvalidInputException("Percentage invalid");

			students.add(new Student(eno, name, branch, sem, per));
			System.out.println("Added");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void displayAll() {
		for (Student s : students)
			System.out.println(s);
	}

	public static void searchStudent() {
		try {
			System.out.print("Enter Eno: ");
			int eno = Integer.parseInt(sc.nextLine());
			boolean found = false;
			for (Student s : students) {
				if (s.getEno() == eno) {
					System.out.println(s);
					found = true;
				}
			}
			if (!found)
				System.out.println("Not Found");
		} catch (Exception e) {
			System.out.println("Invalid Input");
		}
	}

	public static void updateBranch() {
		try {
			System.out.print("Enter Eno: ");
			int eno = Integer.parseInt(sc.nextLine());
			for (Student s : students) {
				if (s.getEno() == eno) {
					System.out.print("New Branch: ");
					String b = sc.nextLine();
					if (b.isEmpty())
						throw new InvalidInputException("Empty branch");
					s.setBranch(b);
					System.out.println("Updated");
					return;
				}
			}
			System.out.println("Not Found");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void deleteStudent() {
		try {
			System.out.print("Enter Eno: ");
			int eno = Integer.parseInt(sc.nextLine());
			boolean removed = students.removeIf(s -> s.getEno() == eno);
			System.out.println(removed ? "Deleted" : "Not Found");
		} catch (Exception e) {
			System.out.println("Invalid Input");
		}
	}

	public static void displaySorted() {
		Collections.sort(students);
		displayAll();
	}
}

class Student implements Comparable<Student> {
	private int eno;
	private String name;
	private String branch;
	private int sem;
	private double percentage;

	public Student(int eno, String name, String branch, int sem, double percentage) {
		this.eno = eno;
		this.name = name;
		this.branch = branch;
		this.sem = sem;
		this.percentage = percentage;
	}

	public int getEno() {
		return eno;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	@Override
	public String toString() {
		return "Eno: " + eno + " | Name: " + name + " | Branch: " + branch + " | Sem: " + sem + " | Per: " + percentage;
	}

	@Override
	public int compareTo(Student other) {
		return this.eno - other.eno;
	}
}

class InvalidInputException extends Exception {
	public InvalidInputException(String message) {
		super(message);
	}
}

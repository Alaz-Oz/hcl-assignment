package miniprojects;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class EmployeeManagementSystem {
	static ArrayList<Employee> employees = new ArrayList<>();
	static File file = new File("employee_data.obj");
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		loadData();
		System.out.println("=== LOGIN ===");
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
					"\n1. Add Employee\n2. Display All\n3. Search by ID\n4. Update Salary\n5. Delete Employee\n6. Sort Employees\n7. Display Departments\n8. Exit");
			System.out.print("Enter Choice: ");

			try {
				int choice = Integer.parseInt(sc.nextLine());
				switch (choice) {
				case 1:
					addEmployee();
					break;
				case 2:
					displayAll();
					break;
				case 3:
					searchEmployee();
					break;
				case 4:
					updateSalary();
					break;
				case 5:
					deleteEmployee();
					break;
				case 6:
					displaySorted();
					break;
				case 7:
					displayDepartments();
					break;
				case 8:
					return;
				default:
					System.out.println("Invalid Choice");
				}
			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage());
			}
		}
	}

	public static void addEmployee() {
		try {
			System.out.print("Enter ID: ");
			int id = Integer.parseInt(sc.nextLine());
			for (Employee e : employees) {
				if (e.getId() == id)
					throw new ValidationException("ID must be unique");
			}

			System.out.print("Enter Name: ");
			String name = sc.nextLine();

			System.out.print("Enter Department: ");
			String dept = sc.nextLine();
			if (dept.trim().isEmpty())
				throw new ValidationException("Department cannot be empty");

			System.out.print("Enter Salary: ");
			double sal = Double.parseDouble(sc.nextLine());
			if (sal <= 0)
				throw new ValidationException("Salary must be positive");

			employees.add(new Employee(id, name, dept, sal));
			saveData();
			System.out.println("Employee Added");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void displayAll() {
		if (employees.isEmpty())
			System.out.println("No Records");
		for (Employee e : employees)
			System.out.println(e);
	}

	public static void searchEmployee() {
		try {
			System.out.print("Enter ID: ");
			int id = Integer.parseInt(sc.nextLine());
			boolean found = false;
			for (Employee e : employees) {
				if (e.getId() == id) {
					System.out.println(e);
					found = true;
					break;
				}
			}
			if (!found)
				System.out.println("Not Found");
		} catch (Exception e) {
			System.out.println("Invalid Input");
		}
	}

	public static void updateSalary() {
		try {
			System.out.print("Enter ID: ");
			int id = Integer.parseInt(sc.nextLine());
			boolean found = false;
			for (Employee e : employees) {
				if (e.getId() == id) {
					System.out.print("Enter New Salary: ");
					double sal = Double.parseDouble(sc.nextLine());
					if (sal <= 0)
						throw new ValidationException("Salary must be positive");
					e.setSalary(sal);
					saveData();
					System.out.println("Updated");
					found = true;
					break;
				}
			}
			if (!found)
				System.out.println("Not Found");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void deleteEmployee() {
		try {
			System.out.print("Enter ID: ");
			int id = Integer.parseInt(sc.nextLine());
			boolean removed = employees.removeIf(e -> e.getId() == id);
			if (removed) {
				saveData();
				System.out.println("Deleted");
			} else {
				System.out.println("Not Found");
			}
		} catch (Exception e) {
			System.out.println("Invalid Input");
		}
	}

	public static void displaySorted() {
		Collections.sort(employees);
		displayAll();
	}

	public static void displayDepartments() {
		System.out.println("Departments:");
		Set<String> depts = employees.stream().map(Employee::getDepartment).collect(Collectors.toSet());
		for (String d : depts)
			System.out.println("- " + d);
	}

	public static void saveData() {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
			oos.writeObject(employees);
		} catch (IOException e) {
			System.out.println("Error Saving File");
		}
	}

	public static void loadData() {
		if (!file.exists())
			return;
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
			employees = (ArrayList<Employee>) ois.readObject();
		} catch (Exception e) {
			employees = new ArrayList<>();
		}
	}
}

class Employee implements Serializable, Comparable<Employee> {
	private int id;
	private String name;
	private String department;
	private double salary;

	public Employee(int id, String name, String department, double salary) {
		this.id = id;
		this.name = name;
		this.department = department;
		this.salary = salary;
	}

	public int getId() {
		return id;
	}

	public String getDepartment() {
		return department;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "ID: " + id + " | Name: " + name + " | Dept: " + department + " | Salary: " + salary;
	}

	@Override
	public int compareTo(Employee other) {
		return this.id - other.id;
	}
}

class ValidationException extends Exception {
	public ValidationException(String message) {
		super(message);
	}
}

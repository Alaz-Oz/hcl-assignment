package assignment_2;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Pattern;

public class EmployeeManagement {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Map<String, Employee> map = null;

		System.out.println("Select Storage Type:");
		System.out.println("1. HashMap");
		System.out.println("2. Hashtable");
		System.out.println("3. TreeMap");
		System.out.print("Choice: ");

		int type = sc.nextInt();
		sc.nextLine();

		switch (type) {
		case 2:
			map = new Hashtable<>();
			break;
		case 3:
			map = new TreeMap<>();
			break;
		default:
			System.out.println("Defaulting to HashMap.");
		case 1:
			map = new HashMap<>();
			break;
		}

		EmployeeManager manager = new EmployeeManager(map);

		loop: while (true) {
			System.out.println("\n--- Employee Management ---");
			System.out.println("1. Add Employee");
			System.out.println("2. Display All");
			System.out.println("3. Remove Employee");
			System.out.println("4. Search Employee");
			System.out.println("5. Demonstrate Null Key/Value");
			System.out.println("6. Exit");
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
				System.out.print("Enter Emp ID (3-5 digits): ");
				String id = sc.nextLine();
				System.out.print("Enter Department: ");
				String dept = sc.nextLine();
				manager.addEmployee(name, id, dept);
				break;
			case 2:
				manager.displayEmployees();
				break;
			case 3:
				System.out.print("Enter Emp ID to remove: ");
				manager.removeEmployee(sc.nextLine());
				break;
			case 4:
				System.out.print("Enter Emp ID to search: ");
				manager.searchEmployee(sc.nextLine());
				break;
			case 5:
				manager.demonstrateNullSupport();
				break;
			case 6:
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

class Employee {

	private static final String PATTERN_ID = "^\\d{3,5}$";
	private static final String PATTERN_NAME = "^[\\w ]{3,}$";
	private static final String PATTERN_DEPT = "^[\\w]{2,}$";

	private String name;
	private String empId;
	private String department;

	public Employee(String name, String empId, String department) throws InvalidException {
		if (!Pattern.matches(PATTERN_NAME, name)) {
			throw new InvalidException("Invalid Name");
		}
		if (!Pattern.matches(PATTERN_ID, empId)) {
			throw new InvalidException("Invalid ID");
		}
		if (!Pattern.matches(PATTERN_DEPT, department)) {
			throw new InvalidException("Invalid Department");
		}

		this.name = name;
		this.empId = empId;
		this.department = department;
	}

	public String getEmpId() {
		return empId;
	}

	@Override
	public String toString() {
		return "ID: " + empId + " | Name: " + name + " | Dept: " + department;
	}
}

interface EmployeeOperations {

	default void addEmployee(String name, String empId, String department) {
		try {
			addEmployee(new Employee(name, empId, department));
		} catch (InvalidException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	void addEmployee(Employee employee);

	void displayEmployees();

	void removeEmployee(String empId);

	void searchEmployee(String empId);

	void demonstrateNullSupport();
}

class EmployeeManager implements EmployeeOperations {
	private Map<String, Employee> employees;

	public EmployeeManager(Map<String, Employee> map) {
		this.employees = map;
	}

	@Override
	public void addEmployee(Employee employee) {
		if (employees.containsKey(employee.getEmpId())) {
			System.out.println("Error: Employee ID already exists.");
			return;
		}
		employees.put(employee.getEmpId(), employee);
		System.out.println("Success: Employee added.");
	}

	@Override
	public void displayEmployees() {
		if (employees.isEmpty()) {
			System.out.println("No records found.");
			return;
		}
		System.out.println("--- Employee List ---");

		for (Employee e : employees.values()) {
			System.out.println(e);
		}
	}

	@Override
	public void removeEmployee(String empId) {
		if (employees.containsKey(empId)) {
			employees.remove(empId);
			System.out.println("Success: Employee removed.");
		} else {
			System.err.println("Error: Employee not found.");
		}
	}

	@Override
	public void searchEmployee(String empId) {
		if (employees.containsKey(empId)) {
			System.out.println("Found: " + employees.get(empId));
		} else {
			System.out.println("Error: Employee not found.");
		}
	}

	@Override
	public void demonstrateNullSupport() {
		System.out.println("Attempting to insert 'null' key and 'null' value...");
		try {
			// Attempt to put null key and null value
			employees.put(null, null);
			System.out.println(">> SUCCESS: This Map support null!");

		} catch (NullPointerException e) {
			System.out.println(">> FAILED: This Map doesn't support null!");
		} catch (Exception e) {
			System.out.println(">> FAILED: " + e.getMessage());
		}
	}
}
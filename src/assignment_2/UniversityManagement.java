package assignment_2;

import java.util.*;
import java.util.regex.*;

class UniversityStudent {
	private static final String PATTERN_ID = "^\\d{3,5}$";
	private static final String PATTERN_NAME = "^[\\w ]{3,}$";
	private static final String PATTERN_COURSE = "^[\\w]{3,}$";

	String id, name, course;
	double marks;

	public UniversityStudent(String id, String name, String course, double marks) throws InvalidException {
		if (!Pattern.matches(PATTERN_ID, id)) {
			throw new InvalidException("Invalid ID");
		}
		if (!Pattern.matches(PATTERN_NAME, name)) {
			throw new InvalidException("Invalid Name");

		}
		if (!Pattern.matches(PATTERN_COURSE, course)) {
			throw new InvalidException("Invalid course");
		}
		this.id = id;
		this.name = name;
		this.course = course;
		this.marks = marks;
	}

	public String toString() {
		return "ID: " + id + " | Name: " + name + " | Course: " + course + " | Marks: " + marks;
	}
}

interface UniversityOperations {
	void addStudent(String id, String name, String course, double marks);

	void displayAll();

	void removeStudent(String id);

	void searchStudent(String id);

	void sortStudentsByMarks();

	void convertToTreeMap();

	void courseStatistics();
}

class UniversityManager implements UniversityOperations {

	Map<String, UniversityStudent> map = new HashMap<>();
	Set<String> courses = new HashSet<>();

	@Override
	public void addStudent(String id, String name, String course, double marks) {
		try {
			if (map.containsKey(id))
				throw new Exception("Duplicate ID.");

			UniversityStudent s = new UniversityStudent(id, name, course, marks);

			map.put(id, s);
			courses.add(course);

			System.out.println("Student Added.");
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	@Override
	public void displayAll() {
		System.out.println("--- All Students ---");
		for (UniversityStudent s : map.values())
			System.out.println(s);
	}

	@Override
	public void removeStudent(String id) {
		if (map.containsKey(id)) {
			UniversityStudent s = map.remove(id);
			System.out.println("Removed: " + s.name);
		} else {
			System.out.println("Student not found.");
		}
	}

	@Override
	public void searchStudent(String id) {
		if (map.containsKey(id)) {
			System.out.println("Found: " + map.get(id));
		} else {
			System.out.println("Not Found.");
		}
	}

	@Override
	public void sortStudentsByMarks() {
		// Using ArrayList for sorting
		ArrayList<UniversityStudent> list = new ArrayList<>(map.values());
		Collections.sort(list, (s1, s2) -> Double.compare(s2.marks, s1.marks));

		System.out.println("\n--- Sorted by Marks (ArrayList) ---");
		for (UniversityStudent s : list)
			System.out.println(s);
	}

	@Override
	public void convertToTreeMap() {
		map = new TreeMap<>(map);
	}

	@Override
	public void courseStatistics() {

		System.out.println("\nUnique Courses: " + courses);

		Map<String, Integer> counts = new HashMap<>();
		for (UniversityStudent s : map.values()) {
			counts.put(s.course, counts.getOrDefault(s.course, 0) + 1);
		}
		System.out.println("Course Counts: " + counts);

	}
}

public class UniversityManagement {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		UniversityManager um = new UniversityManager();

		while (true) {
			System.out.println(
					"\n1. Add 2. Display 3. Remove 4. Search 5. Sort(Marks) 6. TreeMap 7. Course Stats 8. Exit");
			System.out.print("Choice: ");
			try {
				int ch = sc.nextInt();
				sc.nextLine();

				switch (ch) {
				case 1:
					System.out.print("ID, Name, Course, Marks: ");

					um.addStudent(sc.next(), sc.next(), sc.next(), sc.nextDouble());
					break;
				case 2:
					um.displayAll();
					break;
				case 3:
					System.out.print("ID: ");
					um.removeStudent(sc.next());
					break;
				case 4:
					System.out.print("ID: ");
					um.searchStudent(sc.next());
					break;
				case 5:
					um.sortStudentsByMarks();
					break;
				case 6:
					um.convertToTreeMap();
					break;
				case 7:
					um.courseStatistics();
					break;
				case 8:
					System.out.println("Bye");
					sc.close();
					return;
				default:
					System.out.println("Invalid.");
				}
			} catch (Exception e) {
				System.out.println("Input Error!");
				sc.nextLine();
			}
		}
	}
}
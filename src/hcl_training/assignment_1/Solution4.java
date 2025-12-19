package hcl_training.assignment_1;

import java.util.Scanner;

public class Solution4 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("String A");
		String a = sc.nextLine();
		System.out.println("String B");
		String b = sc.nextLine();
		System.out.println("================");
		System.out.println(a.toString());
		System.out.println(b.toString());

		System.out.println("String A length: " + a.length());
		System.out.println("String B length: " + b.length());

		System.out.println("String A is " + (a.isEmpty() ? "" : "not ") + "empty");
		System.out.println("String B is " + (b.isEmpty() ? "" : "not ") + "empty");

		System.out.println("Char at index 0 for String A is: " + a.charAt(0));
		System.out.println("Char at index 0 for String B is: " + b.charAt(0));

		System.out.println("String A and B are " + (a.equals(b) ? "" : "not ") + "equal");

		System.out.println("String A is lexicographically "
				+ (a.compareTo(b) < 0 ? "less than" : a.compareTo(b) > 0 ? "greater than " : "equal to") + "String B");

		if (a.contains("Hello")) {
			System.out.println("Index of \"Hello\" is " + a.indexOf("Hello"));
			System.out.println("Last index of \"Hello\" is " + a.indexOf("Hello"));
		}

		System.out.println("String A does " + (a.startsWith(b) ? "" : "not ") + "starts with " + b);
		System.out.println("String A does " + (a.endsWith(b) ? "" : "not ") + "ends with " + b);

		System.out.println(a.matches("\\d+") ? "A contains only digits" : "A is text");
		System.out.println(a.substring(0, a.length() / 2));
		System.out.println(a.toLowerCase().trim().replace("Hello", "Hey"));

		for (String word : a.split(" ")) {
			System.out.print("<" + word + ">");
		}
		System.out.println();
		System.out.println(String.join("-", b.split(" ")));

		System.out.println(String.valueOf(3.55f));

		sc.close();
	}
}

package hcl_training.assignment_1;

import java.util.Scanner;

public class Solution1 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int a, b;
		System.out.print("Enter value for A: ");
		a = sc.nextInt();
		System.out.print("Enter value for B: ");
		b = sc.nextInt();

		System.out.println("Summation\t\t: " + Mathematics.add(a, b));
		System.out.println("Subtraction\t\t: " + Mathematics.subtract(a, b));
		System.out.println("Multiplication\t\t: " + Mathematics.multiply(a, b));
		System.out.println("Division\t\t: " + Mathematics.divide(a, b));
		System.out.println("Remainder\t\t: " + Mathematics.remainder(a, b));

		System.out.println("--- Unary Operations ---");

		System.out.println("Square of A\t\t: " + Mathematics.square(a));
		System.out.println("Cube of A\t\t: " + Mathematics.cube(a));
		System.out.println("Absolute of A\t\t: " + Mathematics.absolute(a));
		
		sc.close();
	}
}

class Mathematics {

	public static int add(int first, int second) {
		return first + second;
	}

	public static int subtract(int first, int second) {
		return first - second;
	}

	public static int multiply(int first, int second) {
		return first * second;
	}

	public static int divide(int first, int second) {
		return first / second;
	}

	public static int remainder(int first, int second) {
		return first % second;
	}

	public static int square(int number) {
		return number * number;
	}

	public static int cube(int number) {
		return number * number * number;
	}

	public static int absolute(int number) {
		return (number >= 0) ? number : -number;
	}

}
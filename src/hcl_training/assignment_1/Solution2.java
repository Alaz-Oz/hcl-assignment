package hcl_training.assignment_1;

import java.util.Scanner;

public class Solution2 {

	final static String[] options = { "Insertion", "Deletion", "Linear Search", "Binary Search", "Count Even & Odd",
			"Insertion Sort", "Exit" };

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		Array arr = new Array();

		while (true) {
			printOptions();
			System.out.print("Select Option: ");
			int option = sc.nextInt();

			switch (option) {
			case 1:
				arr.insert(sc);
				break;
			case 2:
				arr.delete(sc);
				break;
			case 3:
				arr.linearSearch(sc);
				break;
			case 4:
				arr.binarySearch(sc);
				break;
			case 5:
				arr.countEvenOdd();
				break;
			case 6:
				arr.insertionSort();
				break;
			case 7:
				System.exit(0);
				break;
			default:
				System.out.println("Please select valid option");
			}
			System.out.println();
			arr.display();
			waitAfterResult(sc);

		}

	}

	public static void waitAfterResult(Scanner sc) {
		System.out.println("Press enter key to continue...");
		sc.nextLine();
		sc.nextLine();
	}

	public static void printOptions() {
		System.out.println("===========| Menu |============");

		for (int i = 0; i < options.length; i++) {
			System.out.format("| %2d. %-23s |\n", (i + 1), options[i]);
		}
		System.out.println("===============================");
	}

}

class Array {

//	int[] arr = new int[100];
	int[] arr = { 1, 3, 4, 543, 55, 3, 33, 4 };
	int currentSize = 0;

	public void insert(Scanner sc) {
		if (currentSize == arr.length) {
			System.out.println("Can't insert: Array is full!");
			return;
		}
		System.out.print("Enter value to insert: ");

		int val = sc.nextInt();
		arr[currentSize++] = val;

		System.out.println("Value inserted");
	}

	public void delete(Scanner sc) {
		System.out.print("Enter index to delete: ");
		int index = sc.nextInt();
		if (index >= currentSize) {
			System.out.println("Can't delete: Index is not available");
			return;
		}
		for (int i = index; i < currentSize - 1; i++) {
			arr[i] = arr[i + 1];
		}
		currentSize--;
		System.out.println("Value deleted");
	}

	public void linearSearch(Scanner sc) {
		System.out.print("Enter value to search: ");
		int val = sc.nextInt();

		for (int i = 0; i < currentSize; i++) {
			if (arr[i] == val) {
				System.out.println("Element found at index: " + i);
				return;
			}
		}
		System.out.println("Element NOT found");

	}

	public void binarySearch(Scanner sc) {
		System.out.print("Enter value to search: ");
		int val = sc.nextInt();

		int low = 0, high = currentSize - 1;

		while (low <= high) {
			int mid = low + (high - low) / 2;

			if (val < arr[mid]) {
				high = mid - 1;
			} else if (val > arr[mid]) {
				low = mid + 1;
			} else {
				System.out.println("Element found at index: " + mid);
				return;
			}
		}

		System.out.println("Element NOT found");

	}

	public void countEvenOdd() {
		int even = 0;
		for (int i = 0; i < currentSize; i++) {
			if (arr[i] % 2 == 0) {
				even++;
			}
		}

		System.out.println("Even: " + even + "\tOdd: " + (currentSize - even));
	}

	public void insertionSort() {

		for (int i = 1; i < currentSize; i++) {
			int elem = arr[i];
			int j = i - 1;
			while (j >= 0 && elem < arr[j]) {
				arr[j + 1] = arr[j];
				j--;
			}
			arr[++j] = elem;
		}

		System.out.println("Sorting complete");
	}

	public void display() {
		int length = currentSize * 8;
		System.out.format("%" + ((length / 2) + 6) + "s\n", "<| Result |>");
		for (int i = 0; i < length; i++) {
			System.out.print("-");
		}
		System.out.println();

		for (int i = 0; i < currentSize; i++) {
			System.out.format("| %5d ", arr[i]);
		}
		if (currentSize > 0)
			System.out.println("|");

		for (int i = 0; i < length; i++) {
			System.out.print("-");
		}
		System.out.println();
	}

}
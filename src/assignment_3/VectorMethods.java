package assignment_3;

import java.util.Vector;
import java.util.Arrays;
import java.util.Enumeration;

public class VectorMethods {
	public static void main(String[] args) {
		System.out.println("--- Constructors ---");
		// 1. Default Constructor
		Vector<Integer> v1 = new Vector<>();

		// 2. Constructor with initial capacity
		Vector<Integer> v2 = new Vector<>(10);

		// 3. Constructor with capacity and increment
		Vector<Integer> v3 = new Vector<>(5, 2);

		// 4. Constructor accepting a Collection
		Vector<Integer> v4 = new Vector<>(Arrays.asList(1, 2, 3));
		System.out.println("Vector4 created: " + v4);

		System.out.println("\n--- 15 Methods Demonstration ---");

		// 1. addElement(E obj) - Legacy method
		v1.addElement(10);
		v1.addElement(20);
		v1.addElement(30);
		System.out.println("Vector: " + v1);

		// 2. add(index, element)
		v1.add(1, 15);
		System.out.println("Inserted 15 at index 1: " + v1);

		// 3. elementAt(int index) - Legacy
		System.out.println("Element at 2: " + v1.elementAt(2));

		// 4. firstElement()
		System.out.println("First Element: " + v1.firstElement());

		// 5. lastElement()
		System.out.println("Last Element: " + v1.lastElement());

		// 6. setElementAt(E obj, int index)
		v1.setElementAt(99, 0);
		System.out.println("Set index 0 to 99: " + v1);

		// 7. capacity()
		System.out.println("Current Capacity: " + v1.capacity());

		// 8. contains(Object o)
		System.out.println("Contains 20? " + v1.contains(20));

		// 9. indexOf(Object o)
		System.out.println("Index of 20: " + v1.indexOf(20));

		// 10. removeElement(Object obj)
		v1.removeElement(99);
		System.out.println("Removed 99: " + v1);

		// 11. remove(int index)
		v1.remove(0);
		System.out.println("Removed index 0: " + v1);

		// 12. elements() - Returns Enumeration (Legacy)
		System.out.print("Enumeration: ");
		Enumeration<Integer> e = v1.elements();
		while (e.hasMoreElements())
			System.out.print(e.nextElement() + " ");
		System.out.println();

		// 13. size()
		System.out.println("Size: " + v1.size());

		// 14. isEmpty()
		System.out.println("Is Empty? " + v1.isEmpty());

		// 15. removeAllElements()
		v1.removeAllElements();
		System.out.println("After removeAllElements: " + v1);
	}
}
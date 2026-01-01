package assignment_3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayListMethods {
	public static void main(String[] args) {
		System.out.println("--- Constructors ---");
		// 1. Default Constructor
		ArrayList<String> list1 = new ArrayList<>();

		// 2. Constructor with initial capacity
		ArrayList<String> list2 = new ArrayList<>(20);

		// 3. Constructor accepting a Collection
		List<String> seeds = Arrays.asList("Java", "Python");
		ArrayList<String> list3 = new ArrayList<>(seeds);
		System.out.println("List3 created from collection: " + list3);

		System.out.println("\n--- 15 Methods Demonstration ---");

		// 1. add(E e)
		list1.add("Apple");
		list1.add("Banana");

		// 2. add(int index, E element)
		list1.add(1, "Orange");
		System.out.println("After adds: " + list1);

		// 3. addAll(Collection c)
		list1.addAll(list3);
		System.out.println("After addAll: " + list1);

		// 4. get(int index)
		System.out.println("Element at index 2: " + list1.get(2));

		// 5. set(int index, E element)
		list1.set(0, "Mango");
		System.out.println("After set (index 0 to Mango): " + list1);

		// 6. remove(int index)
		list1.remove(2);
		System.out.println("After remove index 2: " + list1);

		// 7. remove(Object o)
		list1.remove("Java");
		System.out.println("After remove object 'Java': " + list1);

		// 8. contains(Object o)
		System.out.println("Contains 'Mango'? " + list1.contains("Mango"));

		// 9. indexOf(Object o)
		System.out.println("Index of 'Banana': " + list1.indexOf("Banana"));

		// 10. size()
		System.out.println("Size of list: " + list1.size());

		// 11. isEmpty()
		System.out.println("Is list empty? " + list1.isEmpty());

		// 12. subList(int fromIndex, int toIndex)
		List<String> sub = list1.subList(0, 2);
		System.out.println("SubList (0 to 2): " + sub);

		// 13. toArray()
		Object[] arr = list1.toArray();
		System.out.println("Converted to Array: " + Arrays.toString(arr));

		// 14. ensureCapacity(int minCapacity)
		list1.ensureCapacity(50); // It will ennsure that there is enough room for 50 elements.
		System.out.println("Capacity ensured to 50");

		// 15. clear()
		list1.clear();
		System.out.println("After clear: " + list1);
	}
}
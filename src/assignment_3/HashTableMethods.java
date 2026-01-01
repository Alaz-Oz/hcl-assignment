package assignment_3;

import java.util.Hashtable;
import java.util.Map;
import java.util.Enumeration;

public class HashTableMethods {
	public static void main(String[] args) {
		System.out.println("--- Constructors ---");
		// 1. Default
		Hashtable<Integer, String> ht1 = new Hashtable<>();

		// 2. Capacity
		Hashtable<Integer, String> ht2 = new Hashtable<>(20);

		// 3. Capacity and Load Factor
		Hashtable<Integer, String> ht3 = new Hashtable<>(20, 0.5f);

		// 4. From Map
		Hashtable<Integer, String> ht4 = new Hashtable<>(Map.of(1, "A"));
		System.out.println("HT4 created: " + ht4);

		System.out.println("\n--- 15 Methods Demonstration ---");

		// 1. put(K, V)
		ht1.put(101, "John");
		ht1.put(102, "Doe");
		ht1.put(103, "Smith");
		System.out.println("Table: " + ht1);

		// 2. get(Object key)
		System.out.println("Get 102: " + ht1.get(102));

		// 3. containsKey(Object key)
		System.out.println("Has key 101? " + ht1.containsKey(101));

		// 4. contains(Object value) - Legacy (same as containsValue)
		System.out.println("Contains 'Smith'? " + ht1.contains("Smith"));

		// 5. containsValue(Object value) - Modern
		System.out.println("Contains Value 'John'? " + ht1.containsValue("John"));

		// 6. putIfAbsent(K, V)
		ht1.putIfAbsent(104, "Jane");
		System.out.println("After putIfAbsent: " + ht1);

		// 7. remove(Object key)
		ht1.remove(103);
		System.out.println("Removed 103: " + ht1);

		// 8. replace(K, V)
		ht1.replace(101, "Johnny");
		System.out.println("Replaced 101: " + ht1);

		// 9. keys() - Legacy Enumeration
		System.out.print("Keys Enumeration: ");
		Enumeration<Integer> k = ht1.keys();
		while (k.hasMoreElements())
			System.out.print(k.nextElement() + " ");
		System.out.println();

		// 10. elements() - Legacy Enumeration
		System.out.print("Values Enumeration: ");
		Enumeration<String> v = ht1.elements();
		while (v.hasMoreElements())
			System.out.print(v.nextElement() + " ");
		System.out.println();

		// 11. keySet()
		System.out.println("Key Set: " + ht1.keySet());

		// 12. values()
		System.out.println("Values Collection: " + ht1.values());

		// 13. computeIfAbsent
		ht1.computeIfAbsent(105, key -> "User" + key);
		System.out.println("Computed 105: " + ht1);

		// 14. size()
		System.out.println("Size: " + ht1.size());

		// 15. clear()
		ht1.clear();
		System.out.println("Cleared: " + ht1);
	}
}
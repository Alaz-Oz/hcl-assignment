package assignment_3;

import java.util.TreeMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.Comparator;

public class TreeMapMethods {
	public static void main(String[] args) {
		System.out.println("--- Constructors ---");
		// 1. Default (Natural Ordering)
		TreeMap<Integer, String> tm1 = new TreeMap<>();

		// 2. Comparator Constructor (Reverse Order)
		TreeMap<Integer, String> tm2 = new TreeMap<>(Comparator.reverseOrder());

		// 3. From Map
		Map<Integer, String> input = Map.of(3, "Three", 1, "One", 2, "Two");
		TreeMap<Integer, String> tm3 = new TreeMap<>(input);

		// 4. From SortedMap
		SortedMap<Integer, String> sm = new TreeMap<>(input);
		TreeMap<Integer, String> tm4 = new TreeMap<>(sm);
		System.out.println("TM4 from SortedMap: " + tm4);

		System.out.println("\n--- 15 Methods Demonstration ---");

		// Setup data
		tm1.put(10, "Ten");
		tm1.put(50, "Fifty");
		tm1.put(20, "Twenty");
		tm1.put(40, "Forty");
		tm1.put(30, "Thirty");
		System.out.println("TreeMap (Sorted): " + tm1);

		// 1. firstKey()
		System.out.println("First Key: " + tm1.firstKey());

		// 2. lastKey()
		System.out.println("Last Key: " + tm1.lastKey());

		// 3. firstEntry()
		System.out.println("First Entry: " + tm1.firstEntry());

		// 4. lastEntry()
		System.out.println("Last Entry: " + tm1.lastEntry());

		// 5. floorKey(K key) - greatest key <= given key
		System.out.println("Floor Key of 25: " + tm1.floorKey(25));

		// 6. ceilingKey(K key) - smallest key >= given key
		System.out.println("Ceiling Key of 25: " + tm1.ceilingKey(25));

		// 7. lowerKey(K key) - strictly less than
		System.out.println("Lower Key of 20: " + tm1.lowerKey(20));

		// 8. higherKey(K key) - strictly greater than
		System.out.println("Higher Key of 20: " + tm1.higherKey(20));

		// 9. subMap(from, to)
		System.out.println("SubMap (20-50): " + tm1.subMap(20, 50));

		// 10. headMap(to)
		System.out.println("HeadMap (<30): " + tm1.headMap(30));

		// 11. tailMap(from)
		System.out.println("TailMap (>=30): " + tm1.tailMap(30));

		// 12. pollFirstEntry() - retrieves and removes
		System.out.println("Polled First: " + tm1.pollFirstEntry());

		// 13. pollLastEntry() - retrieves and removes
		System.out.println("Polled Last: " + tm1.pollLastEntry());
		System.out.println("Map after polls: " + tm1);

		// 14. descendingMap()
		System.out.println("Descending View: " + tm1.descendingMap());

		// 15. size()
		System.out.println("Final Size: " + tm1.size());
	}
}
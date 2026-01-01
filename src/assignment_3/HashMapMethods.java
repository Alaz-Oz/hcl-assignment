package assignment_3;

import java.util.HashMap;
import java.util.Map;

public class HashMapMethods {
	public static void main(String[] args) {
		System.out.println("--- Constructors ---");
		// 1. Default Constructor
		HashMap<String, Integer> map1 = new HashMap<>();

		// 2. With initial capacity
		HashMap<String, Integer> map2 = new HashMap<>(20);

		// 3. With capacity and load factor
		HashMap<String, Integer> map3 = new HashMap<>(20, 0.8f);

		// 4. From another Map
		Map<String, Integer> seed = Map.of("One", 1);
		HashMap<String, Integer> map4 = new HashMap<>(seed);
		System.out.println("Map4 from seed: " + map4);

		System.out.println("\n--- 15 Methods Demonstration ---");

		// 1. put(K key, V value)
		map1.put("Java", 100);
		map1.put("Python", 90);
		map1.put("C++", 80);
		System.out.println("Map: " + map1);

		// 2. get(Object key)
		System.out.println("Score for Java: " + map1.get("Java"));

		// 3. containsKey(Object key)
		System.out.println("Contains key 'Python'? " + map1.containsKey("Python"));

		// 4. containsValue(Object value)
		System.out.println("Contains value 80? " + map1.containsValue(80));

		// 5. putIfAbsent(K key, V value)
		map1.putIfAbsent("Java", 500); // Won't change as Java exists
		map1.putIfAbsent("Go", 70); // Will add
		System.out.println("After putIfAbsent: " + map1);

		// 6. remove(Object key)
		map1.remove("C++");
		System.out.println("Removed C++: " + map1);

		// 7. remove(Object key, Object value) - specific match
		map1.remove("Go", 100); // Fail (val is 70)
		System.out.println("After failed remove: " + map1);

		// 8. replace(K key, V value)
		map1.replace("Python", 95);
		System.out.println("Replaced Python score: " + map1);

		// 9. getOrDefault(Object key, V defaultValue)
		System.out.println("Get 'Rust' (def 0): " + map1.getOrDefault("Rust", 0));

		// 10. keySet()
		System.out.println("Keys: " + map1.keySet());

		// 11. values()
		System.out.println("Values: " + map1.values());

		// 12. entrySet()
		System.out.println("EntrySet: " + map1.entrySet());

		// 13. size()
		System.out.println("Size: " + map1.size());

		// 14. putAll(Map m)
		map1.putAll(map4);
		System.out.println("After putAll: " + map1);

		// 15. clear()
		map1.clear();
		System.out.println("After clear: " + map1);
	}
}
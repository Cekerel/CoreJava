package ChapterNine.map;

import ChapterNine.Employee;

import java.util.*;

/**
 * This program demonstrates the use of a map woth key type String and value type Employee.
 *
 * @author Cay Horstmann
 * @version 1.11 2012-01-26
 */
public class MapTest {
    public static void main(String[] args) {
       mergeTest();
    }

    private static void normalMethod() {
		Map<String, Employee> staff = new HashMap<>();
		staff.put("144-25-5464", new Employee("Amy Lee"));
		staff.put("56-24-2546", new Employee("Harry Hacker"));
		staff.put("157-62-7935", new Employee("Gary Cooper"));
		staff.put("456-62-5527", new Employee("Francesca Cruz"));

		//print all entries

		System.out.println(staff);

		//remove an entry

		staff.remove("567-24-2546");

		//replace an entry
		staff.put("456-62-5527", new Employee("Francessca Miller"));

		//look up a value
		System.out.println(staff.get("157-62-7935"));

		//iterate through all entries
		staff.forEach((k, v) -> {
			System.out.println("key = " + k + ", value = " + v);
		});
	}

    private static List<Integer> getRamdonNumberList() {
    	List<Integer> list = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			list.add((int) (Math.random() * 20));
		}
		return list;
	}

	/**
	 * This method demonstrates the use of the "merge" and the "putIfAbsent" method.
	 */
    private static void mergeTest() {
    	Map<Integer, Integer> map = new HashMap<>();
    	List<Integer> list = getRamdonNumberList();
		System.out.println(list);
		for (Integer integer:
			 list) {
//			map.putIfAbsent(integer, 0);
//			map.put(integer, map.get(integer) + 1);
			map.merge(integer, 1, Integer::sum);
		}
		Set<Map.Entry<Integer,Integer>> entries = map.entrySet();
		for (Map.Entry<Integer, Integer> entry:
			entries){
			System.out.println("\"" + entry.getKey() + "\" comes out about " + entry.getValue() + " time(s).");
		}
	}
}

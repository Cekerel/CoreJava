package ChapterThree;

import java.util.*;

/**
 * This program demonstrates the ArrayList class/
 * @version 1.11 2012-01-26
 * @author Cay HorstMann
 *
 */

public class ArrayListCase {
	public static void main(String[] args) {
		//fill the staff array list with three Employee objects
		ArrayList<Employee7>staff = new ArrayList<>();
		
		staff.add(new Employee7("Carl", 75000, 1987, 12, 15));
		staff.add(new Employee7("Harry Hacker", 50000, 1989, 10, 1));
		staff.add(new Employee7("Tony Tester", 40000, 1990, 3, 15));
		
		//raise everyone's salary by 5%
		for(Employee7 employee7:staff) {
			employee7.raiseSalary(5);
		}
		
		//print out information about all Employee objects
		for (Employee7 employee7 : staff) {
			System.out.println("name = " + employee7.getName() + ", salary = " + employee7.getSalary() + ", hireday = " + employee7.getHireDay());
		}
	}
}

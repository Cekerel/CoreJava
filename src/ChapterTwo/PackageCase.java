package ChapterTwo;

import ChapterTwo.horstmann.corejava.*;

import static java.lang.System.*;
/**
 * This program demonstrates the use of the packages
 *
 * @version 1.11 2004-02-19
 * @author Cay Horstmann
 * 
 */
public class PackageCase {

	public static void main(String[] args) {
		//because of the import statement, we don't have to use ChapterTwo.PackageCase.Employee4 here
		Employee4 harry = new Employee4("Harry Hacker", 50000, 1989, 10, 1);
		
		harry.raiseSalary(5);
		
		//because of the static import statement, we don't have to use System.out here
		out.println("name = " + harry.getName() + ", salary = " + harry.getSalary());
	}
}


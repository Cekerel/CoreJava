package ChapterFour;

import java.util.*;

/**
 * This program demonstrates the use of the Compareble interface
 * @version 1.30 2004-02-27
 * @author Cay Horstmann
 */
public class EmployeeSortTest {
	public static void main(String[] args) {
		Employee [] staff = new Employee[3];
		
		staff[0] = new Employee("Harry Hacker", 35000);
		staff[1] = new Employee("Carl Cracker", 75000);
		staff[2] = new Employee("Tony Tester", 38000);
		
		Arrays.sort(staff);
		
		//print out information about all Employee objects
		for (Employee e : staff) {
			System.out.println("name = " + e.getName() + ", salary = " + e.getSalary());
		}
	}
}


/*
 * 若使用带类型参数的Comparable接口，则在这种情况下重写的compareTo方法的参数类型可以直接定义为Comparable接口所带的参数类型
 * 若未使用带参数类型的Comparable接口，则在这种情况下重写的compareTo方法的参数类型为Object，且在该方法域内需要进行类型转换
 */
class Employee implements Comparable<Employee> {
	private String name;
	private double salary;
	
	public Employee(String name, double salary) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.salary = salary;
	}
	
	public String getName() {
		return name;
	}
	
	public double getSalary() {
		return salary;
	}
	
	public void raiseSalary(double byPercent) {
		double raise = salary * byPercent / 100;
		salary += raise;
	}
	
	/**
	 * Compare employee by salary
	 * @param other another Employee object
	 * @return a negative value of if this employee has a lower salary than
	 * otherObject, 0 if the salaries are the same, a positive value otherwise
	 */
	public int compareTo(Employee other) {
		return Double.compare(salary, other.salary);
	}
}

//class Employee implements Comparable {
//	private String name;
//	private double salary;
//	
//	public Employee(String name, double salary) {
//		// TODO Auto-generated constructor stub
//		this.name = name;
//		this.salary = salary;
//	}
//	
//	public String getName() {
//		return name;
//	}
//	
//	public double getSalary() {
//		return salary;
//	}
//	
//	public void raiseSalary(double byPercent) {
//		double raise = salary * byPercent / 100;
//		salary += raise;
//	}
//	
//	/**
//	 * Compare employee by salary
//	 * @param other another Employee object
//	 * @return a negative value of if this employee has a lower salary than
//	 * otherObject, 0 if the salaries are the same, a positive value otherwise
//	 */
//	public int compareTo(Object otherObject) {
//		Employee other = (Employee) otherObject;
//		return Double.compare(salary, other.salary);
//	}
//}



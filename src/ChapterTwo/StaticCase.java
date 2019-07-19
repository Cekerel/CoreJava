package ChapterTwo;

//import java.text.NumberFormat;
//
//public class StaticCase {
//	private static final double PI = 3.1415926535897932384626;
//	public static void main(String[] args) {
//		System.out.println(StaticCase.PI);
//		
//		NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
//		NumberFormat percentFormatter = NumberFormat.getPercentInstance();
//		double x = 0.1;
//		System.out.println(currencyFormatter.format(x));
//		System.out.println(percentFormatter.format(x));
//	}
//}


/**
 * This program demonstrates static methods
 * @author ASUS
 *
 */

public class StaticCase {
	public static void main(String[] args) {
		//fill the staff with three Employee objects
		Employee1 [] staff = new Employee1[3];
		
		staff[0] = new Employee1("Tom", 40000);
		staff[1] = new Employee1("Dick", 60000);
		staff[2] = new Employee1("Harry", 65000);
		
		//print out information about all Employee objects
		for (Employee1 employee : staff) {
			employee.setId();
			System.out.println("name = " + employee.getName() + ", id = " + employee.getId() + ", salary = " + employee.getSalary());
		}
		
		int n = Employee1.getNextId(); // calls static method
		System.out.println("Next available id = " + n);
		
	}
}

class Employee1 {
	private static int nextId = 1;
	
	private String name;
	private double salary;
	private int id;
	
	public Employee1(String n, double s) {
		// TODO Auto-generated constructor stub
		name = n;
		salary = s;
		id = 0;
	}
	
	public String getName() {
		return name;
	}
	
	public double getSalary() {
		return salary;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId() {
		id = nextId; //set id to next available field
		nextId++;
	}
	
	public static int getNextId() {
		return nextId; // returns static field
	}
	
	public static void main(String[] args) {
		Employee1 employee = new Employee1("Harry", 50000);
		System.out.println(employee.getName() + " " + employee.getSalary());
		
	}
}
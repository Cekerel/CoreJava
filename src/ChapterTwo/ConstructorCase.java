package ChapterTwo;

import java.util.*;

/**
 * This program demonstrates object construction.
 * @version 1.01 2004-02-19
 */

public class ConstructorCase {
	public static void main(String[] args) {
		// fill the staff array with three Employee3 objects
		Employee3 [] staff = new Employee3[3];
		staff[0] = new Employee3("Harry", 40000);
		staff[1] = new Employee3(60000);
		staff[2] = new Employee3();
		
		//print out information about all Employees objects
		for(Employee3 e : staff)
			System.out.println("name = " + e.getName() + ", id = " + e.getId() + ", salary = " + e.getSalary());
	}
}

class Employee3 {
	private static int nextId;
	private int id;
	private String name = ""; // instance field initialization
	private double salary;
	
	//static initialization block
	static
	{
		Random generator = new Random();
		//set nextId to a random number between 0 and 9999
		nextId = generator.nextInt(10000);
	}
	
	//object initialization block
	{
		id = nextId;
		nextId++;
	}
	
	//three overloaded constructors
	public Employee3(String n, double s) {
		// TODO Auto-generated constructor stub
		name = n;
		salary = s;
	}
	
	public Employee3(double s) {
		// calls the Employee3(String n, double s) constructor
		this("Employee #" + nextId, s);
	}
	
	//the default constructor
	public Employee3() {
		// TODO Auto-generated constructor stub
		//name initialization to "" -- see above
		//salary not explicitly set --initialized to 0
		//id initialized in initialization block
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
	
}
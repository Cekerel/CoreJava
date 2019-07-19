package ChapterThree;

import java.time.LocalDate;

public class AbstractClassesCase {
	public static void main(String[] args) {
		Person [] people = new Person[2];
		
		//fill the people array with Student and Employee objects
		people[0] = new Employee6("Harry Hacker", 50000, 1989, 10, 1);
		people[1] = new Student("Maria Morris", "computer science");
		
		//print out names and description of all Person objects
		for (Person person : people) {
			System.out.println(person.getName() + "," + person.getDescription());
		}
	}
}

abstract class Person {
	public abstract String getDescription();
	private String name;
	
	public Person(String name) {
		// TODO Auto-generated constructor stub
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}

class Employee6 extends Person {
	private double salary;
	private LocalDate hireday;
	
	public Employee6(String name, double salary, int year, int month, int day) {
		// TODO Auto-generated constructor stub
		super(name);
		this.salary = salary;
		hireday = LocalDate.of(year, month, day);
	}
	
	public double getSalary() {
		return salary;
	}
	
	public LocalDate getHireday() {
		return hireday;
	}
	
	public String getDescription() {
		return String.format("an employee with a salary of $%.2f", salary);
	}
	
	public void raiseSalary(double byPercent) {
		double raise = salary * byPercent / 100;
		salary += raise;
	}
}

class Student extends Person {
	private String major;
	
	/**
	 * 
	 * @param name the student's name
	 * @param major the student's major
	 */
	public Student(String name, String major) {
		// TODO Auto-generated constructor stub
		super(name);
		this.major = major;
	}
	
	public String getDescription() {
		return "a student majoring in" + major;
	}
}

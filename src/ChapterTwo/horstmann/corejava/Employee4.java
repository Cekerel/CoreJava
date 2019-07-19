package ChapterTwo.horstmann.corejava;

//the classes in this file are part of this package

import java.time.*;

//import statements come after the package statement 

/**
 * @see ChapterTwo.horsemann.corejava.Employee4#raiseSalary(double)
 * @author ASUS
 *
 */
public class Employee4 {
	private String name;
	private double salary;
	private LocalDate hireday;
	
	public Employee4(String name, double salary, int year, int month, int day) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.salary = salary;
		hireday = LocalDate.of(year, month, day);
	}

	public String getName() {
		return name;
	}
	
	public double getSalary() {
		return salary;
	}
	
	public LocalDate getHireday() {
		return hireday;
	}
	
	/**
	 * Raise the salary of en employee
	 * @param byPercent the percentage by which to raise the salary (e.g. 10 means 10%)
	 * @return the mount of the raise
	 */
	public void raiseSalary(double byPercent) {
		double raise = salary * byPercent / 100;
		salary += raise;
	}
}

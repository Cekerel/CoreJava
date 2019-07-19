package ChapterThree;

import java.time.LocalDate;

/**
 * This program demonstrates inheritance.
 * The source code of the corejava book is named after ManagerTest
 * @version 1.2 2004-02-21
 * @author Cay HorstMann
 *
 */
public class InheritanceCase {
	public static void main(String[] args) {
		//construct a Manager object
		Manager boss = new Manager("Carl Cracker", 80000, 1987, 12, 15);
		boss.setBonus(5000);
		
		Employee5 [] staff = new Employee5[3];
		
		//fill the staff array with Manager and Employee objects
		
		staff[0] = boss;
		staff[1] = new Employee5("Harry Hacker", 50000, 1989, 10, 1);
		staff[2] = new Employee5("Tommy Tester", 40000, 1990, 3, 15);
		
		//print out information about all Employee objects
		for (Employee5 employee5 : staff) {
			System.out.println("name = " + employee5.getName() + ", salary = " + employee5.getSalary());
		}
		
	}
}

class Employee5 {
	private String name;
	private double salary;
	private LocalDate hireday;
	
	public Employee5(String name, double salary, int year, int month, int day) {
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
	
	public void raiseSalary(double bypercent) {
		double raise = salary * bypercent / 100;
		salary += raise;
	}
}

class Manager extends Employee5 {
	
	private double bonus;
	
	/**
	 * @param name the name of the employee
	 * @param salary the salary
	 * @param year the hire year
	 * @param month the hire month
	 * @param day the hire day
	 */
	public Manager(String name, double salary, int year, int month, int day) {
		// TODO Auto-generated constructor stub
		super(name, salary, year, month, day);
		bonus = 0;
	}
	
	public double getSalary() {
		double basesalary = super.getSalary();
		return basesalary + bonus;
	}
	
	public void setBonus(double bonus) {
		this.bonus = bonus;
	}
	
}

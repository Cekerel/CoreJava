package ChapterTwo;

import java.time.*;

import javax.crypto.interfaces.PBEKey;

/**
 * 书上的范例
 * This program tests the Employee class.
 * @version 1.12 2015-05-08
 * @author ASUS
 * 
 */

public class EmployeeTest {
	public static void main(String[] args) {
		//fill the staff array with three Employee objects.
		Employee [] staff = new Employee[3];
		
		staff[0] = new Employee("Carl Craker", 75000, 1987, 12, 15);
		staff[1] = new Employee("Harry Hacker", 50000, 1989, 10, 1);
		staff[2] = new Employee("Tony Tester", 40000, 1990, 3, 15);
		
		//raise everyone's salary by 5%
		for (Employee e : staff) {
			e.raiseSalary(5);
		}
		
		//print out information about all Employee objects
		for (Employee e : staff) {
			System.out.println("name = " + e.getName() + ", salary = " + e.getSalary() + ", hireDay = " + e.getHireDay() + ".");
		}
	}
}


class Employee {
	private String name;
	private double salary;
	private LocalDate hireDay;
	
	public Employee(String n, double s, int year, int month, int day) {
		// TODO Auto-generated constructor stub
		name = n;
		salary = s;
		hireDay = LocalDate.of(year, month, day);
	}
	
	public String getName() {
		return name;
	}
	
	public double getSalary() {
		return salary;
	}
	
	/**
	 * TIPS：下列这种形式在Java中可行
	 * 
	 * public String getName() {
	 * 	return variable1 + variable2
	 * }
	 * 
	 */
	
	
	/**
	 * getHireDay()函数违反了"尽量不编写返回引用可变对象的访问器方法"的原则
	 * 这种做法不提倡，因为破坏了封装性
	 * 
	 * 如果需要返回一个可变对象的引用，应该首先对其进行clone
	 * 
	 * 故下列代码可改成
	 * public Date getHireDay() {
	 * 		return (Date) hireDay.clone();
	 * }
	 * 
	 */
	public LocalDate getHireDay() {
		return hireDay;
	}
	
	public void raiseSalary(double byPercent) {
		double raise = salary * byPercent / 100;
		salary += raise;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	//一个方法可以访问所属类的所有对象的私有数据
	public boolean equals(Employee other) {
		return name.equals(other);
	}
}
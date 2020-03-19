package ChapterSixteen;

import java.time.LocalDate;

/**
 * Employee
 */
public class Employee {
	public final static Integer RECORD_SIZE = 100;
	public final static Integer NAME_SIZE = 40;
    private String name;
    private double salary;
    private LocalDate hireday;

	public Employee() {

	}

    public Employee(String name, double salary, int year, int month, int day) {
        this.name = name;
        this.salary = salary;
        this.hireday = LocalDate.of(year, month, day);
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


	@Override
	public String toString() {
		return "{" +
			"name='" + getName() + "'" +
			", salary='" + getSalary() + "'" +
			", hireday='" + getHireday() + "'" +
			"}";
	}


}
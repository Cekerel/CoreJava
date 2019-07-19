package ChapterThree;


import java.time.LocalDate;
import java.util.Objects;

public class EqualsCase {
	public static void main(String[] args) {
		Employee7 alice1 = new Employee7("Alice Adams", 75000, 1987, 12, 15);
		Employee7 alice2 = alice1;
		Employee7 alice3 = new Employee7("Alice Adams", 75000, 1987, 12, 15);
		Employee7 bob = new Employee7("Bob Brandson", 50000, 1989, 10, 1);
		
		System.out.println("alice1 == alice2:" + (alice1 == alice2));
		
		System.out.println("alice1 == alice3:" + (alice1 == alice3));
		
		System.out.println("alice1.equals(alice3):" + alice1.equals(alice3));
		
		System.out.println("alice1.equals(bob):" + alice1.equals(bob));
		
		System.out.println("bob.toString():" + bob);
		
		Manager1 carl = new Manager1("Carl Craker", 80000, 1987, 12, 15);
		Manager1 boss = new Manager1("Carl Cracker", 80000, 1987, 12, 15);
		boss.setBonus(5000);
		System.out.println("boss.toString:" + boss);
		System.out.println("carl.equals(boss):" + carl.equals(boss));
		System.out.println("alice1.hashCode():" + alice1.hashCode());
		System.out.println("alice3.hashCode():" + alice3.hashCode());
		System.out.println("bob.hashCode():" + bob.hashCode());
		System.out.println("carl.hashCode():" + carl.hashCode());
		
	}
}

class Employee7 {
	private String name;
	private double salary;
	private LocalDate hireday;
	
	public Employee7(String name, double salary, int year, int month, int day) {
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
	
	public LocalDate getHireDay() {
		return hireday;
	}
	
	public void raiseSalary(double byPercent) {
		double raise = salary * byPercent /100;
		salary += raise;
	}
	
	public boolean equals(Object otherObject) {
		//a quick test to see if the objects are identical
		if (this == otherObject) {
			return true;
		}
		
		//must return false if the explicit parameter id null
		if (otherObject == null) {
			return false;
		}
		
		//if the classes don't match, they can't be equal.
		if (getClass() != otherObject.getClass()) {
			return false;
		}
		
		//new we know otherObject is a non-null Employee
		Employee7 other = (Employee7) otherObject;
		
		//test whether the fields have identical values
		return Objects.equals(name, other.name) && salary == other.salary && Objects.equals(hireday, other.hireday);
	}
	
	public int hashCode() {
		return Objects.hash(name, salary, hireday);
	}
	
	public String toString() {
		return getClass().getName() + "[name=" + name + ",salary=" + salary + "hireday=" + hireday + "]";
	}
}

class Manager1 extends Employee7 {
	private double bonus;
	
	public Manager1(String name, double salary, int year, int month, int day) {
		// TODO Auto-generated constructor stub
		super(name, salary, year, month, day);
	}
	
	public double getSalary() {
		double baseSalary = super.getSalary();
		return bonus + baseSalary;
	}
	
	public void setBonus(double bonus) {
		this.bonus = bonus;
	}
	
	public boolean equals(Object otherObject) {
		if(!super.equals(otherObject))
			return false;
		Manager1 other = (Manager1) otherObject;
		//super.equals checked that this and other belong to the same class
		return bonus == other.bonus;
	}
	
	public int hashCode() {
		return super.hashCode() + 17 * new Double(bonus).hashCode();
	}
	
	public String toString() {
		return super.toString() + "[bonus" + bonus +"]";
	}
}
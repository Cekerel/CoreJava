package ChapterTwo;

public class ParamCase {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/**
		 * test1 : Methods can't modify numeric parameters
		 */
		System.out.println("Testing tripleValue:");
		double percent = 10;
		System.out.println("Before: percent = " + percent);
		tripleValue(percent);
		System.out.println("After: percent = " + percent);
		
		/**
		 * Test 2: Methods can change the state of objects parameters
		 */
		System.out.println("\nTesting tripleSalary:");
		Employee2 harry = new Employee2("Harry", 50000);
		System.out.println("Before: salary = " + harry.getSalary());
		tripleSalary(harry);
		System.out.println("After: salary = " + harry.getSalary());
		
		/**
		 * Test 3: Methods can't attach new objects to objects parameters
		 */
		
		/**
		 * The reason why the third method fail to attach new objects parameters is when you change the value
		 * in the copy of the address instead of the the address of the copy in the function, the change in 
		 * the function will affect the incoming parameter. On the contrary, if you change the copy of the 
		 * address in the function, such as a new one, then the copy points to a new address, then the parameter
		 * passed in or point to the original address, it will not change the value of the parameter.
		 */
		
		System.out.println("\nTesting swap:");
		Employee2 a = new Employee2("Alice", 70000);
		Employee2 b = new Employee2("Bob", 60000);
		System.out.println("Before: a = " + a.getName());
		System.out.println("Before: b = " + b.getName());
		swap(a, b);
		System.out.println("After: a = " + a.getName());
		System.out.println("After: b = " + b.getName());
	}
	
	public static void tripleValue(double x) {
		x *= 3;
		System.out.println("End of method: x = " + x);
	}
	
	public static void tripleSalary(Employee2 employee) {
		employee.raiseSalary(200);
		System.out.println("End of the method: salary = " + employee.getSalary());
	}
	
	public static void swap(Employee2 x, Employee2 y) {
		Employee2 temp = x;
		x = y;
		y = temp;
		System.out.println("End of the method: x = " + x.getName());
		System.out.println("End of the method: y = " + y.getName());
	}

}

class Employee2 {
	private String name;
	private double salary;
	
	public Employee2(String n, double s) {
		// TODO Auto-generated constructor stub
		name = n;
		salary = s;
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
}
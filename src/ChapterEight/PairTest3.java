package ChapterEight;

import java.time.LocalDate;


class PairAlg {
	public static boolean hasNulls(Pair<?> p) {
		return p.getFirst() == null || p.getSecond() == null;
	}

	public static void swap(Pair<?> p) {
		swapHelper(p);
	}

	public static <T> void swapHelper(Pair<T> p) {
		T t = p.getFirst();
		p.setFirst(p.getSecond());
		p.setSecond(t);
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
	 * public String getName() { return variable1 + variable2 }
	 * 
	 */

	/**
	 * getHireDay()函数违反了"尽量不编写返回引用可变对象的访问器方法"的原则 这种做法不提倡，因为破坏了封装性
	 * 
	 * 如果需要返回一个可变对象的引用，应该首先对其进行clone
	 * 
	 * 故下列代码可改成 public Date getHireDay() { return (Date) hireDay.clone(); }
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

	// 一个方法可以访问所属类的所有对象的私有数据
	public boolean equals(Employee other) {
		return name.equals(other);
	}
}

class Manager extends Employee {
	private int bonus;

	public Manager(String n, double s, int year, int month, int day) {
		super(n, s, year, month, day);
		// TODO Auto-generated constructor stub
	}

	public Manager(String n, double s, int year, int month, int day, int bonus) {
		super(n, s, year, month, day);
		this.bonus = bonus;
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the bonus
	 */
	public int getBonus() {
		return bonus;
	}

	/**
	 * @param bonus the bonus to set
	 */
	public void setBonus(int bonus) {
		this.bonus = bonus;
	}

}

public class PairTest3 {
	public static void main(String[] args) {
		Manager ceo = new Manager("Gus Greedy", 800000, 2003, 12, 15);
		Manager cfo = new Manager("Sid Sneaky", 600000, 2003, 12, 15);
		ceo.setBonus(1000000);
		cfo.setBonus(500000);
		Manager[] managers = { ceo, cfo };
		Pair<Employee> result = new Pair<Employee>();
		minmaxBonus(managers, result);
		System.out.println("First: " + result.getFirst().getName() + ", second: " + result.getSecond().getName());
		maxminBonus(managers, result);
		System.out.println("First: " + result.getFirst().getName() + ", second: " + result.getSecond().getName());
	}

	public static void printBuddies(Pair<? extends Employee> p) {
		Employee first = p.getFirst();
		Employee second = p.getSecond();
		System.out.println(first.getName() + " and " + second.getName() + " are buddies.");
	}

	public static void minmaxBonus(Manager[] a, Pair<? super Manager> result) {
		if (a.length == 0) {
			return;
		}
		Manager min = a[0];
		Manager max = a[0];
		for (int i = 0; i < a.length; i++) {
			if (min.getBonus() > a[i].getBonus()) {
				min = a[i];
			}
			if (max.getBonus() < a[i].getBonus()) {
				max = a[i];
			}
		}
		result.setFirst(min);
		result.setSecond(max);
	}

	public static void maxminBonus(Manager[] a, Pair<? super Manager> result) {
		minmaxBonus(a, result);
		PairAlg.swapHelper(result); // OK--swapHelper captures wildcard type
	}
}

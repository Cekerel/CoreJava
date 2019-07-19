package ChapterThree;

class One {
	public One() {
		// TODO Auto-generated constructor stub
	}
	public void print() {
		System.out.println("One");
	}
}


class Two extends One {
	public Two() {
		// TODO Auto-generated constructor stub
		super();
	}
	@Override
	public void print() {
		System.out.println("Two");
	}
	public void printPlus() {
		System.out.println("Two again");
	}
}

public class ForcedTypeConversion {
	public static void main(String[] args) {
		One one = new Two();
		Two oneToTwo = (Two) one;
		oneToTwo.printPlus();
		
		One [] ones = new One[3];
		ones[1] = new One();
		ones[1].print();
		ones[0] = new Two();
		Two oneToTwo3 = (Two) ones[0];
		oneToTwo3.printPlus();
		try {
			One one2 = new One();
			Two oneToTwo2 = (Two) one2;
			oneToTwo2.printPlus();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
		}
		
	}
}

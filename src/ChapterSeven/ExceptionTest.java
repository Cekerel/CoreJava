package ChapterSeven;

import java.util.Scanner;

class NumberLessThanZeroException extends Exception {
	public NumberLessThanZeroException() {}
	
	public NumberLessThanZeroException(String string) {
		super(string);
	}
}

class PackAnotherException extends Exception {
	public PackAnotherException() {
		
	}
	
	public PackAnotherException(String message) {
		super(message);
	}
}

public class ExceptionTest {
	public static void main(String[] args) {
//		try (Scanner scanner = new Scanner(System.in)) {
//			while (scanner.hasNext()) {
//				System.out.println(scanner.next());
//			}
//		}
		
	}
	
	public static void throwAnException(int number) throws NumberLessThanZeroException {
		if (number < 0) {
			throw new NumberLessThanZeroException();
		}
	}
	
	public static void packException() throws PackAnotherException {
		try {
			throwAnException(-1);
			System.out.println("Hello World");
		} catch (NumberLessThanZeroException e) {
			// TODO: handle exception
			Throwable anotherException = new PackAnotherException("Pack another exception");
			anotherException.initCause(e);
			throw (PackAnotherException)anotherException;
		}
	}
	
	public static void print() throws NullPointerException {
		String string = null;
		System.out.println(string);
	}
}

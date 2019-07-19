package ChapterSeven;

import java.util.Scanner;

/**
 * A program that displays a trace of feature of a recursive method call.
 * @version 1.01 2004-05-10
 * @author Cay Horstmann
 *
 */
public class StackTraceTest {
	
	public static int factorial(int n) {
		System.out.print("Factorial(" + n + "): ");
		Throwable throwable = new Throwable();
		StackTraceElement [] framElements = throwable.getStackTrace();
		for (StackTraceElement stackTraceElement : framElements) {
			System.out.println(stackTraceElement);
		}
		int r;
		if (n <= 1)
			r = 1;
		else
			r = n * factorial(n - 1);
		System.out.println("return " + r);
		return r;
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Enter n: ");
		int n = in.nextInt();
		factorial(n);
	}
}

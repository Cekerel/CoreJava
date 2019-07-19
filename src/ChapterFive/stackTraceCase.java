package ChapterFive;

import java.util.*;

/**
 * A program that display a trace feature of a recursive method call.
 * @version 1.01 2004-05-10
 * @author Cay Horstmann
 */
public class stackTraceCase {

	public static int factorial(int n) {
		System.out.println("Factorial(" + n + "):");
		Throwable throwable = new Throwable();
//		StackTraceElement [] frames = throwable.getStackTrace();
//		for (StackTraceElement stackTraceElement : frames) {
//			System.out.println(stackTraceElement);
//		}
		throwable.printStackTrace();
		int r;
		if (n <= 1) {
			r = 1;
		}
		else
			r = n * factorial(n - 1);
		System.out.println("return " + r);
		return r;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		System.out.print("Enter n: ");
		int i = in.nextInt();
		factorial(i);
	}

}

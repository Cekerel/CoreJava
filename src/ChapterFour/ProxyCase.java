package ChapterFour;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Random;

/**
 * This program demonstrates the use of the proxies.
 * @version 1.00 2000-04-13
 * @author Cay Horstmann
 */

public class ProxyCase {
	public static void main(String[] args) {
		Object [] elements = new Object[1000];
		for (int i = 0; i < elements.length; i++) {
			Integer value = i + 1;
			InvocationHandler handler = new TraceHandler(value);
			Object proxy = Proxy.newProxyInstance(null, new Class[] {Comparable.class}, handler);
			elements[i] = proxy;
		}
		
		Integer key = new Random().nextInt(elements.length) + 1;
		int result = Arrays.binarySearch(elements, key);
		
		if (result >= 0) {
			System.out.println(elements[result]);
		}
	}
}

class TraceHandler implements InvocationHandler {
	private Object target;
	
	public TraceHandler(Object target) {
		// TODO Auto-generated constructor stub
		this.target = target;
	}
	
	public Object invoke(Object proxy, Method m, Object [] args) throws Throwable {
		System.out.print(target);
		System.out.print("." + m.getName() + "(");
		if (args != null) {
			for (int i = 0; i < args.length; i++) {
				System.out.print(args[i]);
				if (i < args.length - 1) {
					System.out.print(",");
				}
			}
		}
		System.out.println(")");
		
		return m.invoke(target, args);
	}
}

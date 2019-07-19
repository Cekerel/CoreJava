package ChapterThree;

import java.util.ArrayList;

public class WrapperCase {
	public static void main(String[] args) {
		Integer a = 1000;
		Integer b = 1000;
		System.out.println("a == b:" + (a == b));
		
		try {
			Integer c = null;
			System.out.println(2 * c);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
		}
		
		try {
			String aString = new String("1.89");
			int a1 = Integer.parseInt(aString);
			System.out.println(a1);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
		}
		
		Integer d = Integer.valueOf("20");
		System.out.println(d);
		
	}
}

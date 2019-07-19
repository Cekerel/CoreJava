package ChapterThree;
import java.util.*;


/**
 * This program demonstrates enumerated types.
 * @version 1.0 2004-05-24
 * @author Cay HorstMann
 *
 */
public class EnumCase {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Enter a size:(SMALL, MEDIUM, LARGE, EXTRA_LARGE)");
		String input = in.next().toUpperCase();
		Size size = Enum.valueOf(Size.class, input);
		System.out.println("size = " + size);
		System.out.println("Abbreviation = " + size.getAbbreviation());
		if (size == Size.EXTRA_LARGE) {
			System.out.println("Good job -- you paid attention to the _.");
		}
		
		//int ordinal()返回枚举常量在enum生命中的位置，位置从0开始计数
		System.out.println(Size.LARGE.ordinal());
		
		//compareTo(E other)如果枚举常量出现在other之前，则返回一个负值，如果other==this，则返回0.否则返回正值
		System.out.println(Size.EXTRA_LARGE.compareTo(size));
	}
}

enum Size {
	SMALL("S"), MEDIUM("M"), LARGE("L"),EXTRA_LARGE("XL");
	private String abbreviation; //实例域
	private Size(String abbreviation) { //构造器
		this.abbreviation = abbreviation;
	}
	public String getAbbreviation() { //该类的方法域
		return abbreviation;
	}
}

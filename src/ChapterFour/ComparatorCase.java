package ChapterFour;

import java.util.Arrays;


/**
 * 倘若要自己编写某个与库中重名且用法一致的interface，则必须extends库中已存在的interface
 * @author ASUS
 *
 */
interface Comparator extends java.util.Comparator<String> {
	int compare(String first, String second);
}

class LengthComparator implements ChapterFour.Comparator {
	@Override
	public int compare(String first, String second) {
		return first.length() - second.length();
	}
}

public class ComparatorCase {
	public static void main(String[] args) {
		String [] strings = new String[] {
				"fhjkldshalf",
				"fhdska",
				"fhdsfhdksjafklds",
				"fhdskjaflhdkslfdhsaklfjhd",
				"hgiuewohqiuh"
		};
		
		
		/**
		 * 倘若需要将字符串数组中的某两个字符串长度作比较，则需要用比较器对象进行compare()方法调用，而不是在
		 * 字符串本身上调用
		 */
		Comparator comparator = new LengthComparator();
		for (int i = 0; i < strings.length - 1; i++) {
			System.out.println(comparator.compare(strings[i], strings[i + 1]));
		}
		
		Arrays.sort(strings, new LengthComparator());
		for (String string : strings) {
			System.out.println(string);
		}
		
		
	}
}

package ChapterSix;

import ChapterSix.ArrayAlgOne.Pair;

/**
 * @verison 1.07 2012-01-26
 * @author Cay Horstmann
 *
 */
public class PairCase {
	public static void main(String[] args) {
		
	}
}

class ArrayAlgOne {
	public static class Pair<String> {
		public Pair(String first, String second) {
			// TODO Auto-generated constructor stub
		}
	}
	
	
	public static Pair<String> minmax(String [] a) {
		if(a == null || a.length == 0)
			return null;
		String min = a[0];
		String max = a[0];
		for (int i = 0; i < a.length; i++) {
			if (min.compareTo(a[i]) > 0) {
				min = a[i];
			} else if (max.compareTo(a[0]) < 0) {
				max = a[i];
			}
		}
		return new Pair(min,max);
	}
}

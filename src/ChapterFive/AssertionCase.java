package ChapterFive;

public class AssertionCase {
	public static void main(String[] args) {
		int a = -3;
		int b = -5;
		test1(a);
		test2(b);
	}
	
	public static void test1(int n) {
		try {
			assert n >= 0 : "Something goes wrong here, n is not less than 0";
		} catch (AssertionError e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		System.out.println(n);
	}
	
	public static void test2(int n) {
		assert n == 0;
		System.out.println(n);
	}
}

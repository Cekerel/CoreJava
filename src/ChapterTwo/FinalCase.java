package ChapterTwo;

public class FinalCase {
	
	/**
	 * 可以将实例域定义为final，但这样的实例域必须初始化，也就是说，必须确保在每个构造器执
	 * 行之后这个域的值被设置，并且在后面的操作中，不能够在对它进行修改
	 * 
	 * final关键字只是表示存储在被final修饰的某变量中的对象引用不会再指示其他对象，但是被final
	 * 修饰的这个对象可以更改
	 */
	private final StringBuilder stringBuilder = new StringBuilder();
	
	public FinalCase(String str) {
		// TODO Auto-generated constructor stub
		stringBuilder.append(str);
	}
	
	public void print() {
		System.out.println(stringBuilder);
	}
	
	public static void main(String[] args) {
		FinalCase finalCase = new FinalCase("hello java");
		finalCase.print();
	}
}

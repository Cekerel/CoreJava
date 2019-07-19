package ChapterOne;

import java.util.Arrays;
import java.util.Scanner;
public class StringCase {
	public static void main(String[] args){
		String name = new String("Yuanhuiqian!");
		System.out.print(String.format("I Love %s", name));
		System.out.printf("\nI Love %s\n",name);
		String name2 = new String("Java!");
		System.out.println(name.join(name, "I love you!"));
		System.out.println(name.substring(1, 5));
		StringBuilder builder = new StringBuilder();
		builder.append("I love ");
		String completedString = builder.toString();
		System.out.println(completedString);
		Scanner input = new Scanner(System.in);
		StringBuilder builder1 = new StringBuilder();
		String str = input.nextLine();
		builder1.append(str);
		String completedString1 = builder1.toString();
		System.out.println(completedString1);
		
		
		
		int [][] a = 
		{
			{1,2,3,4},
			{5,6,7,8},
			{9,10,11,12},
			{13,14,15,16}
		};
		System.out.println(Arrays.deepToString(a));
		
	}
	
}

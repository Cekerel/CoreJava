package ChapterOne;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Scanner;
public class ScannerCase {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		int number = input.nextInt();	
		System.out.println(input.hasNext());
		Scanner in = new Scanner(Paths.get("E:\\Java\\ExperimentOfConsoleTheory\\src\\ExperimentOne\\test.txt"),"UTF-8");
		String string = in.toString();
		System.out.println(string);
		PrintWriter printwriter = new PrintWriter("E:\\Java\\ExperimentOfConsoleTheory\\src\\ExperimentOne\\mytext,txt");
		
	}

}

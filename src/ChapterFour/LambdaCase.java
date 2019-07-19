package ChapterFour;

import java.util.*;

import javax.swing.JOptionPane;
import javax.swing.Timer;
import java.awt.*;

public class LambdaCase {
	public static void main(String[] args) {
		
		String [] strings = new String[] {
			"Mercury",
			"Venus",
			"Earth",
			"Mars",
			"Jupiter",
			"Saturn",
			"Uranus",
			"Nepture"
		};
		
		Arrays.sort(strings, (first, second) -> {
			return first.length() - second.length();
		});
		
//		Arrays.sort(strings, (first, second) -> first.length() - second.length());
		
		for (String string : strings) {
			System.out.println(string);
		}
		
		
		Timer timer = new Timer(1000, event -> System.out.println("At the tone, the time is" + new Date()));
		timer.start();
		
		JOptionPane.showMessageDialog(null, "Quit program?");
		System.exit(0);
	}
}

package ChapterFour;

/**
 * @version 1.01 2015-05-12
 * @author Cay Horstmann
 *
 */

import java.util.*;

import javax.swing.JOptionPane;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerTesterCase {
	public static void main(String[] args) {
		ActionListener listener = new TimePrinter();
		//construct a timer that calls the listener
		
		Timer timer = new Timer(1000, listener);
		timer.start();
		
		JOptionPane.showMessageDialog(null, "Quit program?");
		System.exit(0);
	}
}

class TimePrinter implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent event) {
		System.out.println("At the tone, the time is" + new Date());
	}
}

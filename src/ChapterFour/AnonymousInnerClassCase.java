package ChapterFour;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.tools.Tool;


/**
 * This program demonstrates anonymous inner classes.
 * @version 1.11 2015-05-12
 * @author Cay Horstmann
 *
 */
public class AnonymousInnerClassCase {
	public static void main(String[] args) {
		TalkingClockForAnonymous clock = new TalkingClockForAnonymous();
		clock.start(1000, true);
		JOptionPane.showMessageDialog(null, "Quit program?");
		System.exit(0);
	}
}

class TalkingClockForAnonymous {
	
	public void start(int interval, boolean beep) {
		ActionListener listener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("At the tone, the time is " + new Date());
				if (beep) {
					Toolkit.getDefaultToolkit().beep();
				}
			}
		};
		
		Timer timer = new Timer(interval, listener);
		timer.start();
	}
}

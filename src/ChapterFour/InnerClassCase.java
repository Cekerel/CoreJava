package ChapterFour;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 * This program demonstrates the use of the inner class
 * @author Cay Horstmann
 * @version 1.11 2015-05-12
 *
 */
public class InnerClassCase {
	public static void main(String[] args) {
		TalkingClock clock = new TalkingClock(1000, true);
		clock.strat();
		JOptionPane.showMessageDialog(null, "Quit program?");
		System.exit(0);
	}
}

class TalkingClock {
	private int interval;
	private boolean beep;
	
	/**
	 * Construct a talking clock
	 * @param interval the interval between messages(in milliseconds)
	 * @param beep true if the clock should beep
	 */
	public TalkingClock(int interval, boolean beep) {
		// TODO Auto-generated constructor stub
		this.beep = beep;
		this.interval = interval;
	}
	
	
	
	public void strat() {
		ActionListener listener = new TimePrinter();
		Timer timer = new Timer(interval, listener);
		timer.start();
	}
	
	
	/**
	 * 内部类可以用public修饰
	 * 
	 * TimePrinter类位于TalkingClock类内部，这并不意味着每个TalkingClock都有一个TimePrinter实例域
	 * 如start()方法所示，TimePrinter对象是由TalkingClock类的方法构造的
	 * @author ASUS
	 *
	 */
	class TimePrinter implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			System.out.println("At the tone, the time is " + new Date());
			if (beep) {
				Toolkit.getDefaultToolkit().beep();
			}
		}
	}
}

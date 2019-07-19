package ChapterTwelve;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BounceThreadCase {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JFrame jFrame = new BounceFrame1();
			jFrame.setTitle("BounceThread");
			jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			jFrame.setVisible(true);
		});
	}
}

class BounceFrame1 extends JFrame {
	private BallComponent comp;
	public static final long STEPS = 1000000000;
	public static final int DELAY = 5;
	
	/**
	 * Constructs the frame with the component for showing the bouncing ball and
	 * Start and close buttons
	 */
	public BounceFrame1() {
		// TODO Auto-generated constructor stub
		comp = new BallComponent();
		add(comp, BorderLayout.CENTER);
		JPanel buttonPanel = new JPanel();
		addButton(buttonPanel, "Start", event -> addBall());
		addButton(buttonPanel, "Close", event -> System.exit(0));
		add(buttonPanel, BorderLayout.SOUTH);
		pack();
	}
	
	public void addButton(Container c, String title, ActionListener listener) {
		JButton button = new JButton(title);
		c .add(button);
		button.addActionListener(listener);
	}
	
	public void addBall() {
		Ball ball = new Ball();
		comp.add(ball);
		Runnable r = () -> {
			try {
				for(int i = 1; i <= STEPS; i++) {
					ball.move(comp.getBounds());
					comp.repaint();
					Thread.sleep(DELAY);
				}
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		};
		Thread t = new Thread(r);
		t.start();
	}
}
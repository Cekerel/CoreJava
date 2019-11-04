package ChapterFourteen.bounce;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.*;

/**
 * Shows an animated bouncing ball
 * @version 1.34 2015-06-21
 * @author Cay Horstmann
 *
 */

public class BounceCase {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(() -> {
			JFrame jFrame = new BounceFrame();
			jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			jFrame.setVisible(true);
		});
	}
}

/**
 * The frame with ball component and buttons.
 */
class BounceFrame extends JFrame {
	private BallComponent comp;
	public static final int STEPS = 1000;
	public static final int DELAY = 3;
	
	public BounceFrame() {
		// TODO Auto-generated constructor stub
		setTitle("Bounce");
		comp = new BallComponent();
		add(comp, BorderLayout.CENTER);
		JPanel buttonPanel = new JPanel();
		addButton(buttonPanel, "Start", event -> addBall());
		addButton(buttonPanel, "Close", event -> System.exit(0));
		add(buttonPanel, BorderLayout.SOUTH);
		pack(); 
	}
	/**
	 * Adds a button to a container.
	 * @param c the container
	 * @param title the button title
	 * @param listener the action listener for the button
	 */
	public void addButton(Container c, String title, ActionListener listener) {
		JButton jButton = new JButton(title);
		c.add(jButton);
		jButton.addActionListener(listener);
	}
	
	public void addBall() {
		try {
			Ball ball = new Ball();
			comp.add(ball);
			for(int i = 1; i <= STEPS; i++) {
				ball.move(comp.getBounds());
				comp.paint(comp.getGraphics());
				Thread.sleep(DELAY);
			}
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
	}
}


class Ball {
	private static final int XSIZE = 15;
	private static final int YSIZE = 15;
	private double x = 0;
	private double y = 0;
	private double dx = 1;
	private double dy = 1;
	
	/**
	 * Moves the ball to the next position, reversing direction if it hits one of the edges
	 */
	public void move(Rectangle2D bounds) {
		x += dx;
		y += dy;
		if (x < bounds.getMinX()) {
			x = bounds.getMinX();
			dx = -dx;
		}
		if (x + XSIZE >= bounds.getMaxX()) {
			x = bounds.getMaxX() - XSIZE;
			dx = -dx;
		}
		if (y < bounds.getMinY()) {
			y = bounds.getMinY();
			dy = -dy;
		}
		if (y + YSIZE >= bounds.getMaxY()) {
			y = bounds.getMaxY() - YSIZE;
			dy = -dy;
		}
	}
	
	/**
	 * Gets the shape of the ball at its current position.
	 */
	public Ellipse2D getShape() {
		return new Ellipse2D.Double(x, y, XSIZE, YSIZE);
	}
}

class BallComponent extends JPanel {
	private static final int DEFAULT_WIDTH = 450;
	private static final int DEFAULT_HEIGHT = 450;
	
	private java.util.List<Ball> balls = new ArrayList<>();
	
	/**
	 * Add a ball to the component
	 * @param b the ball to add
	 */
	public void add(Ball b) {
		balls.add(b);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		for (Ball b : balls) {
			g2.fill(b.getShape());
		}
	}
	
	public Dimension getPerferredSize() {
		return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
}
package ChapterFive;


import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.logging.*;
import javax.swing.*;

/**
 * A modification of the image viewer program that logs various events.
 * @version 1.03 2015-08-20
 * @author Cay Horstmann
 */
public class LoggingImageViewerCase {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (System.getProperty("java.util.logging.config.class") == null
				&& System.getProperty("java.util.logging.config.file") == null) {
			try {
				Logger.getLogger("com.horstmann.corejava").setLevel(Level.ALL);
				final int LOG_ROTATION_COUNT = 10;
				Handler handler = new FileHandler("%h/LoggingImageViewer.log", 0, LOG_ROTATION_COUNT);
				Logger.getLogger("com.horstmann.corejava").addHandler(handler);
			} catch (IOException e) {
				// TODO: handle exception
				Logger.getLogger("com.horstmann.corejava").log(Level.SEVERE, "Can't create log file handler", e);
			}
		}
		EventQueue.invokeLater(() -> {
			Handler windowHandler = new WindowHandler();
			windowHandler.setLevel(Level.ALL);
			Logger.getLogger("com.horstmann.corejava").addHandler(windowHandler);
			
			JFrame frame = new ImageViewweFrame();
			frame.setTitle("loggingImageViewer");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			Logger.getLogger("com.horstmann.corejava").fine("Showing frame");
			frame.setVisible(true);
		});
	}

}
/**
 * The frame thst shows the image.
 */
class ImageViewweFrame extends JFrame {
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGTH = 400;
	
	private JLabel label;
	private static Logger logger = Logger.getLogger("com.horstmann.corejava");
	
	public ImageViewweFrame() {
		// TODO Auto-generated constructor stub
		logger.entering("imageViewerFrame", "<init>");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGTH);
		
		//set up menu bar 
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("File");
		menuBar.add(menu);
		
		JMenuItem openItem = new JMenuItem("open");
		menu.add(openItem);
		openItem.addActionListener(new FileOpenListener());
		
		JMenuItem exitItem = new JMenuItem("Exit");
		menu.add(exitItem);
		exitItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				logger.fine("Exiting...");
				System.exit(0);
			}
		});
		
		//use a label to display the images
		label  = new JLabel();
		add(label);
		logger.exiting("ImageViewerFrame", "<init>");
	}
	
	private class FileOpenListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			logger.entering("ImageViewerFrame.FileOpenListener", "actionPerformed", event);
			
			//set up file ending with .gif
			JFileChooser chooser = new JFileChooser();
			chooser.setCurrentDirectory(new File("."));
			
			//accept all files ending with .gif
			chooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
				public boolean accept(File file) {
					return file.getName().toLowerCase().endsWith(".gif") || file.isDirectory();
				}
				
				public String getDescription() {
					return "GIF Images";
				}
			});

			//show file chooser dialog
			int r = chooser.showOpenDialog(ImageViewweFrame.this);
			
			//if the image file accepetd.Set is as icon of the label
			if (r == JFileChooser.APPROVE_OPTION) {
				String name = chooser.getSelectedFile().getPath();
				logger.log(Level.FINE, "Reading file{0}", name);
				label.setIcon(new ImageIcon(name));
			} else {
				logger.fine("File open dialog canceled");
			}

			logger.exiting("ImageViewerFrame.FileOpenListener", "actionPerformed");
		}
	}
	
}

class WindowHandler extends StreamHandler {
	private JFrame frame;
	
	public WindowHandler() {
		// TODO Auto-generated constructor stub
		frame = new JFrame();
		final JTextArea output = new JTextArea();
		output.setEditable(false);
		frame.setSize(200, 200);
		frame.add(new JScrollPane(output));
		frame.setFocusableWindowState(false);
		frame.setVisible(true);
		setOutputStream(new OutputStream() {
			
			@Override
			public void write(int b) throws IOException {
				// TODO Auto-generated method stub
				
			}
			
			public void write(byte [] b, int off, int len) {
				output.append(new String(b, off, len));
			}
		});
	}
	
	public void publisj(LogRecord record) {
		if (!frame.isVisible()) {
			return;
		}
		super.publish(record);
		flush();
	}
}
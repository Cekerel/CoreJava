package ChapterSeven;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.logging.*;

public class LoggingImageViewer {
    public static void main(String[] args) {
        if (System.getProperty("java.util.logging.config.class") == null && System.getProperty("java.util.logging.config.file") == null) {
            try {
                Logger.getLogger("com.horstmann.corejava").setLevel(Level.ALL);
                final int LOG_ROTATION_COUNT = 10;
                Handler handler = new FileHandler("%h/LoggingImageViewer.log", 0, LOG_ROTATION_COUNT);
                Logger.getLogger("com.horstmann.corejava").addHandler(handler);
            } catch (IOException e) {
                Logger.getLogger("com.horstmann.corejava").log(Level.SEVERE, "Can't create log file handler", e);
            }
        }
        EventQueue.invokeLater(() -> {
            Handler windowHandler = new WindowHandler();
            windowHandler.setLevel(Level.ALL);
            Logger.getLogger("com.horstmann.corejava").addHandler(windowHandler);

            JFrame jFrame = new ImageViewerFrame();
            jFrame.setTitle("LoggingImageViewer");
            jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            Logger.getLogger("com.horstmann.corejava").fine("Showing frame");
            jFrame.setVisible(true);

        });

    }
}

class WindowHandler extends StreamHandler {
    private JFrame jFrame;
    public WindowHandler() {
        jFrame = new JFrame();
        final JTextArea output = new JTextArea();
        output.setEditable(false);
        jFrame.setSize(200,200);
        jFrame.add(new JScrollPane());
        jFrame.setFocusableWindowState(false);
        jFrame.setVisible(true);
        setOutputStream(new OutputStream() {
            @Override
            public void write(int b) throws IOException {

            }

            @Override
            public void write(byte[] b, int off, int len) throws IOException {
                super.write(b, off, len);
            }
        });
    }

    public void publish(LogRecord record) {
        if (!jFrame.isVisible())
            return;
        super.publish(record);
        flush();
    }
}

class ImageViewerFrame extends JFrame {
    private static final int DEFALUT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 400;
    private JLabel label;
    private static Logger logger = Logger.getLogger("com.horstmann.corejava");
    public ImageViewerFrame() {
        logger.entering("ImageViewerFrame", "<init>");
        setSize(DEFALUT_WIDTH,DEFAULT_HEIGHT);
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu menu = new JMenu("File");
        menuBar.add(menu);
        JMenuItem openItem = new JMenuItem("Open");
        menu.add(openItem);
        openItem.addActionListener(new FileOpenListener());

        JMenuItem exitItem = new JMenuItem("Exit");
        menu.add(exitItem);
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logger.fine("Exiting.");
                System.exit(0);
            }
        });

        label = new JLabel();
        add(label);
        logger.exiting("ImageViewerFrame", "<init>");
    }
    private class FileOpenListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            logger.entering("ImagerViewerFrame.FileOpenListener", "actionPerformed", e);

            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File("."));
            chooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
                @Override
                public boolean accept(File f) {
                    return f.getName().toLowerCase().endsWith(".gif") || f.isDirectory();
                }
                public String getDescription() {
                    return "GIF Images";
                }

            });
            int r = chooser.showOpenDialog(ImageViewerFrame.this);
            if (r == JFileChooser.APPROVE_OPTION) {
                String name = chooser.getSelectedFile().getPath();
                logger.log(Level.FINE, "Reading file {0}", name);
                label.setIcon(new ImageIcon(name));
            } else {
                logger.fine("File open dialog canceled.");
                logger.exiting("ImageViewerFram.FileOpenListener", "actionPerformed");
            }
        }
    }

}

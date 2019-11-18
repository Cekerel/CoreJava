package ChapterFourteen.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

/**
 * EncodingTransfer
 */
public class EncodingTransfer {

    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            System.out.println("Enter base directory (e.g. /usr/local/jdk5.0/src): ");
            String directory = in.nextLine();
            System.out.println("Enter the encoding you wanna transfer from (e.g. UTF-8)");
            String from = in.nextLine();
            System.out.println("Enter the encoding you wannna transfer to (e.g. GB2312): ");
            String to = in.nextLine();
            Transfer transfer = new Transfer(new File(directory), from, to);
            Thread t = new Thread(transfer);
            t.start();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}

class Transfer implements Runnable {
    private File file;
    private String from;
    private String to;

    public Transfer(File file, String from, String to) {
        this.file = file;
        this.from = from;
        this.to = to;
    }

    @Override
    public void run() {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                Transfer transfer = new Transfer(f, from, to);
                Thread t = new Thread(transfer);
                t.start();
            }
        } else {
            try {
                transfering(file, from, to);
            } catch (FileNotFoundException | UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private void transfering(File file, String from, String to)
            throws FileNotFoundException, UnsupportedEncodingException {
        Scanner in = new Scanner(file, from);
        StringBuffer buffer = new StringBuffer();
        while (in.hasNextLine()) {
            buffer.append(in.nextLine() + "\n");
        }
        PrintWriter writer = new PrintWriter(file, to);
        writer.write(buffer.toString());
        writer.flush();
        writer.close();
    }
}
package ChapterFourteen.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ThreadLocalTest
 */
public class ThreadLocalTest {
    // private static final ThreadLocal<SimpleDateFormat> dateFormat = ThreadLocal
    // .withInitial(() -> new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"));
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public static void main(String[] args) {

        Runnable r = new Runnable() {

            @Override
            public void run() {
                while (true) {
                    String dateStamp = dateFormat.format(new Date());
                    System.out.println(dateStamp);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        };
        for (int i = 0; i < 20; i++) {
            Thread thread = new Thread(r);
            thread.start();
        }
    }
}
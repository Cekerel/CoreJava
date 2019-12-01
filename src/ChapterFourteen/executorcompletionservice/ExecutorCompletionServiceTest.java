package ChapterFourteen.executorcompletionservice;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * ExecutorCompletionServiceTest
 */
public class ExecutorCompletionServiceTest {

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(10);
        ExecutorCompletionService<String> service = new ExecutorCompletionService<>(pool);
        final Random random = new Random();
        for (int i = 0; i < 50; i++) {
            service.submit(new Callable<String>() {

                @Override
                public String call() throws Exception {
                    Thread.sleep(random.nextInt(5000));
                    return Thread.currentThread().getName();
                }
            });
        }
        int countCompletion = 0;
        while (countCompletion < 50) {
            try {
                Future<String> future = service.take();
                System.out.println(future.get() + " ");
                countCompletion++;
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block111
                e.printStackTrace();
            } catch (ExecutionException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }
}
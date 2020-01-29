package ChapterFifteen.streams.parallelStream;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * ParallelStreamForkAndJoin
 */
public class ParallelStreamForkAndJoin {

    public static long forkJoinSum(long n) {
        long [] numbers = LongStream.rangeClosed(0, n).toArray();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
        return new ForkJoinPool().invoke(task);
    }

    public static void main(String[] args) {
        System.out.println(forkJoinSum(10));
    }
}

class ForkJoinSumCalculator extends RecursiveTask<Long> {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private final long[] numbers;
    private final int start;
    private final int end;

    public static final long THRESHOLD = 10_000;

    public ForkJoinSumCalculator(long [] numbers) {
        this(numbers, 0, numbers.length);
    }

    public ForkJoinSumCalculator(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    private long computeSequentially() {
        long sum = 0;
        for (int i = start; i < end; i++) {
            sum += numbers[i];
        }
        return sum;
    }

    @Override
    protected Long compute() {
        int length = end - start;
        //Determine whether that the task is small enough or not.
        //If the task is small enough, compute it directly.
        if (length <= THRESHOLD) {
            return computeSequentially(); 
        }

        //If the task is not small enough, divide it into two parts, then compute them all.
        ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers, start, start + length / 2);
        leftTask.fork();
        ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbers, start + length / 2, end);
        Long leftResult = leftTask.join();
        Long rightResult = rightTask.compute();
        return leftResult + rightResult;
    }
}
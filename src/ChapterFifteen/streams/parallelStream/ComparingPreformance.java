package ChapterFifteen.streams.parallelStream;

import java.util.function.Function;

/**
 * ComparingPreformance
 */
public class ComparingPreformance {

    public static long measureSumPerf(Function<Long, Long> adder, long n) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            long sum = adder.apply(n);
            long duration = (System.nanoTime() - start) / 1_000_000;
            System.out.println("Result: " + sum);
            fastest = fastest < duration ? fastest : duration;
        }
        return fastest;
    }

    

    public static void main(String[] args) {
        System.out.println("Sequential sum done in: " + measureSumPerf(ParallelStream::sequentialSum, 1_000_000) + " msecs");

        
    }
}
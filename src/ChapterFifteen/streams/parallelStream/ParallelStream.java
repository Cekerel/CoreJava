package ChapterFifteen.streams.parallelStream;

import java.util.stream.Stream;

/**
 * ParallelStream
 */
public class ParallelStream {

    public static long sequentialSum(long n) {
        return Stream.iterate(1L, i -> i + 1).limit(n).reduce(0L, Long::sum);
    }

    public static long iterativeSum(long n) {
        long result = 0L;
        for (long i = 1L; i <= n; i++) {
            result += i;
        }
        return result;
    }

    public static long parallelSum(long n) {
        return Stream.iterate(1L, i -> i + 1L).limit(n).parallel().reduce(0L, Long::sum);
    }
}
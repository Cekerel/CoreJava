package ChapterFifteen.streams.parallelStream;

import java.util.stream.Stream;

/**
 * ParallelStream
 */
public class ParallelStream {

    public static long sequentialSum(long n) {
        return Stream.iterate(1L, i -> i + 1).limit(n).reduce(0L, Long::sum);
    }
}
package ChapterFifteen.streams;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collector.Characteristics;

/**
 * StreamCollectorInterface
 */
public class StreamCollectorInterface {

    public static void main(String[] args) {

    }
}

class CollectorInterface<T> implements Collector<T, List<T>, List<T>> {
    @Override
    public Supplier<List<T>> supplier() {
        // TODO Auto-generated method stub
       return () -> new ArrayList<T>();
    }

    @Override
    public BiConsumer<List<T>, T> accumulator() {
        // TODO Auto-generated method stub
        return (list, item) -> list.add(item);
    }

    @Override
    public Function<List<T>, List<T>> finisher() {
        // TODO Auto-generated method stub
        return Function.identity();
    }

    @Override
    public BinaryOperator<List<T>> combiner() {
        // TODO Auto-generated method stub12
        return (l1, l2) -> {
            l1.addAll(l2);
            return l1;
        };
    }

    @Override
    public Set<Characteristics> characteristics() {
        // TODO Auto-generated method stub
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH, Characteristics.CONCURRENT));
    }
}
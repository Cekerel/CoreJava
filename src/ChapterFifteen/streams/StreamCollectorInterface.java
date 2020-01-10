package ChapterFifteen.streams;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * StreamCollectorInterface
 */
public class StreamCollectorInterface {

    public static void main(String[] args) {

    }
}

class CollectorInterface<T> implements Collector<T, List<T>, List<T>> {
    //This method creates an accumulator instance for process of data collection 
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

class PrimeNumbersCollector implements Collector<Integer, Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> {
    @Override
    public Supplier<Map<Boolean, List<Integer>>> supplier() {
        return () -> new HashMap<Boolean, List<Integer>>();
    }

    @Override
    public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
        return (map, integer) -> map.get(isPrime(integer)).add(integer);
    }

    @Override
    public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
        return (map1, map2) -> {
            map1.get(true).addAll(map2.get(true));
            map1.get(false).addAll(map2.get(false));
        }
    }

    @Override
    public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return new Set<Characteristics> {}
        
    }
}
package ChapterFifteen.streams;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.IntStream;
import java.util.stream.Collector.Characteristics;

/**
 * StreamCollectorInterface
 */
public class StreamCollectorInterface {

    public static void main(String[] args) {
        // Map<Boolean, List<Integer>> primeIntegerMap = IntStream.range(2, 100).boxed()
        //         .collect(new PrimeNumbersCollector());
        // Set<Map.Entry<Boolean, List<Integer>>> entrySet = primeIntegerMap.entrySet();
        // for (Entry<Boolean, List<Integer>> entry : entrySet) {
        //     System.out.print(entry.getKey() + ": ");
        //     List<Integer> list = entry.getValue();
        //     System.out.println(list);
        // }

        Map<Boolean, List<Integer>> map = new HashMap<Boolean, List<Integer>>() {
            /**
             *
             */
            private static final long serialVersionUID = 1L;

            {
                put(true, new ArrayList<>());
                put(false, new ArrayList<>());
            }
        };
    }
}

class CollectorInterface<T> implements Collector<T, List<T>, List<T>> {
    /**
     * This method creates an accumulator for data collection.
     */
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
        return () -> new HashMap<Boolean, List<Integer>>() {

            /**
             *
             */
            {
                put(true, new ArrayList<Integer>());
                put(false, new ArrayList<Integer>());
            }
        };
    }

    @Override
    public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
        return (map, integer) -> {
            map.get(isPrime(map.get(true), integer)).add(integer);
        };
    }

    @Override
    public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
        return Function.identity();
    }

    @Override
    public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
        return (map1, map2) -> {
            map1.get(true).addAll(map2.get(true));
            map2.get(false).addAll(map2.get(false));
            return map1;
        };
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH));
    }

    public boolean isPrime(List<Integer> primes, Integer candidate) {
        int candidateRoot = (int) Math.sqrt((double) candidate);

        return takeWhile(primes, i -> i <= candidateRoot).stream().noneMatch(p -> candidate % p == 0);
    }

    /**
     * This method returns a list that contains the items which meet the given condition.
     */
    public <A> List<A> takeWhile(List<A> list, Predicate<A> p) {
        int i = 0;
        for (A item : list) {
            if (!p.test(item))
                return list.subList(0, i);
            i++;
        }
        return list;
    }
}
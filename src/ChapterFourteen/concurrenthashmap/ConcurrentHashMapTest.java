package ChapterFourteen.concurrenthashmap;

import java.io.File;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * ConcurrentHashMapTest
 */
public class ConcurrentHashMapTest {
    private static final ConcurrentHashMap<String, AtomicLong> concurrentHashMap = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        try (Scanner in = new Scanner(new File("src\\ChapterFourteen\\concurrenthashmap\\words.txt"), "UTF-8")){
            while (in.hasNext()) {
                String string = in.next();
                concurrentHashMap.putIfAbsent(string, new AtomicLong());
                concurrentHashMap.get(string).incrementAndGet();
            }
            Set<Map.Entry<String, AtomicLong>> resultSet = concurrentHashMap.entrySet();
            for (Map.Entry<String,AtomicLong> entry : resultSet) {
                System.out.println(entry.getKey() + " ---->  " + entry.getValue());
            }

            concurrentHashMap.forEach(Long.MAX_VALUE, (k, v) -> System.out.println(k + " -> " + v));

            concurrentHashMap.forEach(Long.MAX_VALUE, (k, v) -> k + " -> " + v , System.out::println);

        } catch (Exception e) {
            //TODO: handle exception
        }
    }

}
package ChapterFifteen.streams;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;
import java.util.Map.Entry;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.stream.Collectors.*;

import ChapterFifteen.streams.Dish.CaloricLevel;
import ChapterFifteen.streams.Dish.Type;

/**
 * StreamCollectors
 */
public class StreamReduceAndSumCollector {

    private static <T> void show(String title, Stream<T> stream) {
        System.out.println(title + " ");
        List<T> list = stream.collect(Collectors.toList());
        Iterator<T> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println("\n");
    }

    public static void main(String[] args) {
        try (Scanner input = new Scanner(new File("src\\ChapterFifteen\\streams\\string"), "UTF-8")) {
            List<String> list = new ArrayList<>();
            while (input.hasNext()) {
                list.add(input.next());
            }

            long size = list.stream().collect(Collectors.counting());
            System.out.println("The number of the strings is " + size);
            long quantity = list.stream().count();
            System.out.println("The quantity of the strings is " + quantity);

            Comparator<String> stringLengthComparator = Comparator.comparing(String::length);
            Optional<String> longestString = list.stream().collect(Collectors.maxBy(stringLengthComparator));
            System.out.println("The longest string is " + longestString.orElse("") + ", and its length is "
                    + longestString.orElse("").length());
            Optional<String> shortestString = list.stream().collect(Collectors.minBy(stringLengthComparator));
            System.out.println("The shortest string is " + shortestString.orElse("") + ", and its length is "
                    + shortestString.orElse("").length());

            long totalStringLength = list.stream().collect(Collectors.summingInt(String::length));
            System.out.println("The total length of the whole strings is " + totalStringLength);

            double averageStringLength = list.stream().collect(Collectors.averagingInt(String::length));
            System.out.println("The average length of the whole strings is " + averageStringLength);

            IntSummaryStatistics statistics = list.stream().collect(Collectors.summarizingInt(String::length));
            System.out.println("The number of the strings is " + statistics.getCount()
                    + ", the maximum string length is " + statistics.getMax() + ", the minimum string length is "
                    + statistics.getMin() + ", the total length of the whole strings is " + statistics.getSum()
                    + ", the average string length is " + statistics.getAverage());

            String theWholeString = list.stream().collect(Collectors.joining(" "));
            System.out.println("The strings in the file are as follows: " + theWholeString);

            long sum = list.stream().collect(Collectors.reducing(0, String::length, (a, b) -> a + b));
            System.out.println("The total length of the whole strings is " + sum);

            Optional<String> theLongestString = list.stream()
                    .collect(Collectors.reducing((a, b) -> a.length() > b.length() ? a : b));
            System.out.println("The longest string is " + theLongestString.orElse(""));

            Optional<String> theShortestString = list.stream()
                    .collect(Collectors.reducing((a, b) -> a.length() < b.length() ? a : b));
            System.out.println("The shortest string is " + theShortestString.orElse(""));

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

}
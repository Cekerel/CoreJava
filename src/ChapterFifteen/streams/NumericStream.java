package ChapterFifteen.streams;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * NumericStream
 */
public class NumericStream {
    private static <T> void show(String title, Stream<T> stream) {
        System.out.println(title + ": ");
        List<T> list = stream.collect(Collectors.toList());
        Iterator<T> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println("\n");
    }

    public static void main(String[] args) {
        try (Scanner input = new Scanner(new File("src\\ChapterFifteen\\streams\\string"), "utf-8")) {
            List<String> list = new ArrayList<>();
            while (input.hasNext()) {
                list.add(input.next());
            }
            int totalLength = list.stream().mapToInt(w -> w.length()).sum();
            System.out.println("The total string length is " + totalLength);

            int totalLength2 = list.stream().mapToInt(String::length).sum();
            System.out.println("The total string length is " + totalLength2);

            IntStream intStream = list.stream().mapToInt(String::length);
            Stream<Integer> integerStream = intStream.boxed();
            show("The length stream", integerStream);

            OptionalInt maximumStringLength = list.stream().mapToInt(w -> w.length()).max();
            System.out.println("The maximum string length is " + maximumStringLength.orElse(Integer.MAX_VALUE));
            OptionalInt minimumStringLength = list.stream().mapToInt(w -> w.length()).min();
            System.out.println("The minimum string length is " + minimumStringLength.orElse(Integer.MIN_VALUE));
            OptionalDouble averageStringLength = list.stream().mapToInt(w -> w.length()).average();
            System.out.println("The average string length is " + averageStringLength.orElse(0));
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
}
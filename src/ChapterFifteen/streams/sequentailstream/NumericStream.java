package ChapterFifteen.streams.sequentailstream;

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

    private static void showTuple(String title, Stream<int []> stream) {
        System.out.println(title + " ");
        stream.forEach(t -> System.out.println(t[0] + " " + t[1] + " " + t[2]));
    }
    

    public static void main(String[] args) {
        try (Scanner input = new Scanner(new File("src\\ChapterFifteen\\streams\\sequentailstream\\string"), "utf-8")) {
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

            IntStream rangeNotClosedStream = IntStream.range(0, 100);
            show("The stream without the closed range", rangeNotClosedStream.boxed());
            IntStream rangeClosedStream = IntStream.rangeClosed(0, 100);
            show("The stream with the closed range", rangeClosedStream.boxed());


            Stream<int []> pythagoreanTuple = IntStream.rangeClosed(1, 100)
                                                    .boxed()
                                                    .flatMap(a -> IntStream.rangeClosed(a, 100)
                                                            .mapToObj(b -> new int[] {a, b, (int)Math.sqrt(a * a + b * b)})).filter(t -> t[2] % 1 == 0);
            showTuple("Pythagorean Tuples", pythagoreanTuple);


        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }
    }
}
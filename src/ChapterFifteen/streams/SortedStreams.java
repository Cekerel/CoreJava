package ChapterFifteen.streams;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * SortedStreams
 */
public class SortedStreams {

    private static <T> List<T> show(String title, Stream<T> stream) {
        System.out.print(title + ": ");
        List<T> list = stream.collect(Collectors.toList());
        Iterator<T> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println("\n");
        return list;
    }
    public static void main(String[] args) {
        try (Scanner input = new Scanner(new File("src\\ChapterFifteen\\streams\\string"), "UTF-8")) {
            List<String> list = new ArrayList<>();
            while (input.hasNextLine()) {
                list.add(input.nextLine());
            }
            Stream<String> stringStream = list.stream();
            show("The original string stream", stringStream);
            Stream<String> naturalOrderSortedStringStream = list.stream().sorted(Comparator.comparing(String::length));
            show("The natural order sorted string stream", naturalOrderSortedStringStream);
            Stream<String> reverseOrderSortedStringStream = list.stream().sorted(Comparator.comparing(String::length).reversed());
            show("The reverse order sorted string stream", reverseOrderSortedStringStream);

            Stream<Double> numberStream = Stream.generate(Math::random).limit(10);
            List<Double> numberList = show("The original number stream", numberStream);
            Stream<Double> naturalOrderSortedNumberStream = numberList.stream().sorted(Comparator.naturalOrder());
            show("The natural order sorted number stream", naturalOrderSortedNumberStream);
            Stream<Double> reverseOrderSortedNumberStream = numberList.stream().sorted(Comparator.reverseOrder());
            show("The reverse order sorted number stream", reverseOrderSortedNumberStream);

        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }
    }
}
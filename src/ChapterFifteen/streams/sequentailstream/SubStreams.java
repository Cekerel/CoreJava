package ChapterFifteen.streams.sequentailstream;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * SubStreams
 */
public class SubStreams {
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
        try (Scanner input = new Scanner(new File("src\\ChapterFifteen\\streams\\sequentailstream\\string"), "UTF-8")) {
            List<String> list = new ArrayList<>();
            while (input.hasNextLine()) {
                list.add(input.nextLine());
            }
            Stream<String> stringStream = list.stream();
            show("The original string stream", stringStream);
            Stream<String> naturalOrderSortedStringStream = list.stream().limit(3);
            show("The limited string stream", naturalOrderSortedStringStream);
            Stream<String> reverseOrderSortedStringStream = list.stream().skip(3);
            show("The skip string stream", reverseOrderSortedStringStream);
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }
    }
}
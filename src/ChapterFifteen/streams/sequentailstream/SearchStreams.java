package ChapterFifteen.streams.sequentailstream;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This demonstrates the situation where uses the method which can help to search for some elements in the stream
 */
public class SearchStreams {

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
        try (Scanner input = new Scanner(new File("src\\ChapterFifteen\\streams\\sequentailstream\\string"), "UTF-8")) {
            List<String> list = new ArrayList<>();
            while (input.hasNext()) {
                list.add(input.nextLine());
            }
            Stream<String> originalStream = list.stream();
            show("The original stream", originalStream);
            boolean ifAnyMatch = list.stream().anyMatch(w -> w.length() > 12);
            System.out.println("If any match : " + ifAnyMatch);
            boolean ifAllMatch = list.stream().allMatch(w -> w.length() > 2);
            System.out.println("If all match : " + ifAllMatch);
            boolean ifNoneMatch = list.stream().noneMatch(w -> w.length() > 120);
            System.out.println("If none match : " + ifNoneMatch);
            Optional<String> ifFindAny = list.stream().filter(w -> w.length() > 6).findAny();
            System.out.println("If find any : " + ifFindAny.orElse("Null"));
            Optional<String> getFirstIfFindAny = list.stream().filter(w -> w.length() > 6).findFirst();
            System.out.println("Get the first if find any : " + getFirstIfFindAny.orElse("Null"));
            Optional<String> getLastIfFindAny = list.stream().filter(w -> w.length() > 6).sorted(Comparator.comparing(String::length).reversed()).findFirst();
            System.out.println("Get the last if find any : " + getLastIfFindAny.orElse("Null"));
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }
    }
}
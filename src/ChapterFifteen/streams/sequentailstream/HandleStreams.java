package ChapterFifteen.streams.sequentailstream;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * HandlerStream
 */
public class HandleStreams {
    private static <T> void show(String title, Stream<T> stream) {
        System.out.print(title + ": ");
        List<T> list = stream.collect(Collectors.toList());
        Iterator<T> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println("\n");
    }
    private static Stream<String> letter(String string) {
        List<String> list = new ArrayList<>();
        int length = string.length();
        for (int i = 0; i < length; i++) {
            list.add(string.substring(i, i + 1));
        }
        return list.stream();
    }

    public static void main(String[] args) {
        try (Scanner input = new Scanner(new File("src\\ChapterFifteen\\streams\\sequentailstream\\string"), "UTF-8")) {
            List<String> list = new ArrayList<>();
            while (input.hasNext()) {
                list.add(input.nextLine());
            }
            Stream<String> originalStream = list.stream();
            show("The original stream", originalStream);
            Stream<String> distinctStream = list.stream().distinct();
            show("Distinct Method", distinctStream);
            Stream<String> streamOne = list.stream().map(String::toLowerCase);
            show("Map Method", streamOne);
            Stream<String> streamTwo = list.stream().filter(w -> w.length() > 15);
            show("Filter Method", streamTwo);
            Stream<String> streamThree = list.stream().filter(w -> w.length() > 15).flatMap(w -> letter(w));
            show("FlatMap Method", streamThree);
            Stream<String> streamFour = list.stream().peek(s -> System.out.println(s));
            show("Peek Method", streamFour);



        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }
    }
}
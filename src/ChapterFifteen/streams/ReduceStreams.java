package ChapterFifteen.streams;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * ReduceStreams
 */
public class ReduceStreams {

    public static void main(String[] args) {
        try (Scanner input = new Scanner(new File("src\\ChapterFifteen\\streams\\string"), "UTF-8")) {
            List<String> list = new ArrayList<>();
            while (input.hasNextLine()) {
                list.add(input.nextLine());
            }
            Integer count = list.stream().map(w -> 1).reduce(0, Integer::sum);
            System.out.println("The total number of string is " + count);
            Integer length = list.stream().map(w -> w.length()).reduce(0, Integer::sum);
            System.out.println("The total string length is " + length);
            Optional<Integer> maximum = list.stream().map(w -> w.length()).reduce(Integer::max);
            System.out.println("The maximum string length is " + maximum.orElse(Integer.MAX_VALUE));
            Optional<Integer> minimum = list.stream().map(w -> w.length()).reduce(Integer::min);
            System.out.println("The minimum string length is " + minimum.orElse(Integer.MIN_VALUE));
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }
    }
}
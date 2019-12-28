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
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.stream.Collectors.*;

import ChapterFifteen.streams.Dish.CaloricLevel;

/**
 * StreamCollectors
 */
public class StreamCollectors {

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




            List<Dish> dishList = extractDishFromFile("src\\ChapterFifteen\\streams\\Dishes");
            Map<CaloricLevel, List<Dish>> dishByCaloricLevel = dishList.stream().collect(Collectors.groupingBy(dish -> 
                                                                    {
                                                                        if(dish.getCalories() <= 400)
                                                                            return CaloricLevel.DIET;
                                                                        else if (dish.getCalories() <= 700)
                                                                            return CaloricLevel.NORMAL;
                                                                        else
                                                                            return CaloricLevel.FAT;
                                                                    }));
            Set<Map.Entry<CaloricLevel,List<Dish>>> entrySet = dishByCaloricLevel.entrySet();
            Iterator iterator = entrySet.iterator();
            while (iterator.hasNext()) {
                Map.Entry<CaloricLevel, List<Dish>> entry = (Map.Entry<CaloricLevel, List<Dish>>)iterator.next();
                System.out.print(entry.getKey() + ": ");
                List<Dish> dishes = entry.getValue();
                for (Dish dish2 : dishes) {
                    System.out.print(dish2.getName() + " ");
                }
                System.out.println();
            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    private static List<Dish> extractDishFromFile(String path) {
        List<Dish> dishList = new ArrayList<>();
        try (Scanner input = new Scanner(new File(path), "UTF-8")) {
            while (input.hasNextLine()) {
                String[] string = input.nextLine().split(" ");
                Dish dish = new Dish(string[0], string[1], Integer.parseInt(string[2]));
                dishList.add(dish);
            }
            return dishList;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return null;
        }

    }
}

class Dish {
    private String name;
    private int calories;
    private String type;

    public Dish(String name, String type, int calories) {
        this.name = name;
        this.calories = calories;
        this.type = type;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCalories() {
        return this.calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public enum CaloricLevel {
        DIET, NORMAL, FAT
    }

}

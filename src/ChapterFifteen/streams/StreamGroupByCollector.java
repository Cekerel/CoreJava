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
public class StreamGroupByCollector {

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

        List<Dish> dishList = Dish.generateDishList();
        Map<CaloricLevel, List<Dish>> dishByCaloricLevel = dishList.stream().collect(Collectors.groupingBy(dish -> {
            if (dish.getCalories() <= 400)
                return Dish.CaloricLevel.DIET;
            else if (dish.getCalories() <= 700)
                return Dish.CaloricLevel.NORMAL;
            else
                return Dish.CaloricLevel.FAT;
        }));
        Set<Map.Entry<Dish.CaloricLevel, List<Dish>>> entrySet = dishByCaloricLevel.entrySet();
        Iterator iterator = entrySet.iterator();
        while (iterator.hasNext()) {
            Map.Entry<Dish.CaloricLevel, List<Dish>> entry = (Map.Entry<Dish.CaloricLevel, List<Dish>>) iterator.next();
            System.out.print(entry.getKey() + ": ");
            List<Dish> dishes = entry.getValue();
            for (Dish dish2 : dishes) {
                System.out.print(dish2.getName() + " ");
            }
            System.out.println();
        }

        Map<Dish.Type, Map<Dish.CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel = dishList.stream()
                .collect(Collectors.groupingBy(Dish::getType, Collectors.groupingBy(dish -> {
                    if (dish.getCalories() <= 400)
                        return Dish.CaloricLevel.DIET;
                    else if (dish.getCalories() >= 700)
                        return Dish.CaloricLevel.FAT;
                    else
                        return Dish.CaloricLevel.NORMAL;
                })));
        Set<Map.Entry<Dish.Type, Map<Dish.CaloricLevel, List<Dish>>>> entryForDishesByTypeCaloricLevel = dishesByTypeCaloricLevel
                .entrySet();
        Iterator iterator2 = entryForDishesByTypeCaloricLevel.iterator();
        while (iterator2.hasNext()) {
            Map.Entry<Dish.Type, Map<Dish.CaloricLevel, List<Dish>>> entry = (Map.Entry<Dish.Type, Map<Dish.CaloricLevel, List<Dish>>>) iterator2
                    .next();
            Dish.Type type = entry.getKey();
            System.out.print(type + ": {");
            Set<Map.Entry<Dish.CaloricLevel, List<Dish>>> set2 = ((Map<Dish.CaloricLevel, List<Dish>>) entry.getValue())
                    .entrySet();
            Iterator iterator3 = set2.iterator();
            while (iterator3.hasNext()) {
                Map.Entry<Dish.CaloricLevel, List<Dish>> entry2 = (Map.Entry<Dish.CaloricLevel, List<Dish>>) iterator3
                        .next();
                System.out.print(entry2.getKey() + ": [");
                List<Dish> dishListAfterGroupingby = entry2.getValue();
                for (Dish dish : dishListAfterGroupingby) {
                    System.out.print(dish.getName() + " ");
                }
                System.out.print("] ");
            }
            System.out.println("}");
        }

        Map<Dish.Type, Long> typesCount = dishList.stream()
                .collect(Collectors.groupingBy(Dish::getType, Collectors.counting()));
        Set<Map.Entry<Dish.Type, Long>> typesCountEntrySet = typesCount.entrySet();
        Iterator typesCountEntrySetIterator = typesCountEntrySet.iterator();
        while (typesCountEntrySetIterator.hasNext()) {
            Map.Entry<Dish.Type, Long> entry2 = (Map.Entry<Type, Long>) typesCountEntrySetIterator.next();
            System.out.println(entry2.getKey() + ": " + entry2.getValue());
        }

        Map<Dish.Type, Dish> mostCaloricByType = dishList.stream()
                .collect(Collectors.groupingBy(Dish::getType, Collectors.collectingAndThen(
                        Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)), Optional::get)));
        Set<Map.Entry<Dish.Type, Dish>> mostCaloricByTypeEntrySet = mostCaloricByType.entrySet();
        Iterator mostCaloricByTypeIterator = mostCaloricByTypeEntrySet.iterator();
        while (mostCaloricByTypeIterator.hasNext()) {
            Map.Entry<Dish.Type, Dish> entry = (Map.Entry<Dish.Type, Dish>) mostCaloricByTypeIterator.next();
            System.out.println(entry.getKey() + ": " + entry.getValue().toString());

        }

        Map<Dish.Type, Double> averageCaloricByType = dishList.stream()
                .collect(Collectors.groupingBy(Dish::getType, Collectors.averagingInt(Dish::getCalories)));
        Set<Map.Entry<Dish.Type, Double>> averageCaloricByTypeEntrySet = averageCaloricByType.entrySet();
        Iterator averageCaloricByTypeIterator = averageCaloricByTypeEntrySet.iterator();
        while (averageCaloricByTypeIterator.hasNext()) {
            Map.Entry<Dish.Type, Double> entry = (Map.Entry<Dish.Type, Double>) averageCaloricByTypeIterator.next();
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

    }

}
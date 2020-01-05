package ChapterFifteen.streams;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import ChapterFifteen.streams.Dish.CaloricLevel;

/**
 * StreamCollectors
 */
public class StreamGroupByCollector {

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
        for (Map.Entry<Dish.CaloricLevel, List<Dish>> entry : entrySet) {
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
        for (Map.Entry<Dish.Type, Map<Dish.CaloricLevel, List<Dish>>> entry : entryForDishesByTypeCaloricLevel) {
            Dish.Type type = entry.getKey();
            System.out.print(type + ": {");
            Set<Map.Entry<Dish.CaloricLevel, List<Dish>>> set2 = ((Map<Dish.CaloricLevel, List<Dish>>) entry.getValue())
                    .entrySet();
            for (Map.Entry<Dish.CaloricLevel, List<Dish>> entry2 : set2) {
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
        for (Map.Entry<Dish.Type, Long> entry2 : typesCountEntrySet) {
            System.out.println(entry2.getKey() + ": " + entry2.getValue());
        }

        Map<Dish.Type, Dish> mostCaloricByType = dishList.stream()
                .collect(Collectors.groupingBy(Dish::getType, Collectors.collectingAndThen(
                        Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)), Optional::get)));
        Set<Map.Entry<Dish.Type, Dish>> mostCaloricByTypeEntrySet = mostCaloricByType.entrySet();
        for (Map.Entry<Dish.Type, Dish> entry : mostCaloricByTypeEntrySet) {
            System.out.println(entry.getKey() + ": " + entry.getValue().toString());

        }

        Map<Dish.Type, Double> averageCaloricByType = dishList.stream()
                .collect(Collectors.groupingBy(Dish::getType, Collectors.averagingInt(Dish::getCalories)));
        Set<Map.Entry<Dish.Type, Double>> averageCaloricByTypeEntrySet = averageCaloricByType.entrySet();
        for (Map.Entry<Dish.Type, Double> entry : averageCaloricByTypeEntrySet) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

    }

}
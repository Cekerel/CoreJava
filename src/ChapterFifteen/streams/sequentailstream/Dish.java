package ChapterFifteen.streams.sequentailstream;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Dish {
    private String name;
    private int calories;
    private Type type;
    private boolean vegetarian;

    public Dish(String name, String type, int calories, Boolean vegetarian) {
        this.name = name;
        this.calories = calories;
        this.type = Type.valueOf(type);
        this.vegetarian = vegetarian;
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

    public Type getType() {
        return this.type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public boolean isVegetarian() {
        return this.vegetarian;
    }

    public boolean getVegetarian() {
        return this.vegetarian;
    }

    public void setVegetarian(boolean Vegetarian) {
        this.vegetarian = Vegetarian;
    }

    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", calories='" + getCalories() + "'" +
            ", type='" + getType() + "'" +
            ", Vegetarian='" + isVegetarian() + "'" +
            "}";
    }

    enum CaloricLevel {
        DIET, NORMAL, FAT
    }

    enum Type {
        FISH, VEGETABLES, MEAT, FRUIT, OTHER;
    }


    public static List<Dish> generateDishList() {
        String path = "src\\ChapterFifteen\\streams\\sequentailstream\\string";
        List<Dish> dishList = new ArrayList<>();
        try (Scanner input = new Scanner(new File(path), "UTF-8")) {
            while (input.hasNextLine()) {
                String[] strings = input.nextLine().split(" ");
                Dish dish = new Dish(strings[0], strings[1], Integer.parseInt(strings[2]),
                        Boolean.parseBoolean(strings[3]));
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

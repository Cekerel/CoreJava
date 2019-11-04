package ChapterNine;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Intersection {
    public static void main(String[] args) {
        List<Integer> aList = new ArrayList<>();
        List<Integer> bList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            aList.add(i);
        }
        for (int i = 10; i < 30; i++) {
            bList.add(i);
        }
        aList.retainAll(bList);
        listIterator(aList);
    }
    private static void listIterator(List<Integer> list) {
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
    }
}

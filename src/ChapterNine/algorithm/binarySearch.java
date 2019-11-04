package ChapterNine.algorithm;

import java.util.*;

public class binarySearch {
    public static void main(String[] args) {
        int [] nums = {1, 5, 10, 12, 15, 20, 23, 25, 29, 37, 39, 40};
        List<Integer> list = new ArrayList<>();
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            list.add(nums[i]);
        }
        int index = Collections.binarySearch(list, 13);
        if (index >= 0)
            System.out.println("The index is " + index);
        else {
            System.out.println(index);
            list.add(-index - 1, 13);
            Iterator<Integer> iterator = list.iterator();
            while (iterator.hasNext()) {
                System.out.print(iterator.next() + " ");
            }
        }
    }
}

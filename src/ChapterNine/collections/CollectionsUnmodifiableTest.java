package ChapterNine.collections;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CollectionsUnmodifiableTest {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("20", "30", "40", "50");
        list = lookAt(Collections.unmodifiableList(list));
        checkUnsupportedOperationException(Collections.unmodifiableList(list));
    }

    public static List<String> lookAt(List<String> list) {

        for (String string :
                list) {
            System.out.println(string);
        }
        return list;
    }

    public static void checkUnsupportedOperationException(List<String> list) {
        try {
            list.add("60");
        } catch (UnsupportedOperationException exception) {
            exception.printStackTrace();
        }
    }
}

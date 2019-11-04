package ChapterNine.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShuffleTest {
	public static void main(String[] args) {
		List<Integer> numbers = new ArrayList<Integer>();
		for (int i = 0; i < 49; i++) {
			numbers.add(i + 1);
		}
		Collections.shuffle(numbers);
		List<Integer> winningCombination = numbers.subList(0, 6);
		Collections.sort(winningCombination);
		System.out.println(winningCombination);
	}
}

package ChapterNine.linkedList;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * This program demonstrates opeartions on linked lists.
 * @version 1.11 2012-01-26
 * @author Cay Horstmann
 *
 */
public class LinkedListTest {
	public static void main(String[] args) {
		List<String> aList = new LinkedList<>();
		aList.add("Amy");
		aList.add("Carl");
		aList.add("Erica");

		List<String> bList = new LinkedList<>();
		bList.add("Bob");
		bList.add("Doug");
		bList.add("Frances");
		bList.add("Gloria");
		
		//merge the word from b into a

		ListIterator<String> aIterator = aList.listIterator();
		Iterator<String> bIterator = bList.iterator();
		while (bIterator.hasNext()) {
			if (aIterator.hasNext()) {
				aIterator.add(bIterator.next());
			}
		}
		
		System.out.println(aList);
		
		// remove every second word from b
		
		bIterator = bList.iterator();
		
		while (bIterator.hasNext()) {
			bIterator.next();	//skip one element
			if (bIterator.hasNext()) {
				bIterator.next(); //skip next element
				bIterator.remove(); //remove that element
			}
		}
		
		System.out.println(bList);
		
		// bulk operation: remove all words in b from a
		
		aList.removeAll(bList);
		
		System.out.println(aList);
	}
}

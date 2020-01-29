package ChapterFifteen.streams.sequentailstream;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.*;

/**
 * StreamCollectedIntoMaps
 */
public class StreamCollectedIntoMaps {
    public static class Person {
        private int id;
        private String name;

        public Person(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return this.id;
        }

        public String getName() {
            return this.name;
        }

        @Override
        public String toString() {
            return "{" + " id='" + getId() + "'" + ", name='" + getName() + "'" + "}";
        }
    }

    public static void main(String[] args) {
        List<Person> personList = Arrays.asList(new Person(1001, "Peter"), new Person(1002, "Paul"),
                new Person(1003, "Mary"));

        Map<Integer, String> idToName = personList.stream().collect(Collectors.toMap(Person::getId, Person::getName));
        Set<Map.Entry<Integer, String>> idToNameEntrySet = idToName.entrySet();
        for (Entry<Integer,String> entry : idToNameEntrySet) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
        System.out.println("idToName: " + idToName);

        Map<Integer, Person> idToPerson = personList.stream().collect(Collectors.toMap(Person::getId, Function.identity()));
        System.out.println("idToPerson: " + idToPerson.getClass().getName() + " " + idToPerson);

        idToPerson = personList.stream().collect(Collectors.toMap(Person::getId, Function.identity(), (existingValue, newValue) -> {
            throw new IllegalStateException();
        }, TreeMap::new));
        System.out.println("idToPerson: " + idToPerson.getClass().getName() + " " + idToPerson);

        Map<String, String> languageNames = Stream.of(Locale.getAvailableLocales()).collect(Collectors.toMap(Locale::getDisplayLanguage,
                l -> l.getDisplayLanguage(l), (existingValue, newValue) -> existingValue));
        System.out.println("languagesName: " + languageNames);

        Map<String, Set<String>> countryLanguageSets = Stream.of(Locale.getAvailableLocales()).collect(Collectors.toMap(Locale::getDisplayCountry, l -> Collections.singleton(l.getDisplayLanguage()), (a, b) -> {
            Set<String> union = new HashSet<>(a);
            union.addAll(b);
            return union;
        }));
        System.out.println("countryLanguageSets: " + countryLanguageSets);

    }
}

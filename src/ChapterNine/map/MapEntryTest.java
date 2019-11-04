package ChapterNine.map;

import ChapterNine.Employee;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapEntryTest {
    public static void main(String[] args) {
        Map<String, Employee> employeeMap = new HashMap<>();
        employeeMap.put("144-25-5464", new Employee("Amy Lee"));
        employeeMap.put("56-24-2546", new Employee("Harry Hacker"));
        employeeMap.put("157-62-7935", new Employee("Gary Cooper"));
        employeeMap.put("456-62-5527", new Employee("Francesca Cruz"));

        Set<Map.Entry<String, Employee>> entries = employeeMap.entrySet();
        for (Map.Entry<String, Employee> temp :
                entries) {
            System.out.println(temp.getKey() + " --- " + temp.getValue().toString());
        }
    }
}

package ChapterSixteen;

import java.io.*;
import java.util.*;
import java.time.*;


/**
 * RandomAccessTest
 */
public class RandomAccessTest {

    public static void main(String[] args) throws IOException {
        Employee [] staff = new Employee[3];
        staff[0] = new Employee("Carly", 75000, 1987, 12, 15);
        staff[1] = new Employee("Harry Hacker", 50000, 1989, 10, 1);
        staff[2] = new Employee("Tony Tester", 40000, 1990, 3, 15);
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream("src\\ChapterSixteen\\employeeData.dat"))) {
            //save all employee records to the file employee.dat
            for (Employee employee : staff) {
                writeData(out, employee);
            }
        }


        try (RandomAccessFile in = new RandomAccessFile("src\\ChapterSixteen\\employeeData.dat", "r")) {
            //retrive all records into a new array

            //compute the array size
            int n = (int) (in.length() / Employee.RECORD_SIZE);
            Employee [] newStaff = new Employee[n];
            
            //read employees in reverse order
            for (int i = n - 1; i >= 0; i--) {
                newStaff[i] = new Employee();
                in.seek(i * Employee.RECORD_SIZE);
                newStaff[i] = readData(in);
            }

            //print the newly read employee records
            for (Employee employee : newStaff) {
                System.out.println(employee);
            }
        }
    }

    /**
     * Writes employee data to a data output
     * @param out the data output
     * @param e the employee
     * @throws IOException
     */
    private static void writeData(DataOutput out, Employee e) throws IOException {
        DataIO.writeFixedString(e.getName(), Employee.NAME_SIZE, out);
        out.writeDouble(e.getSalary());
        LocalDate hireday = e.getHireday();
        out.writeInt(hireday.getYear());
        out.writeInt(hireday.getMonthValue());
        out.writeInt(hireday.getDayOfMonth());
    }

    /**
     * Reads employee data from a data input
     * @param in the data input
     * @return the employee
     * @throws IOException
     */
    private static Employee readData(DataInput in) throws IOException {
        String name = DataIO.readFixedString(Employee.NAME_SIZE, in);
        double salary = in.readDouble();
        int y = in.readInt();
        int m = in.readInt();
        int d = in.readInt();
        return new Employee(name, salary, y, m - 1, d);
    }
}
package ChapterFifteen.optional;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Properties;

/**
 * OptionalMap
 */
public class OptionalMap {

    public static void main(String[] args) {

        // getNameFromEmptyOptionalInsurance();
        // Properties properties = new Properties();
        // properties.setProperty("a", "5");
        // properties.setProperty("b", "true");
        // properties.setProperty("c", "-3");
        // System.out.println(readDuration(properties, "b"));
        Optional<Person> person = Optional.empty();
        Optional<Car> car = Optional.empty();
        System.out.println(readInsuranceNameIfNull(person, car));

    }

    public static String getCarInsuranceName(Optional<Person> person) {
        return person.flatMap(Person::getCar).flatMap(Car::getInsurance).map(Insurance::getName).orElse("Unknown");
    }

    public static <T> T getNullableOptionalObject(Optional<T> optional) {
        return optional.get();
    }

    /**
     * If create an optional from a null value with the method <b>Option.of</b>, a
     * <b><i>NullPointerException</i></b> would be immediately thrown
     * 
     * @throws NullPointerException
     */
    public static void getNameFromOptionalInsurance() throws NullPointerException {
        Insurance insurance = null;
        Optional<Insurance> insuranceOptional = Optional.of(insurance);
        Optional<String> string = insuranceOptional.map(Insurance::getName);
        System.out.println(string.get());
    }

    /**
     * If create an optional from a null value with the method
     * <b>Optional.ofNullable</b>, a <b><i>NoSuchELementException</i></b> would be
     * immediately thrown
     * 
     * @throws NoSuchElementException
     */
    public static void getNameFromNullableOptionalInsurance() throws NoSuchElementException {
        Insurance insurance = null;
        Optional<Insurance> insuranceOptional = Optional.ofNullable(insurance);
        Optional<String> string = insuranceOptional.map(Insurance::getName);
        System.out.println("The optional contains a null pointer : " + !string.isPresent());
    }

    /**
     * If create an optional from an empty value with the method
     * <b>Optional.empty</b>, a <b><i>NoSuchElementException</i></b> would be
     * immediately thrown
     * 
     * @throws NoSuchElementException
     */
    public static void getNameFromEmptyOptionalInsurance() throws NoSuchElementException {
        Optional<Insurance> personOptional = Optional.empty();
        System.out.println(personOptional.get().getName());
    }

    public Insurance findCheapestInsurance(Person person, Car car) {
        Insurance insurance = new Insurance();
        return insurance;
    }

    public Optional<Insurance> nullSafeFindCheapestInsurance(Optional<Person> person, Optional<Car> car) {
        // if (person.isPresent() && car.isPresent()) {
        // return Optional.of(findCheapestInsurance(person.get(), car.get()));
        // } else {
        // return Optional.empty();
        // }
        return person.flatMap(p -> car.map(c -> findCheapestInsurance(p, c)));
    }

    public static Optional<Integer> stringToInt(String string) {
        try {
            return Optional.ofNullable(Integer.parseInt(string));
        } catch (Exception e) {
            //TODO: handle exception
            return Optional.empty();
        }
    }

    public static int readDuration(Properties props, String name) {
        return Optional.ofNullable(props.getProperty(name)).flatMap(s -> stringToInt(s)).filter(i -> i > 0).orElse(0);
    }

    public static String readInsuranceNameIfNull(Optional<Person> person, Optional<Car> car) {
        return person.flatMap(Person::getCar).flatMap(Car::getInsurance).map(Insurance::getName).orElse("Default");
    }
}
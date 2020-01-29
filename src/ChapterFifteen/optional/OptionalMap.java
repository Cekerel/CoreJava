package ChapterFifteen.optional;

import java.util.Optional;

/**
 * OptionalMap
 */
public class OptionalMap {

    public static void main(String[] args) {
        Person person = null;
        System.out.println(getNullableOptionalObject(Optional.ofNullable(person)));

        Person personNotNull = new Person();
        System.out.println(getCarInsuranceName(Optional.of(personNotNull)));
    }

    public static String getCarInsuranceName(Optional<Person> person) {
        return person.flatMap(Person::getCar)
                    .flatMap(Car::getInsurance)
                    .map(Insurance::getName)
                    .orElse("Unknown");
    }

    public static <T> T getNullableOptionalObject(Optional<T> optional) {
        return optional.get();
    }
}
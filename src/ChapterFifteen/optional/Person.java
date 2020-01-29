package ChapterFifteen.optional;

import java.util.Optional;

/**
 * Person
 */
public class Person {
    private Optional<Car> car;

    public Optional<Car> getCar() {
        return this.car;
    }    
}

class Car {
    private Optional<Insurance> insurance;

    public Optional<Insurance> getInsurance() {
        return this.insurance;
    } 
}

class Insurance {
    private String name = "Hello";
    public String getName() {
        return name;
    }
}
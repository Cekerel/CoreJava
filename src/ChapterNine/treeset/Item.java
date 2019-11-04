package ChapterNine.treeset;

import java.util.Objects;

public class Item implements Comparable<Item>{
    private String description;
    private int partNumber;

    public Item(String description, int partNumber) {
        this.description = description;
        this.partNumber = partNumber;
    }

    public String getDescription() {
        return description;
    }

    public String toString() {
        return "[Description = "+ description + ", partNumber = " + partNumber + "]";
    }
    public boolean equals(Object otherObject) {
        if (this == otherObject) return true;
        if (otherObject == null) return false;
        if (this.getClass() != otherObject.getClass()) return false;
        Item item = (Item) otherObject;
        return item.partNumber == this.partNumber && Objects.equals(description, item.description);
    }

    public int hasdCode() {
        return Objects.hash(description, partNumber);
    }

    public int compareTo(Item other) {
        int diff = Integer.compare(partNumber, other.partNumber);
        return diff != 0 ? diff : description.compareTo(other.description);
    }
}

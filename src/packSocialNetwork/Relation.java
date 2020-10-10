package packSocialNetwork;

import java.util.Objects;

/**
 * Relation defines the relation of two people, this relation is mutual.
 * This project is being developed on Data Structures and Algorithms subject on UPV/EHU at 2020/2021 academic year.
 *
 * @author Iyan Alvarez and Davy Wellinger
 * @version firstMilestone
 */
public class Relation {

    // Attributes
    private String person1;
    private String person2;


    // Constructor
    public Relation(String person1, String person2) {
        this.person1 = person1;
        this.person2 = person2;
    }

    // Methods
    public String getPerson1() {
        return person1;
    }
    public String getPerson2() {
        return person2;
    }
    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Relation)) return false;
        Relation that = (Relation) o;
        return ((Objects.equals(this.person1, that.getPerson1()) &&
                 Objects.equals(this.person2, that.getPerson2())) ||
                (Objects.equals(this.person1, that.getPerson2()) &&
                 Objects.equals(this.person2, that.getPerson1())));
    }
    @Override public String toString() {
        return  person1 + "," + person2;
    }
}

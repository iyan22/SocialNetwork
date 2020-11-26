package packSocialNetwork;

import java.util.Objects;

/**
 * Relation defines the relation of two people, this relation is mutual.
 * This project is being developed on Data Structures and Algorithms subject on UPV/EHU at 2020/2021 academic year.
 *
 * @author Iyán Álvarez
 * @version secondIyan
 */
public class Relation implements Comparable<Relation> {

    // Attributes
    private String person1;
    private String person2;


    // Constructor
    public Relation(String person1, String person2) {
        this.person1 = person1;
        this.person2 = person2;
    }

    // Methods
    /**
     * Getter of the relation's person1.
     * @return ID of person1
     */
    public String getPerson1() {
        return person1;
    }
    /**
     * Getter of the relation's person2.
     * @return ID of person2
     */
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
    /**
     * Compares a Relation with the given Reltaion by the person1, and if needed by person2.
     * @param o The other Person to be compared.
     * @return a negative integer, zero, or a positive integer as this person is lexicographically less than, equal to, or greater than the specified object.
     */
    @Override public int compareTo(Relation o) {
        int res;
        if (this.person1.compareTo(o.getPerson1()) == 0) {
            if (this.person2.compareTo(o.getPerson2()) == 0) {
                res = 0;
            }
            else if (this.person2.compareTo(o.getPerson2()) < 0) {
                res = -1;
            }
            else {
                res = 1;
            }
        }
        else if (this.person1.compareTo(o.getPerson1()) < 0) {
            res = -1;
        }
        else {
            res = 1;
        }
        return res;
    }
}

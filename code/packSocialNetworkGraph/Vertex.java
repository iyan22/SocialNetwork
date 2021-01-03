package packSocialNetworkGraph;

import packSocialNetwork.Person;

import java.util.Objects;

/**
 * Vertex class, each vertex holds a person.
 */
public class Vertex {
    private Person p;


    public Vertex(Person p){

        this.p = p;


    }


    public Person getP() {
        return p;
    }

    /**
     * Equals override.
     * @param o object, should be person.
     * @return boolean, true if the comparing object is equal, false in other case.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return Objects.equals(p, vertex.p);
    }

    /**
     * HashCode override. Useful for hashing.
     * @return hashcode of the unique identifier of each person.
     */
    @Override
    public int hashCode() {
        return Objects.hash(p.getIdentifier());
    }
}

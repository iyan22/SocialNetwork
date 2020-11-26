package packSocialNetwork;

/**
 * Person has a lot of data/information and can have relations with other packSocialNetwork.Person's on the Social Network.
 * This project is being developed on Data Structures and Algorithms subject on UPV/EHU at 2020/2021 academic year.
 *
 * @author Iyán Álvarez
 * @version secondIyan
 */
public class Person implements Comparable<Person> {

    // Attributes
    private String identifier;
    private String name;
    private String surname;
    private String birthdate;
    private String gender;
    private String birthplace;
    private String home;
    private String[] studydata;
    private String[] workdata;
    private String[] movies;
    private String groupcode;

    // Constructors
    /**
     * Creates a packSocialNetwork.Person with the specified data.
     * @param identifier User ID's for the Social Network
     * @param name packSocialNetwork.Person's name.
     * @param surname packSocialNetwork.Person's surname.
     * @param birthdate packSocialNetwork.Person's birthdate in dd-mm-year format.
     * @param gender packSocialNetwork.Person's gender.
     * @param birthplace packSocialNetwork.Person's birthplace.
     * @param home packSocialNetwork.Person's home.
     * @param studydata packSocialNetwork.Person's study data.
     * @param workdata packSocialNetwork.Person's work data.
     * @param movies packSocialNetwork.Person's favourite movies.
     * @param groupcode Groupcode.
     */
    public Person(String identifier, String name, String surname, String birthdate, String gender, String birthplace, String home, String[] studydata, String[] workdata, String[] movies, String groupcode) {
        this.identifier = identifier;       // 0
        this.name = name;                   // 1
        this.surname = surname;             // 2
        this.birthdate = birthdate;         // 3
        this.gender = gender;               // 4
        this.birthplace = birthplace;       // 5
        this.home = home;                   // 6
        this.studydata = studydata;         // 7
        this.workdata = workdata;           // 8
        this.movies = movies;               // 9
        this.groupcode = groupcode;         // 10
    }

    // Methods
    /**
     * Getter of the user's ID.
     * @return user's ID (indentifier)
     */
    public String getIdentifier() {
        return identifier;
    }
    /**
     * Getter of the user's name.
     * @return Name of the person.
     */
    public String getName() {
        return name;
    }
    /**
     * Getter of the user's surname.
     * @return Surname of the person.
     */
    public String getSurname() {
        return surname;
    }
    /**
     * Getter of the user's birthplace.
     * @return Birthplace of the person.
     */
    public String getBirthplace() {
        return birthplace;
    }
    /**
     * Gets the basic information of the user: ID Name Surname
     * @return Values of ID, Name and Surname separated by blanks.
     */
    public String getBasicInfo() {
        return identifier + " " + surname;
    }
    /**
     * Compares a Person with the given Object and returns true if the ID, name and surname are the same.
     * @param o Object that is going to be compared.
     * @return true iif is the same instance or the Object is a Person and the user's ID, name and surname are the same;
     * otherwise false.
     */
    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Person)) {
            return false;
        }
        Person person = (Person) o;
        return  this.identifier.equals(person.getIdentifier()) &&
                this.name.equals(person.getName()) &&
                this.surname.equals(person.getSurname());
    }
    /**
     * Returns the basic information of a packSocialNetwork.Person in the format: idperson,name,lastname,birthdate,gender,birthplace,home,studiedat,workplaces,films,groupcode
     * @return The basic information of a packSocialNetwork.Person in the specified format.
     */
    @Override public String toString() {
        String studydataS = "";
        for(int i = 0; i < studydata.length; i++) {
            studydataS += studydata[i];
            if (i < studydata.length-1) {
                studydataS += ";";
            }
        }
        String workdataS = "";
        for(int i = 0; i < workdata.length; i++) {
            workdataS += workdata[i];
            if (i < workdata.length-1) {
                workdataS += ";";
            }
        }
        String moviesS = "";
        for(int i = 0; i < movies.length; i++) {
            moviesS += movies[i];
            if (i < movies.length-1) {
                moviesS += ";";
            }
        }
        return  identifier + "," + name + "," + surname + "," + birthdate + "," + gender + "," +
                birthplace + "," + home + "," + studydataS + "," + workdataS +
                "," + moviesS + "," + groupcode;
    }
    /**
     * Compares a Person with the given Person by the ID, and if needed by name and by surname.
     * @param o The other Person to be compared.
     * @return a negative integer, zero, or a positive integer as this person is lexicographically less than, equal to, or greater than the specified object.
     */
    @Override public int compareTo(Person o) {
        int res;
        if (this.identifier.compareTo(o.getIdentifier()) == 0) {
            if (this.name.compareTo(o.getName()) == 0) {
                if (this.surname.compareTo(o.getSurname()) == 0) {
                    res = 0;
                }
                else if (this.surname.compareTo(o.getSurname()) < 0) {
                    res = -1;
                } else {
                    res = 1;
                }
            }
            else if (this.name.compareTo(o.getName()) < 0) {
                res = -1;
            }
            else {
                res = 1;
            }
        }
        else if (this.identifier.compareTo(o.getIdentifier()) < 0) {
            res = -1;
        }
        else {
            res = 1;
        }
        return res;
    }
}

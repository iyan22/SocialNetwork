package packSocialNetwork;

import java.util.Comparator;

/**
 * Person has a lot of data/information and can have relations with other packSocialNetwork.Person's on the Social Network.
 * This project is being developed on Data Structures and Algorithms subject on UPV/EHU at 2020/2021 academic year.
 *
 * @author Iyán Álvarez and Davy Wellinger
 * @version secondMilestone
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
     * Empty constructor for searching purposes.
     */
    public Person() {}
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

    // Getters
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
     * Getter of the user's birthdate.
     * @return Birthdate of the person.
     */
    public String getBirthdate() {
        return birthdate;
    }
    /**
     * Getter of the user's Gender.
     * @return Gender of the person.
     */
    public String getGender() {
        return gender;
    }
    /**
     * Getter of the user's birthdate year.
     * @return Year of birthdate of the person.
     */
    public int getBirthdateYear() {
        String s = "";
        String[] sarr = birthdate.split("-");
        return Integer.parseInt(sarr[2]);
    }
    /**
     * Getter of the user's birthplace.
     * @return Birthplace of the person.
     */
    public String getBirthplace() {
        return birthplace;
    }
    /**
     * Getter of the user's home.
     * @return Home of the person.
     */
    public String getHome() {
        return home;
    }
    /**
     * Getter of the user's favourite movies.
     * @return Favourite movies of the person.
     */
    public String getMovies() {
        String moviesS = "";
        for(int i = 0; i < movies.length; i++) {
            moviesS += movies[i];
            if (i < movies.length-1) {
                moviesS += ";";
            }
        }
        return moviesS;
    }
    /**
     * Getter of the user's Studydata array.
     * @return user's Studydata (studydata)
     */
    public String[] getStudydata() {
        return studydata;
    }
    /**
     * Getter of the user's Workdata array.
     * @return user's Workdata (workdata)
     */
    public String[] getWorkdata() {
        return workdata;
    }
    /**
     * Getter of the user's Movies array.
     * @return user's Movies (movies)
     */
    public String[] getMoviesdata() {
        return movies;
    }
    /**
     * Getter of the user's Groupcode.
     * @return Groupcode of the person.
     */
    public String getGroupcode() {
        return groupcode;
    }
    /**
     * Gets the basic information of the user: ID Surname
     * @return Values of ID and Surname separated by blanks.
     */
    public String getBasicInfo() {
        return identifier + " " + surname;
    }
    /**
     * Gets the basic information of the user: Name Surname Birthplace StudyData
     * @return Values of Name, Surname, Birthplace and StudyData separated by blanks.
     */
    public String getMoreInfo() {
        String studydataS = "";
        for(int i = 0; i < studydata.length; i++) {
            studydataS += studydata[i];
            if (i < studydata.length-1) {
                studydataS += ";";
            }
        }
        return name + " " + surname + " " + birthplace + " " + studydataS;
    }
    /**
     * Gets different information of the user: Birthplace Surname Name
     * @return Values of Birthplace, Surname and Name separated by blanks.
     */
    public String getDifferentInfo() {
        return this.birthplace + " " + this.surname + " " + this.name;
    }

    //Setters
    /**
     * Setter for identifier.
     * @param identifier Identifier.
     */
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
    /**
     * Setter for name.
     * @param name Name.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Setter for surname.
     * @param surname Surname.
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }
    /**
     * Setter for Birthdate.
     * @param birthdate Birthdate.
     */
    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }
    /**
     * Setter for Gender.
     * @param gender Gender.
     */
    public void setGender(String gender) {
        this.gender = gender;
    }
    /**
     * Setter for Birthplace.
     * @param birthplace Birthplace.
     */
    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }
    /**
     * Setter for Home.
     * @param home Home.
     */
    public void setHome(String home) {
        this.home = home;
    }
    /**
     * Setter for Groupcode.
     * @param groupcode Groupcode.
     */
    public void setGroupcode(String groupcode) {
        this.groupcode = groupcode;
    }

    // Methods
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
     * Compares a Person with the given Person by the ID.
     * @param o The other Person to be compared.
     * @return a negative integer, zero, or a positive integer as this person is lexicographically less than, equal to, or greater than the specified object.
     */
    @Override public int compareTo(Person o) {
        int res;
        if (this.identifier.compareTo(o.getIdentifier()) == 0) {
            res = 0;
        }
        else if (this.identifier.compareTo(o.getIdentifier()) < 0) {
            res = -1;
        }
        else {
            res = 1;
        }
        return res;
    }
    public static Comparator<Person> comparatorDatesList() {
        return new Comparator<Person>() {
            @Override public int compare(Person o1, Person o2) {
                int res;
                if (o1.getBirthplace().compareTo(o2.getBirthplace()) == 0) {
                    if (o1.getSurname().compareTo(o2.getSurname()) == 0) {
                        if (o1.getName().compareTo(o2.getName()) == 0) {
                            res = 0;
                        }
                        else if (o1.getName().compareTo(o2.getName()) < 0) {
                            res = -1;
                        } else {
                            res = 1;
                        }
                    }
                    else if (o1.getSurname().compareTo(o2.getSurname()) < 0) {
                        res = -1;
                    }
                    else {
                        res = 1;
                    }
                }
                else if (o1.getBirthplace().compareTo(o2.getBirthplace()) < 0) {
                    res = -1;
                }
                else {
                    res = 1;
                }
                return res;
            }
        };
    }
    /**
     * Method for using in other methods. Compares a person by its Studydata.
     * @param ob2 Studydata of the comparing Person.
     * @return Boolean true or false.
     */
    public boolean equalsStudydata(String[] ob2){
        boolean result = false;

        int counter=0;

        for (int i=0; i<ob2.length; i++){
            for (int e=0; e<this.getStudydata().length; e++) {
                if ( ob2[i].equalsIgnoreCase(this.getStudydata()[e])){
                    counter++;
                }
            }
        }

        if (counter == ob2.length){

            result=true;
        }
        return result;
    }
    /**
     * Method for using in other methods. Compares a person by its Workdata.
     * @param ob2 Workdata of the comparing Person.
     * @return Boolean true or false.
     */
    public boolean equalsWorkdata(String[] ob2){
        boolean result = false;

        int counter=0;

        for (int i=0; i<ob2.length; i++){
            for (int e=0; e<this.getWorkdata().length; e++) {
                if (ob2[i].equalsIgnoreCase(this.getWorkdata()[e])){
                    counter++;
                }
            }
        }

        if (counter == ob2.length){

            result=true;
        }
        return result;
    }
    /**
     * Method for using in other methods. Compares a person by its favourite movies.
     * @param ob2 Movies of the comparing Person.
     * @return Boolean true or false.
     */
    public boolean equalsMovies(String[] ob2){
        boolean result = false;

        int counter=0;

        for (int i=0; i<ob2.length; i++){
            for (int e=0; e<this.getMoviesdata().length; e++) {
                if ( ob2[i].equalsIgnoreCase(this.getMoviesdata()[e]) ){
                    counter++;
                }
            }
        }

        if (counter == ob2.length){

            result=true;
        }
        return result;
    }

    //Comparators
    /**
     * Comparator method: Identifier.
     */
    public static Comparator<Person> CprIdentifier = new Comparator<Person>() {
        @Override
        public int compare(Person o1, Person o2) {
            String id1 = o1.getIdentifier();
            String id2 = o2.getIdentifier();
            return id1.compareTo(id2);
        }
    };
    /**
     * Comparator method: Name.
     */
    public static Comparator<Person> CprName = new Comparator<Person>() {
        @Override
        public int compare(Person o1, Person o2) {
            String id1 = o1.getName().toUpperCase();
            String id2 = o2.getName().toUpperCase();

            //ascending order
            return id1.compareTo(id2);
        }
    };
    /**
     * Comparator method: Surname.
     */
    public static Comparator<Person> CprSurname = new Comparator<Person>() {
        @Override
        public int compare(Person o1, Person o2) {
            String id1 = o1.getSurname().toUpperCase();
            String id2 = o2.getSurname().toUpperCase();

            //ascending order
            return id1.compareTo(id2);
        }
    };
    /**
     * Comparator method: Birthdate.
     */
    public static Comparator<Person> CprBirthdate = new Comparator<Person>() {
        @Override
        public int compare(Person o1, Person o2) {
            String id1 = o1.getBirthdate().toUpperCase();
            String id2 = o2.getBirthdate().toUpperCase();

            //ascending order
            return id1.compareTo(id2);
        }
    };
    /**
     * Comparator method: Gender.
     */
    public static Comparator<Person> CprGender = new Comparator<Person>() {
        @Override
        public int compare(Person o1, Person o2) {
            String id1 = o1.getGender().toUpperCase();
            String id2 = o2.getGender().toUpperCase();

            //ascending order
            return id1.compareTo(id2);
        }
    };
    /**
     * Comparator method: Birthplace.
     */
    public static Comparator<Person> CprBirthplace = new Comparator<Person>() {
        @Override
        public int compare(Person o1, Person o2) {
            String id1 = o1.getBirthplace().toUpperCase();
            String id2 = o2.getBirthplace().toUpperCase();

            //ascending order
            return id1.compareTo(id2);
        }
    };
    /**
     * Comparator method: Home.
     */
    public static Comparator<Person> CprHome = new Comparator<Person>() {
        @Override
        public int compare(Person o1, Person o2) {
            String id1 = o1.getHome().toUpperCase();
            String id2 = o2.getHome().toUpperCase();

            //ascending order
            return id1.compareTo(id2);
        }
    };
    /**
     * Comparator method: Groupcode.
     */
    public static Comparator<Person> CprGroupcode = new Comparator<Person>() {
        @Override
        public int compare(Person o1, Person o2) {
            String id1 = o1.getGroupcode().toUpperCase();
            String id2 = o2.getGroupcode().toUpperCase();

            //ascending order
            return id1.compareTo(id2);
        }
    };

}

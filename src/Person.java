/**
 * Person has a lot of data/information and can have relations with other Person's on the Social Network.
 * This project is being developed on Data Structures and Algorithms subject on UPV/EHU at 2020/2021 academic year.
 *
 * @author Iyán Álvarez
 * @version firstIyan
 */
public class Person {

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

    /**
     * Creates a Person with the specified data.
     * @param identifier User ID's for the Social Network
     * @param name Person's name.
     * @param surname Person's surname.
     * @param birthdate Person's birthdate in dd-mm-year format.
     * @param gender Person's gender.
     * @param birthplace Person's birthplace.
     * @param home Person's home.
     * @param studydata Person's study data.
     * @param workdata Person's work data.
     * @param movies Person's favourite movies.
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

    /**
     * Returns the basic information of a Person in the format: idperson,name,lastname,birthdate,gender,birthplace,home,studiedat,workplaces,films,groupcode
     * @return The basic information of a Person in the specified format.
     */
    @Override public String toString() {
        String studydataS = "";
        for(int i = 0; i < studydata.length; i++) {
            studydataS += studydata[i];
        }
        String workdataS = "";
        for(int i = 0; i < workdata.length; i++) {
            workdataS += workdata[i];
        }
        String moviesS = "";
        for(int i = 0; i < movies.length; i++) {
            moviesS += movies[i];
        }
        return  identifier + "," + name + "," + surname + "," + birthdate + "," + gender + "," +
                birthplace + "," + home + "," + studydataS + "," + workdataS +
                "," + moviesS + "," + groupcode;
    }
}

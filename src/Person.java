import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

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

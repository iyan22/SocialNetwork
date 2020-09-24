import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * SocialNetwork contains people information and the relations between them if exist.
 * This project is being developed on Data Structures and Algorithms subject on UPV/EHU at 2020/2021 academic year.
 *
 * @author Iyán Álvarez
 * @version firstIyan
 */
public class SocialNetwork {

    // Attributes
    private ArrayList<Person> personList;
    private ArrayList<String> relationList1;
    private ArrayList<String> relationList2;
    private static SocialNetwork instance;

    // Constructors
    /**
     * Creates an instance of Social Network.
     */
    private SocialNetwork() {
        this.personList = new ArrayList<>();
        this.relationList1 = new ArrayList<>();
        this.relationList2 = new ArrayList<>();
    }

    // Methods
    /**
     * Gets instance of the Social Network, if it does not exist it creates a new one, that is going to be unique
     * and returns it.
     * This method is based on the Singleton design pattern.
     * @return The instance of the Social Network.
     */
    public static SocialNetwork getInstance() {
        if (instance == null) {
            instance = new SocialNetwork();
        }
        return instance;
    }

    // 1st milestone
    /**
     * Presents an initial menu with the different choices for interacting with the social network.
     */
    public void initialMenu() {
        printInitialMenu();
        selectionInitialMenu();
    }
    /**
     * Prints the choices of the initial menu.
     */
    private void printInitialMenu() {
        System.out.println( "Social Network menu: \n" +
                            "1. Load 'people' into the network ... \n" +
                            "2. Load 'relationships'...\n" +
                            "3. Print out people to console ... \n" +
                            "4. Print out people to file ... \n" +
                            "5. Log out \n \n");
        System.out.print("Select one option: ");
    }
    /**
     * Request user to select an option and do what was specified in the menu, if selected option does not
     * exist, will call the program recursively.
     */
    private void selectionInitialMenu() {
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();
        String fn;
        switch (option){
            case 1:
                System.out.print("Write the name of the file: " );
                fn = sc.next();
                System.out.print("\nLoading 'people' into the network ... ");
                addPeopleFromFile(fn);
                System.out.print("completed.\n\n");
                break;
            case 2:
                System.out.print("Write the name of the file: " );
                fn = sc.next();
                System.out.print("\nLoading 'realtions' ... ");
                addRelationsFromFile(fn);
                System.out.print("completed.\n\n");
                break;
            case 3:
                System.out.println("\nPrinting out people to console: \n" );
                printPeopleToConsole();
                System.out.println("\nCompleted.\n\n");
                break;
            case 4:
                System.out.print("Write the name of the file: " );
                fn = sc.next();
                System.out.print("\nPrinting out people to file ... " );
                printPeopleToFile(fn);
                System.out.print("completed.\n\n");
                break;
            case 5:
                System.out.println("Logging out...");
                break;
            default:
                break;
        }
        if (option != 5) {
            printInitialMenu();
            selectionInitialMenu();
        }
    }
    /**
     * Adds a person to the Social Netowork.
     * @param data Data of the person.
     *             Must follow the specified format: idperson,name,lastname,birthdate,gender,birthplace,home,studiedat,workplaces,films,groupcode
     */
    private void addPerson(String data) {
        String[] d = data.split(",");
        String[] d7 = d[7].split(";");
        String[] d8 = d[8].split(";");
        String[] d9 = d[9].split(";");
        Person p = new Person(d[0], d[1], d[2], d[3], d[4], d[5], d[6], d7, d8, d9, d[10]);
        personList.add(p);
    }
    /**
     * Adds all the people from the file to the Social Network.
     * The file must be on files/ directory.
     * The file must follow the specified format: idperson,name,lastname,birthdate,gender,birthplace,home,studiedat,workplaces,films,groupcode
     * @param filename Name of the file that contains people that is going to be added to the Social Network.
     */
    private void addPeopleFromFile(String filename) {
        File f = new File("files/" + filename);
        try {
            Scanner sf = new Scanner(f);
            String sexample = "idperson,name,lastname,birthdate,gender,birthplace,home,studiedat,workplaces,films,groupcode";
            if (sexample.equals(sf.nextLine())) {
                while (sf.hasNextLine()) {
                    addPerson(sf.nextLine());
                }
            }
            else {
                System.err.println("Error: File data is not in required format");
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error: File was not found");
        }
    }
    /**
     * Prints all the people at the Social Network to the console.
     */
    private void printPeopleToConsole() {
        for (Person p: personList) {
            System.out.println(p.toString());
        }
    }
    /**
     * Prints all the people at the Social Network to a file.
     * The file will be located on files/ directory with the specified name.
     * @param filename Name of the file that is going to contain all the people in the Social Network.
     */
    private void printPeopleToFile(String filename) {
        File f = new File("files/" + filename);
        FileWriter fw = null;
        try {
            fw = new FileWriter(f);
            String sexample = "idperson,name,lastname,birthdate,gender,birthplace,home,studiedat,workplaces,films,groupcode\n";
            fw.write(sexample);
            for (Person p: personList) {
                fw.write(p.toString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.err.println("Error: File was not found");
        }

    }
    /**
     * Adds a relation of 2 people.
     * @param p1 User ID of one person.
     * @param p2 User ID of the other person.
     */
    private void addRelation(String p1, String p2) {
        this.relationList1.add(p1);
        this.relationList2.add(p2);
    }
    /**
     * Adds all the relations that the specified file contains.
     * The file must be on files/ directory.
     * The file must follow the specified format: friend1,friend2
     * @param filename Name of the file that contains relations that are going to be added to the Social Network.
     */
    private void addRelationsFromFile(String filename) {
        File f = new File("files/" + filename);
        try {
            Scanner sf = new Scanner(f);
            String sexample = "friend1,friend2";
            if (sexample.equals(sf.nextLine())) {
                String arr[];
                while (sf.hasNextLine()) {
                    arr = sf.nextLine().split(",");
                    addRelation(arr[0], arr[1]);
                }
            }
            else {
                System.err.println("Error: File data is not in required format");
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error: File was not found");
        }
    }

    // 2nd milestone


    // 3rd milestone


}

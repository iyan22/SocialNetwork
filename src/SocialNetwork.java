import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class SocialNetwork {

    // Attributes
    private ArrayList<Person> personList;

    // Constructors
    public SocialNetwork() {
        this.personList = new ArrayList<>();
    }

    // Methods
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
     * exist, wil call the program recursively.
     */
    private void selectionInitialMenu() {
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();
        String fn;
        switch (option){
            case 1:
                System.out.println("Write the name of the file: " );
                fn = sc.next();
                System.out.print("\nLoading 'people' into the network ... ");
                addPeopleFromFile(fn);
                System.out.print("completed.\n\n");
                break;
            case 2:
                System.out.print("\nLoading 'realtions' ... ");

                System.out.print("completed.\n\n");
                break;
            case 3:
                System.out.println("Printing out people to console: \n" );
                printPeopleToConsole();
                System.out.println("\nCompleted.\n\n");
                break;
            case 4:
                System.out.println("Write the name of the file: " );
                fn = sc.next();
                System.out.print("Printing out people to file ... " );
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

    public void addPerson(String data) {
        String[] d = data.split(",");
        String[] d7 = d[7].split(";");
        String[] d8 = d[8].split(";");
        String[] d9 = d[9].split(";");
        Person p = new Person(d[0], d[1], d[2], d[3], d[4], d[5], d[6], d7, d8, d9, d[10]);
        personList.add(p);
    }

    public void addPeopleFromFile(String filename) {
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

    public void printPeopleToConsole() {
        for (Person p: personList) {
            System.out.println(p.toString());
        }
    }

    public void printPeopleToFile(String filename) {
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

    public static void main(String[] args) {
        SocialNetwork sn = new SocialNetwork();
        sn.initialMenu();
    }
}

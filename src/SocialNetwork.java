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
                            "3. Print out people \n" +
                            "4. Search ... \n" +
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
        switch (option){
            case 1:
                System.out.println("Option 1 correct");
                break;
            case 2:
                System.out.println("Option 2 correct");
                break;
            case 3:
                System.out.println("Option 3 correct");
                break;
            case 4:
                System.out.println("Option 4 correct");
                break;
            case 5:
                System.out.println("Option 5 correct");
                break;
            default:
                printInitialMenu();
                selectionInitialMenu();
                break;
        }
    }

    public static void main(String[] args) {
        SocialNetwork sn = new SocialNetwork();
        sn.initialMenu();
    }
}

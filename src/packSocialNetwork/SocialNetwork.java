package packSocialNetwork;

import packSocialNetworkExceptions.PersonAlreadyAtSocialNetwork;
import packSocialNetworkExceptions.PersonNotFoundException;
import packSocialNetworkExceptions.RelationshipAlreadyAtSocialNetwork;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
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
    private ArrayList<Relationship> relationList;
    private static SocialNetwork instance;
    private final Scanner sc = new Scanner(System.in);

    // Constructors
    /**
     * Creates an instance of Social Network.
     */
    private SocialNetwork() {
        this.personList = new ArrayList<>();
        this.relationList = new ArrayList<>();
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
        System.out.println("Social Network menu:");
        printAddPersonPeople();
        printAddRelationships();
        printPrintOut();
        printFind();
        System.out.println("5. Log out \n \n");
    }
    private void printAddPersonPeople() {
        System.out.println(
                        "1. Add person or people: \n" +
                        "    - Manually \n" +
                        "    - File \n");
    }
    private void printAddRelationships() {
        System.out.println(
                        "2. Add relationships: \n" +
                        "    - Manually \n" +
                        "    - File \n");
    }
    private void printPrintOut() {
        System.out.println(
                        "3. Print out people: \n" +
                        "    - Console \n" +
                        "    - File \n");
    }
    private void printFind() {
        System.out.println(
                        "4. Find: \n" +
                        "    - Friends by Surname \n" +
                        "    - City \n" +
                        "    - Dates \n" +
                        "    - Movies \n" +
                        "    - Chain \n" +
                        "    - Cliques \n");
    }
    private void printFindFriendsBySurname() {
        System.out.println("Find friends by surname: ");


    }
    /**
     * Request user to select an option and do what was specified in the menu, if selected option does not
     * exist, will call the program recursively.
     */
    private void selectionInitialMenu() {
        String fn;
        String fna;
        int option;
        do {
            option = askForSelectionInput();
            System.out.println("You have selected: ");
            switch (option) {
                case 1:
                    addPersonPeopleSelected();
                    break;
                case 2:
                    addRelationshipsSelected();
                    break;
                case 3:
                    printOutSelected();
                    break;
                case 4:
                    System.out.print("Write the name of the file: ");
                    fn = sc.next();
                    System.out.print("\nPrinting out people to file ... ");
                    printPeopleToFile(fn);
                    System.out.print("completed.\n\n");
                    break;
                case 5:
                    System.out.println("Logging out...");
                    break;
                    /*
                    System.out.print("Write the surname: ");
                    fna = sc.next();
                    fn = "Input";
                    while (!((fn.equals("C")) | (fn.equals("F")))) {
                        System.out.print("Print to the console (C) or to a file (F): ");
                        fn = sc.next();
                    }
                    if (fn.equals("C")) {
                        printFriendsBySurnameToConsole(fna);
                    } else {
                        System.out.print("Write the name of the file: ");
                        fn = sc.next();
                        printFriendsBySurnameToFile(fna, fn);
                    }
                    break;
                case 6:
                    System.out.print("Write the city: ");
                    fna = sc.next();
                    fn = "Input";
                    while (!((fn.equals("C")) | (fn.equals("F")))) {
                        System.out.print("Print to the console (C) or to a file (F): ");
                        fn = sc.next();
                    }
                    if (fn.equals("C")) {
                        printPeopleBornInCityToConsole(fna);
                    } else {
                        System.out.print("Write the name of the file: ");
                        fn = sc.next();
                        printPeopleBornInCityToFile(fna, fn);
                    }
                    break;
                case 7:
                    System.out.println("Logging out...");
                    break;
                     */
                default:
                    break;
            }
            if (option != 5) printInitialMenu();
        } while (option != 5);
        sc.close();
    }
    /**
     * Request user for an input, it has to be a number and treats InputMismatchException.
     * @return Returns user input int.
     */
    private int askForSelectionInput() {
        boolean correct = false;
        Scanner sc = new Scanner(System.in);
        int option = 0;
        while (!correct) {
            try {
                System.out.print("Select one option: ");
                option = sc.nextInt();
                correct = true;
            } catch (InputMismatchException e) {
                System.err.println("Error: You should introduce a number");
                sc.nextLine();
            }
        }
        return option;
    }
    private void addPersonPeopleSelected() {
        printAddPersonPeople();
        String to;
        do {
            System.out.println("Manually (M) or File (F)");
            System.out.print("\nEnter M or F: ");
            to = sc.next();
        } while (!(to.equals("M") || to.equals("F")));
        if (to.equals("M")) {
            System.out.println("Follow the next format:");
            System.out.println("idperson,name,lastname,birthdate,gender,birthplace,home,studiedat,workplaces,films,groupcode");
            try {
                addPerson(sc.next());
            } catch (PersonAlreadyAtSocialNetwork e) {
                System.out.println("That person is already at the network");
            }
        }
        else {
            System.out.println("The file must be on 'files/' directory.");
            System.out.print("Enter the name of the file: ");
            addPeopleFromFile(sc.next());
            System.out.print("Completed.\n\n");
        }
    }
    private void addRelationshipsSelected() {
        printAddRelationships();
        String to;
        do {
            System.out.println("Manually (M) or File (F)");
            System.out.print("\nEnter M or F: ");
            to = sc.next();
        } while (!(to.equals("M") || to.equals("F")));
        if (to.equals("M")) {
            System.out.println("Follow the next format:");
            System.out.println("friend1,friend2");
            try {
                String s = sc.next();
                String[] sa = s.split(",");
                addRelation(sa[0], sa[1]);
            } catch (RelationshipAlreadyAtSocialNetwork e) {
                System.out.println("That relationship is already at the network");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("There was an error with your input");
            }
        }
        else {
            System.out.println("The file must be on 'files/' directory.");
            System.out.print("Enter the name of the file: ");
            addRelationsFromFile(sc.next());
            System.out.print("Completed.\n\n");
        }
    }
    private void printOutSelected() {
        printPrintOut();
        String to;
        do {
            System.out.println("Console (C) or File (F)");
            System.out.print("\nEnter C or F: ");
            to = sc.next();
        } while (!(to.equals("C") || to.equals("F")));
        if (to.equals("C")) {
            printPeopleToConsole();
        }
        else {
            System.out.println("The file must be on 'files/' directory.");
            System.out.print("Enter the name of the file: ");
            printPeopleToFile(sc.next());
        }
    }


    /**
     * Adds a person to the Social Netowork.
     * @param data Data of the person.
     *             Must follow the specified format: idperson,name,lastname,birthdate,gender,birthplace,home,studiedat,workplaces,films,groupcode
     * @throws PersonAlreadyAtSocialNetwork If the person's ID already exists in the Social Network.
     */
    private void addPerson(String data) throws PersonAlreadyAtSocialNetwork {
        String[] d = data.split(",");
        for (Person p: personList) {
            if (p.getIdentifier().equals(d[0])) {
                throw new PersonAlreadyAtSocialNetwork();
            }
        }
        try {
            String[] d7 = d[7].split(";");
            String[] d8 = d[8].split(";");
            String[] d9 = d[9].split(";");
            Person p = new Person(d[0], d[1], d[2], d[3], d[4], d[5], d[6], d7, d8, d9, d[10]);
            personList.add(p);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("There was an error with your input");
        }
    }
    /**
     * Adds all the people from the file to the Social Network.
     * The file must be on files/ directory.
     * The file must follow the specified format: idperson,name,lastname,birthdate,gender,birthplace,home,studiedat,workplaces,films,groupcode
     * @param filename Name of the file that contains people that is going to be added to the Social Network.
     */
    private void addPeopleFromFile(String filename) {
        int error = 0;
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
                System.out.println("Error: File data is not in required format");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: File was not found");
        } catch (PersonAlreadyAtSocialNetwork e) {
            error++;
        }
        if (error > 0) {
            System.err.println("Error: " + error + " users couldn't be added to the Social Network because the user ID already exists");
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
    private void addRelation(String p1, String p2) throws RelationshipAlreadyAtSocialNetwork, PersonNotFoundException {
        if (existsInSocialNetwork(p1) && existsInSocialNetwork(p2)) {
            Relationship r = new Relationship(p1, p2);
            if (relationList.contains(r)) throw new RelationshipAlreadyAtSocialNetwork();
            else relationList.add(r);
        }
        else throw new PersonNotFoundException();
    }
    /**
     * Adds all the relations that the specified file contains.
     * The file must be on files/ directory.
     * The file must follow the specified format: friend1,friend2
     * @param filename Name of the file that contains relations that are going to be added to the Social Network.
     */
    private void addRelationsFromFile(String filename) {
        File f = new File("files/" + filename);
        int al = 0;
        int nf = 0;
        try {
            Scanner sf = new Scanner(f);
            String sexample = "friend1,friend2";
            if (sexample.equals(sf.nextLine())) {
                String[] arr;
                while (sf.hasNextLine()) {
                    arr = sf.nextLine().split(",");
                    addRelation(arr[0], arr[1]);
                }
            }
            else {
                System.err.println("Error: File data is not in required format");
            }
        } catch (RelationshipAlreadyAtSocialNetwork e) {
            al++;
        } catch (PersonNotFoundException e) {
            nf++;
        } catch (FileNotFoundException e) {
            System.err.println("Error: File was not found");
        }
        if (al > 0) System.out.println("Error: " + al + "realtionship(s) were already at the network");
        if (nf > 0) System.out.println("Error: " + nf + "realtionship(s) couldn't be added; at least one person was not found");
    }

    private boolean existsInSocialNetwork(String ID) {
        int i = 0;
        if (personList.isEmpty()) return false;
        while (i < personList.size()) {
            if (personList.get(i).getIdentifier().equals(ID)) return true;
        }
        return false;
    }

    // 2nd milestone

    // 3rd milestone


}

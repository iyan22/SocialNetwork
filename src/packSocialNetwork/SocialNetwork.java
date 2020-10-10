package packSocialNetwork;

import packSocialNetworkExceptions.PersonAlreadyAtSocialNetwork;
import packSocialNetworkExceptions.PersonNotFoundException;
import packSocialNetworkExceptions.RelationAlreadyAtSocialNetwork;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

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
    private ArrayList<Relation> relationList;
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
        System.out.println("\nSocial Network menu:");
        printAddPersonPeople();
        printAddRelations();
        printPrintOut();
        printFind();
        System.out.println("5. Log out \n \n");
    }
    private void printAddPersonPeople() {
        System.out.println(
                        "1. Add person or people: \n" +
                        "    M. Manually \n" +
                        "    F. File ");
    }
    private void printAddRelations() {
        System.out.println(
                        "2. Add relation(s): \n" +
                        "    M. Manually \n" +
                        "    F. File ");
    }
    private void printPrintOut() {
        System.out.println(
                        "3. Print out people: \n" +
                        "    C. Console \n" +
                        "    F. File ");
    }
    private void printFind() {
        System.out.println(
                        "4. Find: \n" +
                        "    1. Friends of the person given the surname \n" +
                        "    2. People born in specified city \n" +
                        "    3. People born between two dates \n" +
                        "    4. Movies \n" +
                        "    5. Chain \n" +
                        "    6. Cliques ");
    }
    /**
     * Request user to select an option and do what was specified in the menu, if selected option does not
     * exist, will call the program recursively.
     */
    private void selectionInitialMenu() {
        int option;
        do {
            option = askForSelectionInput();
            System.out.println("You have selected: ");
            switch (option) {
                case 1:
                    addPersonPeopleSelected();
                    break;
                case 2:
                    addRelationsSelected();
                    break;
                case 3:
                    printOutSelected();
                    break;
                case 4:
                    printFindSelected();
                    break;
                case 5:
                    System.out.println("Logging out...");
                    break;
                default:
                    break;
            }
            if (option != 5) {
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) { }
                printInitialMenu();
            }
        } while (option != 5);
        sc.close();
    }
    /**
     * Request user for an input, it has to be a number and treats InputMismatchException.
     * @return Returns user input int.
     */
    private int askForSelectionInput() {
        boolean correct = false;
        int option = 0;
        while (!correct) {
            try {
                System.out.print("Select one option: ");
                option = sc.nextInt();
                correct = true;
            } catch (InputMismatchException e) {
                System.out.println("Error: You should introduce a number");
                sc.nextLine();
            }
        }
        return option;
    }
    /**
     * Prints Add person option and performs task.
     */
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
    /**
     * Prints Add relation option and performs task.
     */
    private void addRelationsSelected() {
        printAddRelations();
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
            } catch (RelationAlreadyAtSocialNetwork e) {
                System.out.println("Error: That relation is already at the network");
            } catch (PersonNotFoundException e) {
                System.out.println("Error: The realtion couldn't be added; at least one ID was not found on SocialNetwork");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Error: There was an error with your input");
            }
        }
        else {
            System.out.println("The file must be on 'files/' directory.");
            System.out.print("Enter the name of the file: ");
            addRelationsFromFile(sc.next());
            System.out.print("Completed.\n\n");
        }
    }
    /**
     * Prints Print out option and performs task.
     */
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
     * Prints Find option and performs task.
     */
    private void printFindSelected() {
        printFind();
        int to = 0;
        do {
            System.out.println("Select one of the previous options (1-6)");
            System.out.print("\nEnter 1, 2, 3, 4, 5, 6: ");
            try {
                to = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Error: Introduce a number from 1 to 6 (both included)");
            }
        } while (to < 1 && 6 < to);
        switch (to) {
            case 1:
                System.out.println("You have selected: ");
                System.out.println( "1. Friends of the person given the surname \n" +
                                    "    C. Console \n" +
                                    "    F. File ");
                String tos;
                do {
                    System.out.println("Console (C) or File (F)");
                    System.out.print("\nEnter C or F: ");
                    tos = sc.next();
                } while (!(tos.equals("C") || tos.equals("F")));
                System.out.print("Write the surname: ");
                String sn = sc.next();
                if (tos.equals("C")) printFriendsBySurnameToConsole(sn);
                else {
                    System.out.println("The file will be on 'files/' directory.");
                    System.out.print("Enter the name of the file: ");
                    printFriendsBySurnameToFile(sn, sc.next());

                }

                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
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
            System.out.println("There was an error with your input\n");
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
        File f;
        Scanner sf;
        try {
            f = new File("files/" + filename);
            sf = new Scanner(f);
            String sexample = "idperson,name,lastname,birthdate,gender,birthplace,home,studiedat,workplaces,films,groupcode";
            if (sexample.equals(sf.nextLine())) {
                while (sf.hasNextLine()) {
                    try {
                        addPerson(sf.nextLine());
                    } catch (PersonAlreadyAtSocialNetwork e) {
                        error++;
                    }
                }
                sf.close();
            } else {
                System.out.println("Error: File data is not in required format");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: File can not be found");
        }
        if (error > 0) {
            System.out.println("Error: " + error + " users couldn't be added to the Social Network because the user ID already exists\n");
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
    private void addRelation(String p1, String p2) throws RelationAlreadyAtSocialNetwork, PersonNotFoundException {
        if (existsInSocialNetwork(p1) && existsInSocialNetwork(p2)) {
            Relation r = new Relation(p1, p2);
            if (relationList.contains(r)) throw new RelationAlreadyAtSocialNetwork();
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
        File f;
        Scanner sf;
        int al = 0;
        int nf = 0;
        try {
            f = new File("files/" + filename);
            sf = new Scanner(f);
            String sexample = "friend1,friend2";
            if (sexample.equals(sf.nextLine())) {
                String[] arr;
                while (sf.hasNextLine()) {
                    try {
                        arr = sf.nextLine().split(",");
                        addRelation(arr[0], arr[1]);
                    } catch (RelationAlreadyAtSocialNetwork e) {
                        al++;
                    } catch (PersonNotFoundException e) {
                        nf++;
                    }
                }
                sf.close();
            }
            else {
                System.out.println("Error: File data is not in required format");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: File can not be found");
        }
        if (al > 0) System.out.println("Error: " + al + " realtionship(s) already at the network");
        if (nf > 0) System.out.println("Error: " + nf + " realtionship(s) couldn't be added; at least one ID was not found on SocialNetwork");
    }
    /**
     * Checks if one person exists in the social network given the ID (identifier).
     * @param ID ID of the person you wan to check if exits in the social network.
     * @return true  iif specified ID is associated to a person of the social network; otherwise false.
     */
    private boolean existsInSocialNetwork(String ID) {
        int i = 0;
        if (personList.isEmpty()) return false;
        while (i < personList.size()) {
            if (personList.get(i).getIdentifier().equals(ID)) return true;
            i++;
        }
        return false;
    }

    // 2nd milestone
    /**
     * Given an ID, returns the Person instance with the specified ID.
     * @param identifier ID of the Person that we want.
     * @return Person that has the given ID.
     * @throws PersonNotFoundException If no one in the SocialNetwork has that ID.
     */
    private Person findPersonByID(String identifier) throws PersonNotFoundException {
        ArrayList<Person> arr = new ArrayList<>();
        for (Person p: personList) {
            if (p.getIdentifier().equals(identifier)) {
                return p;
            }
        }
        throw new PersonNotFoundException();
    }
    /**
     * Given a surname, finds the Person(s) in the SocialNetwork with that surname and returns them in an
     * ArrayList of Person.
     * @param surname Surname of the person(s) that we want to find.
     * @return ArrayList of Persons in the SocialNetwork with that surname.
     * @throws PersonNotFoundException If no one in the SocialNetwork has that surname.
     */
    private ArrayList<Person> findPersonBySurname(String surname) throws PersonNotFoundException {
        ArrayList<Person> arr = new ArrayList<>();
        for (Person p: personList) {
            if (p.getSurname().equals(surname)) {
                arr.add(p);
            }
        }
        if (arr.isEmpty()) {
            throw new PersonNotFoundException();
        }
        return arr;
    }
    /**
     * Given a surname, returns a String with the friends of the user(s) with that surname.
     * @param surname Surname of the person(s) that we want to know his/her friends.
     * @return A String with the friends of the user(s) with that surname.
     * @throws PersonNotFoundException If no one in the SocialNetwork has that surname.
     */
    private String findFriendsBySurname(String surname) throws PersonNotFoundException {
        String s = "";
        ArrayList<Person> arr = findPersonBySurname(surname);
        String id;
        for (Person p: arr) {
            id = p.getIdentifier();
            s += "The user " + id + " surname is " + surname + "\nList of the friends: \n";
            for (Relation r: relationList) {
                if (r.getPerson1().equals(id)) {
                    s += findPersonByID(r.getPerson2()).getBasicInfo() + "\n";
                }
                else if (r.getPerson2().equals(id)) {
                    s += findPersonByID(r.getPerson1()).getBasicInfo() + "\n";
                }
            }
            s += "\n";
        }
        return s;
    }
    /**
     * Given a surname, prints the friends of the user(s) with that surname in the console.
     * @param surname Surname of the person(s) that we want to know his/her friends.
     */
    private void printFriendsBySurnameToConsole(String surname) {
        try {
            System.out.println(findFriendsBySurname(surname));
        } catch (PersonNotFoundException e) {
            System.out.println("Error: Does not exist no one with that surname in the SocialNetwork");
        }
    }
    /**
     * Given a surname, prints the friends of the user(s) with that surname in the specified file.
     * @param surname Surname of the person(s) that we want to know his/her friends.
     * @param filename File where we want to save the information.
     */
    private void printFriendsBySurnameToFile(String surname, String filename) {
        File f;
        FileWriter fw;
        String s =  "";
        try {
            f = new File("files/" + filename);
            fw = new FileWriter(f);
            s += findFriendsBySurname(surname);
            fw.write(s);
            fw.close();
        } catch (IOException e) {
            System.out.println("Error: File was not found");
        } catch (PersonNotFoundException e) {
            System.out.println("Error: Does not exist no one with that surname in the SocialNetwork");
        }
    }




    // 3rd milestone


}

package packSocialNetwork;

import packSocialNetworkExceptions.PersonAlreadyAtSocialNetwork;
import packSocialNetworkExceptions.PersonNotFoundException;

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
                            "5. Find friends given the surname ... \n" +
                            "6. Find people given the birthplace ... \n" +
                            "7. Log out \n \n");
    }
    /**
     * Request user to select an option and do what was specified in the menu, if selected option does not
     * exist, will call the program recursively.
     */
    private void selectionInitialMenu() {
        Scanner sc = new Scanner(System.in);
        String fn;
        String fna;
        int option;
        do {
            option = askForSelectionInput();
            switch (option) {
                case 1:
                    System.out.print("Write the name of the file: ");
                    fn = sc.next();
                    System.out.print("\nLoading 'people' into the network ... ");
                    addPeopleFromFile(fn);
                    System.out.print("completed.\n\n");
                    break;
                case 2:
                    System.out.print("Write the name of the file: ");
                    fn = sc.next();
                    System.out.print("\nLoading 'realtions' ... ");
                    addRelationsFromFile(fn);
                    System.out.print("completed.\n\n");
                    break;
                case 3:
                    System.out.println("\nPrinting out people to console: \n");
                    printPeopleToConsole();
                    System.out.println("\nCompleted.\n\n");
                    break;
                case 4:
                    System.out.print("Write the name of the file: ");
                    fn = sc.next();
                    System.out.print("\nPrinting out people to file ... ");
                    printPeopleToFile(fn);
                    System.out.print("completed.\n\n");
                    break;
                case 5:
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
                default:
                    break;
            }
        } while (option != 7);
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
                System.err.println("Error: File data is not in required format");
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error: File was not found");
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
                String[] arr;
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
    // TODO Check if relation already exists in the Social Network

    // 2nd milestone
    /**
     * Given a surname, prints the friends of the user(s) with that surname in the console.
     * @param surname Surname of the person(s) that we want to know his/her friends.
     */
    private void printFriendsBySurnameToConsole(String surname) {
        try {
            System.out.println(findFriendsBySurname(surname));
        } catch (PersonNotFoundException e) {
            System.err.println("Error: A Person does not exist in the Social Network");
        }
    }
    /**
     * Given a surname, prints the friends of the user(s) with that surname in the specified file.
     * @param surname Surname of the person(s) that we want to know his/her friends.
     * @param filename File where we want to save the information.
     */
    private void printFriendsBySurnameToFile(String surname, String filename) {
        File f = new File("files/" + filename);
        FileWriter fw = null;
        try {
            fw = new FileWriter(f);
            fw.write(findFriendsBySurname(surname));
            fw.close();
        } catch (IOException e) {
            System.err.println("Error: File was not found");
        } catch (PersonNotFoundException e) {
            System.err.println("Error: A Person does not exist in the Social Network");
        }
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
            for(int i = 0; i < relationList1.size(); i++) { // Always relationList1.size() == relationList2.size()
                if(relationList1.get(i).equals(id)) {
                    s += findPersonByID(relationList2.get(i)).getBasicInfo() + "\n";
                }
                else if (relationList2.get(i).equals(id)) {
                    s += findPersonByID(relationList1.get(i)).getBasicInfo() + "\n";
                }
            }
            s += "\n";
        }
        return s;
    }
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
     * Given a city, prints in the console the person(s) basic information of the ones that were born in the given city.
     * @param city Birthplace of the person(s) that we want to find.
     */
    private void printPeopleBornInCityToConsole(String city) {
        try {
            System.out.println(findPeopleBornInCity(city));
        } catch (PersonNotFoundException e) {
            System.err.println("Error: No one in the Social Network has born there");
        }
    }
    /**
     * Given a city, prints in the specified file the person(s) basic information of the ones that were born
     * in the given city.
     * @param city Birthplace of the person(s) that we want to find.
     * @param filename File where we want to save the information.
     */
    private void printPeopleBornInCityToFile(String city, String filename) {
        File f = new File("files/" + filename);
        FileWriter fw = null;
        try {
            fw = new FileWriter(f);
            fw.write(findPeopleBornInCity(city));
            fw.close();
        } catch (IOException e) {
            System.err.println("Error: File was not found");
        } catch (PersonNotFoundException e) {
            System.err.println("Error: No one in the Social Network has born there");
        }
    }
    /**
     * Given a city, finds the person(s) basic information that were born in the given city.
     * @param city Birthplace of the person(s) that we want to find.
     * @throws PersonNotFoundException If no one in the SocialNetwork have been born in that city.
     */
    private String findPeopleBornInCity(String city) throws PersonNotFoundException {
        String s = "These are the user's born in " + city + ":\n";
        String bplace;
        int n = 0;
        for (Person p: personList) {
            bplace = p.getBirthplace();
            if (bplace.equals(city)) {
                s += p.getBasicInfo() + "\n";
                n++;
            }
        }
        if (n==0) {
            throw new PersonNotFoundException();
        }
        else {
            return s;
        }
    }
    // TODO Implement search function that has all the different searches

    // 3rd milestone


}

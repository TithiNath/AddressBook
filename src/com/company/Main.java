package com.company;

import java.io.*;
import java.util.*;

public class Main {
    private static Scanner in = new Scanner(System.in);
    static List<String> files = new ArrayList<>();
    static Map<String, List<Person>> myMaps = new HashMap<String, List<Person>>();
    public static void main(String[] args) throws IOException {
        showMainMenu();
    }

    private static void findPerson(String filename) throws IOException {
        System.out.println("1. Find with name");
        System.out.println("2. Find with surname");
        System.out.println("3. Find with phone number");
        System.out.println("4. Find with email");


        String choice;
        do {
            choice = in.nextLine();
            switch (choice) {
                case "1":
                    findByName(filename);
                    break;
                case "2":
                    findBySurname(filename);
                    break;
                case "3":
                    findByPhoneNo(filename);
                    break;
                case "4":
                    findByEmail(filename);
                    break;
                default:
                    System.out.print("Choose number from 1 to 4: ");
            }
        } while (!choice.equals("1") && !choice.equals("2") && !choice.equals("3") && !choice.equals("4"));
        System.out.println();
        showMainMenu();
    }

    private static void findBySurname(String filename) {
        System.out.print("Enter surname: ");
        String surnameToFind = in.nextLine();
        List<Person> p=myMaps.get(filename);
        boolean found=false;
        Person record=null;
        ArrayList<Person> a=new ArrayList<Person>();
        for(int i=0;i<p.size();i++)
        {
            if(p.get(i).getSurname().equals(surnameToFind))
            {
                record=p.get(i);
                a.add(record);
                found=true;
            }
        }
        if(found==true)
            System.out.println(a);
        else
            System.out.println("Record not found");
    }

    private static void findByName(String filename) {
        System.out.print("Enter name: ");
        String nameToFind = in.nextLine();
        List<Person> p=myMaps.get(filename);
        boolean found=false;
        Person record=null;
        ArrayList<Person> a=new ArrayList<Person>();
        for(int i=0;i<p.size();i++)
        {
            if(p.get(i).getName().equals(nameToFind))
            {
                record=p.get(i);
                a.add(record);
                found=true;
            }
        }
        if(found==true)
            System.out.println(a);
        else
            System.out.println("Record not found");
    }
    private static void findByPhoneNo(String filename) {
        System.out.print("Enter phone number: ");
        String numberToFind = in.nextLine();
        List<Person> p=myMaps.get(filename);
        boolean found=false;
        Person record=null;
        ArrayList<Person> a=new ArrayList<Person>();
        for(int i=0;i<p.size();i++)
        {
            if(p.get(i).getPhoneNumber().equals(numberToFind))
            {
                record=p.get(i);
                a.add(record);
                found=true;
            }
        }
        if(found==true)
            System.out.println(a);
        else
            System.out.println("Record not found");
    }
    private static void findByEmail(String filename) {
        System.out.print("Enter email: ");
        String emailToFind=in.nextLine();
        List<Person> p=myMaps.get(filename);
        boolean found=false;
        Person record=null;
        ArrayList<Person> a=new ArrayList<Person>();
        for(int i=0;i<p.size();i++)
        {
            if(p.get(i).getEmail().equals(emailToFind))
            {
                record=p.get(i);
                a.add(record);
                found=true;
            }
        }
        if(found==true)
            System.out.println(a);
        else
            System.out.println("Record not found");
    }
    private static void createFile() throws IOException {
        String fileName=in.nextLine();
        if(fileName.endsWith(".txt")) {
            File file = new File(fileName);
            if(file.exists()==false && files.contains(fileName)==false)
            {
                files.add(fileName);
                myMaps.put(fileName, new ArrayList<Person>());
                System.out.println(fileName + " created.");
                showMainMenu();
            }
            else if (file.exists() == true && files.contains(fileName)==false) {
                Scanner sc = new Scanner(file);
                myMaps.put(fileName,new ArrayList<Person>());
                if(sc.hasNextLine()) {
                    while (sc.hasNextLine()) {
                        String currLine = sc.nextLine();
                        if(currLine.length() > 1)
                            myMaps.get(fileName).add(new Person(currLine));
                    }
                }
                sc.close();
                if(files.contains(fileName)==false)
                files.add(fileName);
                System.out.println("File already exist");
                showMainMenu();
            }
            if (files.contains(fileName)) {
                System.out.println("File already exists");
                showMainMenu();
            }
        }
        else
        {
            System.out.println("Enter file name ending with '.txt'");
            showMainMenu();
        }
    }
    private static void checkEmail(String currFile,String email) throws IOException {
        boolean x=false;
        for(int i=0;i<myMaps.get(currFile).size();i++)
        {
            if(myMaps.get(currFile).get(i).getEmail().equals(email))
            {
                x=true;
                break;
            }
        }
        if(x==true)
        {
            System.out.println(email+" already exist, could not add the record");
            showMainMenu();
        }
    }
    private static void addPerson(String currFile) throws IOException {

        System.out.println("Enter name: ");
        String name = in.nextLine();
        System.out.println("Enter surname: ");
        String surname = in.nextLine();
        System.out.println("Enter phone number: ");
        String phoneNumber = in.nextLine();
        System.out.println("Enter email: ");
        String email =in.nextLine();
        checkEmail(currFile,email);
        System.out.println("Enter address: ");
        String address = in.nextLine();

        Person person = new Person(name, surname, phoneNumber, email, address);
        addToFile(person,currFile);
        List<Person> people = new ArrayList<>();
        people.add(person);
        myMaps.get(currFile).add(person);
        System.out.println("Added person number: "+ person);
        System.out.println();
        showMainMenu();
    }

    private static void addToFile(Person person,String currFile) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(currFile, true))) {
            writer.write(person.getName()+" " + person.getSurname() + " " + person.getPhoneNumber() + " " + person.getEmail() +
                    " " + person.getAddress() + "\n");

        } catch(IOException e) {
            System.out.println(e);
        }
    }
    public static void show(String fileName) throws IOException {
        System.out.println("Records in "+fileName+" :");
        System.out.println(myMaps.get(fileName));
        showMainMenu();
    }
    public static void delete(String filename,String email) throws IOException {
        List<Person> p=myMaps.get(filename);
        boolean a=false;
        Person j=null;
        for (int i = 0; i < p.size(); i++)
        {
            if((p.get(i).getEmail().equals(email)))
            {
                j=p.get(i);
                p.remove(i);
                a=true;
                break;
            }
        }
        if(a==true) {
            System.out.println("Record with email " + email + " deleted.");
            System.out.println("Do you want to update "+filename+" ?");
            System.out.println("Yes/No");
            String ch=in.nextLine().toLowerCase();

            switch(ch)
            {
                case "yes":
                    PrintWriter writer = new PrintWriter(filename);
                    writer.print("");
                    for(int i=0;i<myMaps.get(filename).size();i++)
                    {
                        Person person = new Person(myMaps.get(filename).get(i).getName() , myMaps.get(filename).get(i).getSurname(),myMaps.get(filename).get(i).getPhoneNumber(),myMaps.get(filename).get(i).getEmail(),myMaps.get(filename).get(i).getAddress());
                        addToFile(person, filename);
                    }
                    showMainMenu();
                    break;

                case "no":
                    p.add(j);
                    System.out.println("File not updated");
                    showMainMenu();
                    break;

                default:
                    System.out.println("invalid choice, could not update the file.");
                    showMainMenu();

            }
        }
        else {
            System.out.println("Record with email " + email + " not found.");
            showMainMenu();
        }
    }
    public static void sort(String filename) throws IOException {

        System.out.print("\nSort By :\n" +
                "1) FirstName | 2) LastName | 3) Email | 4) Phone No | 5) Address\n");

        char choice = in.nextLine().charAt(0);
        List<Person> sortAddressBook = new ArrayList<>();
        sortAddressBook.addAll(myMaps.get(filename));
        switch(choice){
            case '1':
                Collections.sort(sortAddressBook, new Comparator().new FirstNameComparator());
                //addressFileReader.setAddressList(sortAddressBook);
                System.out.println(sortAddressBook);
                System.out.println("\nSuccess : Sorted by 'firstName'");
                menuForSort(filename,sortAddressBook);
                showMainMenu();
                break;
            case '2':
                Collections.sort(sortAddressBook, new Comparator().new LastNameComparator());
                //addressFileReader.setAddressList(sortAddressBook);
                System.out.println(sortAddressBook);
                System.out.println("\nSorted by 'lastName'");
                menuForSort(filename,sortAddressBook);
                showMainMenu();
                break;

            case '3':
                Collections.sort(sortAddressBook, new Comparator().new EmailComparator());
                System.out.println(sortAddressBook);
                System.out.println("\nSorted by 'email'");
                menuForSort(filename,sortAddressBook);
                showMainMenu();
                break;
            case '4':
                Collections.sort(sortAddressBook, new Comparator().new PhoneComparator());
                System.out.println(sortAddressBook);
                System.out.println("\nSorted by 'PhoneNo'");
                menuForSort(filename,sortAddressBook);
                showMainMenu();
                break;
            case '5':
                Collections.sort(sortAddressBook, new Comparator().new AddressComparator());
                System.out.println(sortAddressBook);
                System.out.println("\nSorted by 'Address'.");
                menuForSort(filename,sortAddressBook);
                showMainMenu();
                break;
            default:
                System.out.println("Error : invalid choice, try again.");
        }
    }
    private static void menuForSort(String filename,List sortAddressBook) throws FileNotFoundException {
        System.out.println("Do you want to update "+filename+"?");
        System.out.println("Yes/No");
        String choice=in.nextLine();
        String ch=choice.toLowerCase();
        switch(ch)
        {
            case "yes":
                myMaps.get(filename).clear();
                myMaps.get(filename).addAll(sortAddressBook);
                PrintWriter writer = new PrintWriter(filename);
                writer.print("");
                writer.write(String.valueOf(myMaps.get(filename)));
                writer.close();
                System.out.println(filename+" updated.");
                break;
            case "no":
                System.out.println(filename+" not updated.");
            default:
                System.out.println("invalid choice, could not update the file.");
        }
    }
    private static void update(String filename) throws IOException {
        System.out.println("Enter email of the person for whom you want to update the record");
        String email=in.nextLine();
        boolean x=false;
        int y=Integer.MAX_VALUE;
        for(int i=0;i<myMaps.get(filename).size();i++)
        {
            if(myMaps.get(filename).get(i).getEmail().equals(email))
            {
                x=true;
                y=i;
            }
        }
        if(x==true) {
            System.out.println("What you want to update?");
            System.out.println("1) FirstName | 2) LastName | 3) Email | 4) Phone No | 5) Address\n");
            char choice = in.nextLine().charAt(0);
            List <Person> p=myMaps.get(filename);
            switch(choice) {
                case '1':
                    System.out.println("Enter first name:");
                    String name=in.nextLine();
                    p.get(y).setName(name);
                    PrintWriter writer = new PrintWriter(filename);
                    writer.print("");
                    for(int i=0;i<myMaps.get(filename).size();i++)
                    {
                        Person person = new Person(myMaps.get(filename).get(i).getName() , myMaps.get(filename).get(i).getSurname(),myMaps.get(filename).get(i).getPhoneNumber(),myMaps.get(filename).get(i).getEmail(),myMaps.get(filename).get(i).getAddress());
                        addToFile(person, filename);
                    }
                    System.out.println("Record updated.");
                    System.out.println(p.get(y));
                    showMainMenu();
                    break;
                case '2':
                    System.out.println("Enter last name:");
                    String lastname=in.nextLine();
                    p.get(y).setSurname(lastname);
                    PrintWriter writer1 = new PrintWriter(filename);
                    writer1.print("");
                    for(int i=0;i<myMaps.get(filename).size();i++)
                    {
                        Person person = new Person(myMaps.get(filename).get(i).getName() , myMaps.get(filename).get(i).getSurname(),myMaps.get(filename).get(i).getPhoneNumber(),myMaps.get(filename).get(i).getEmail(),myMaps.get(filename).get(i).getAddress());
                        addToFile(person, filename);
                    }
                    System.out.println("Record updated.");
                    System.out.println(p.get(y));
                    showMainMenu();
                    break;
                case '3':
                    System.out.println("Enter email:");
                    String eMail=in.nextLine();
                    boolean a=false;
                    for(int i=0;i<p.size();i++)
                    {
                        if(p.get(i).getEmail().equals(eMail))
                        {
                            a=true;
                            break;
                        }
                    }
                    if(a==true)
                    {
                        System.out.println("email already exists, could not update the record.");
                    }
                    else {
                        p.get(y).setEmail(eMail);
                        PrintWriter writer2 = new PrintWriter(filename);
                        writer2.print("");
                        for(int i=0;i<myMaps.get(filename).size();i++)
                        {
                            Person person = new Person(myMaps.get(filename).get(i).getName() , myMaps.get(filename).get(i).getSurname(),myMaps.get(filename).get(i).getPhoneNumber(),myMaps.get(filename).get(i).getEmail(),myMaps.get(filename).get(i).getAddress());
                            addToFile(person, filename);
                        }
                        System.out.println("Record updated.");
                        System.out.println(p.get(y));
                    }
                    showMainMenu();
                    break;
                case '4':
                    System.out.println("Enter phone number:");
                    String pNo=in.nextLine();
                    p.get(y).setPhoneNumber(pNo);
                    PrintWriter writer3 = new PrintWriter(filename);
                    writer3.print("");
                    for(int i=0;i<myMaps.get(filename).size();i++)
                    {
                        Person person = new Person(myMaps.get(filename).get(i).getName() , myMaps.get(filename).get(i).getSurname(),myMaps.get(filename).get(i).getPhoneNumber(),myMaps.get(filename).get(i).getEmail(),myMaps.get(filename).get(i).getAddress());
                        addToFile(person, filename);
                    }
                    System.out.println("Record updated.");
                    System.out.println(p.get(y));
                    showMainMenu();
                    break;
                case '5':
                    System.out.println("Enter address:");
                    String address=in.nextLine();
                    p.get(y).setAddress(address);
                    PrintWriter writer4 = new PrintWriter(filename);
                    writer4.print("");
                    for(int i=0;i<myMaps.get(filename).size();i++)
                    {
                        Person person = new Person(myMaps.get(filename).get(i).getName() , myMaps.get(filename).get(i).getSurname(),myMaps.get(filename).get(i).getPhoneNumber(),myMaps.get(filename).get(i).getEmail(),myMaps.get(filename).get(i).getAddress());
                        addToFile(person, filename);
                    }
                    System.out.println("Record updated.");
                    System.out.println(p.get(y));
                    showMainMenu();
                    break;
                default:
                    System.out.println("invalid choice, could not update the file.");
                    showMainMenu();
            }
        }
        else
        {
            System.out.println("There is no record having email "+email);
            showMainMenu();
        }
    }
    private static void showMainMenu() throws IOException {
        System.out.println("1. Load a file");
        System.out.println("2. Add person");
        System.out.println("3. Find person");
        System.out.println("4. Show all contacts");
        System.out.println("5. Delete a person");
        System.out.println("6. Sort the records");
        System.out.println("7. Update an existing record");
        System.out.println("8. Close program");

        String choice;
        do {
            choice = in.nextLine();
            switch (choice) {
                case "1":
                    System.out.println("Enter filename ending with .txt");
                    createFile();
                    break;
                case "2":
                    System. out. println(files);
                    System.out.println("Choose the file from the list to which you want to add the record.");
                    String currFile=in.nextLine();
                    if(files.contains(currFile)) {
                        addPerson(currFile);
                        showMainMenu();
                    }
                    else
                    {
                        System.out.println("File does not exist");
                        showMainMenu();
                    }
                    break;
                case "3":
                    System. out. println(files);
                    System.out.println("Enter file name in which record is to be searched");
                    String fileName=in.nextLine();
                    if(files.contains(fileName)) {
                        findPerson(fileName);
                        showMainMenu();
                    }
                    else
                    {
                        System.out.println("File does not exist");
                        showMainMenu();
                    }
                    break;
                case "4":
                    System. out. println(files);
                    System.out.println("Choose the file from the list to which you want to add the record.");
                    String currFileDisplay=in.nextLine();
                    if(files.contains(currFileDisplay)) {
                        show(currFileDisplay);
                        System.out.println();
                        showMainMenu();
                    }
                    else
                    {
                        System.out.println("File does not exist");
                        showMainMenu();
                    }
                    break;
                case "5":
                    System. out. println(files);
                    System.out.println("Choose the file from the list from which you want to delete record.");
                    String filename=in.nextLine();
                    if(files.contains(filename)){
                        System.out.println("Enter email of the person whose record you want to delete");
                        String email=in.nextLine();
                        delete(filename,email);
                    }
                    else
                    {
                        System.out.println("File does not exist");
                        showMainMenu();
                    }

                    break;
                case "6":
                    System. out. println(files);
                    System.out.println("Enter file name which you want to sort");
                    String filenameToSort=in.nextLine();
                    if(files.contains(filenameToSort)) {
                        sort(filenameToSort);
                    }
                    else
                    {
                        System.out.println("File does not exist");
                        showMainMenu();
                    }
                case "7":
                    System. out. println(files);
                    System.out.println("Enter file name which you want to sort");
                    String filenameToUpdate=in.nextLine();
                    if(files.contains(filenameToUpdate)) {
                        update(filenameToUpdate);
                    }
                    else
                    {
                        System.out.println("File does not exist");
                        showMainMenu();
                    }
                case "8":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Enter number from 1 to 8");
            }
        }while(!choice.equals("6"));
    }
}
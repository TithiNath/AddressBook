package com.company;
public class Person {
    //static int id = Main.people.size();
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private String address;

    Person(String name, String surname, String phoneNumber, String email, String address) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        //id++;
    }
    public Person(String line) {
        String[] l = line.split(" ");
            this.name = l[0].trim();
            this.surname = l[1].trim();
            this.phoneNumber = l[2].trim();
            this.address = l[3].trim();
            this.email = l[4].trim();

    }

    String getName() {
        return name;
    }

    String getSurname() {
        return surname;
    }

    String getPhoneNumber() {
        return phoneNumber;
    }

    String getEmail() {
        return email;
    }

    String getAddress() {
        return address;
    }
    void setName(String name)
    {
        this.name=name;
    }
    void setSurname(String lname)
    {
        this.surname=lname;
    }
    void setEmail(String email)
    {
        this.email=email;
    }
    void setPhoneNumber(String pNo)
    {
        this.phoneNumber=pNo;
    }
    void setAddress(String address)
    {
        this.address=address;
    }

    @Override
    public String toString() {
        return "\n\nName: " + getName() + "\nSurname: " + getSurname() + "\nPhone number: " + getPhoneNumber() + "\nEmail: " +
                getEmail() + "\nAddress: " + getAddress();
    }
}


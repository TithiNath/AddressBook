package com.company;

public class Comparator {
    public class FirstNameComparator implements java.util.Comparator<Person> {
        @Override
        public int compare(Person o1,Person o2) {
            return o1.getName().compareToIgnoreCase(o2.getName());
        }
    }

    public class LastNameComparator implements java.util.Comparator<Person> {
        @Override
        public int compare(Person o1, Person o2) {
            return o1.getSurname().compareToIgnoreCase(o2.getSurname());
        }
    }

    public class EmailComparator implements java.util.Comparator<Person> {
        @Override
        public int compare(Person o1, Person o2) {
            return o1.getEmail().compareToIgnoreCase(o2.getEmail());
        }
    }

    public class PhoneComparator implements java.util.Comparator<Person> {
        @Override
        public int compare(Person o1, Person o2) {
            return o1.getPhoneNumber().compareTo(o2.getPhoneNumber());
        }
    }

    public class AddressComparator implements java.util.Comparator<Person> {
        @Override
        public int compare(Person o1, Person o2) {
            return o1.getAddress().compareToIgnoreCase(o2.getAddress());
        }
    }
}

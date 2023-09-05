package dao;

import model.Person;

public interface IPersonDAO {
    //US-9
    void createPerson(String name, int age);
    //US-9
    void deletePerson(int phoneNumber);
    //US-9
    void updatePerson(String name, int age);
    // US-1 & US-8 & US-2
    Person findPerson(int phoneNumber);

}

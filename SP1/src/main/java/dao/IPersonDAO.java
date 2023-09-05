package dao;

import model.Person;

public interface IPersonDAO {
    //US-9
    void createPerson(String name, int age);
    //US-9
    void deletePerson(int id);
    //US-9
    void updatePerson(int id, String newName, int newAge);
    // US-1 & US-8 & US-2
    Person findPerson(int phoneNumber);

}

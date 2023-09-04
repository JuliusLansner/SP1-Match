package dao;

import model.Person;

public interface IPersonDAO {

    void createPerson(String name, int age);

    void deletePerson(int phoneNumber);

    void updatePerson(String name, int age);

    void findPerson(int phoneNumber);

}

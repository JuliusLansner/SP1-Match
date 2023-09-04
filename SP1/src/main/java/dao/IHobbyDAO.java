package dao;

import model.Person;

import java.util.List;

public interface IHobbyDAO {

    List<Person> personsWithGivenHobby(String name);

    int amountOfHobbyists(int id);






}

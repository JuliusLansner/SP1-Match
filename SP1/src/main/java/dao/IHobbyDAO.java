package dao;

import model.Hobby;
import model.Person;

import java.util.HashMap;
import java.util.List;

public interface IHobbyDAO {
    //US-3
    List<Person> personsWithGivenHobby(String hobbyName);

    //US-4
    Long amountOfHobbyists(String hobbyName);

    //US-5
    HashMap<Hobby, Long> allHobbiesAndInterest();
}

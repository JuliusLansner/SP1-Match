package dao;

import model.Hobby;
import model.Person;

import java.util.HashMap;
import java.util.List;

public interface IHobbyDAO {
    //US-3
    List<Person> personsWithGivenHobby(String name);
    //US-4
    int amountOfHobbyists(int id);
    //US-5
    HashMap<Hobby,Integer> allHobbiesAndInterest();






}

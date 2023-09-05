package dao;

import model.Person;

import java.util.List;

public interface IAdressDAO {
    //US-6
    List<Person> cityPersonLivingList(String cityName);
}

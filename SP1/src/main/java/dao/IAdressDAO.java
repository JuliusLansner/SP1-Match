package dao;

import model.Person;

import java.util.List;

public interface IAdressDAO {
    //US-6
    //CRUD if it makes sense
    List<Person> cityPersonLivingList(String cityName);
}

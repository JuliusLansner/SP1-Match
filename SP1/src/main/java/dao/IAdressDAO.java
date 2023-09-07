package dao;

import model.Person;
import model.ZipCode;

import java.util.List;

public interface IAdressDAO {
    //US-6
    //CRUD if it makes sense
    List<Person> cityPersonLivingList(String cityName);

    void addAddressToPerson(String streetname,int streetnumber,String name,String cityName);
    public ZipCode zipByCity(String cityName);

}

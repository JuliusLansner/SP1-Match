package dao;

import model.ZipCode;

import java.util.HashMap;
import java.util.List;

public interface IZipDAO {
    //US-7
    HashMap<ZipCode,String> allPostCodeCityNameDK();


}

package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import model.ZipCode;

import java.util.HashMap;
import java.util.List;

public class IZipDAOImpl implements IZipDAO {
    //CRUD if it makes sense
    //Follow singleton pattern
    private static EntityManagerFactory emf;
    private static IZipDAOImpl zipDAO;


    private IZipDAOImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public static IZipDAOImpl getInstance(EntityManagerFactory emf) {
        if (zipDAO == null) {
            zipDAO = new IZipDAOImpl(emf);
        }
        return zipDAO;

    }

    //US-7
    @Override
    public HashMap<ZipCode, String> allPostCodeCityNameDK() {
        try (EntityManager em = emf.createEntityManager()) {
            //zip and cityName columns will be put into an array of objects[]
            TypedQuery<Object[]> query = em.createQuery("SELECT zip,cityName FROM ZipCode", Object[].class);
            //resultList will become a list of the results from query
            List<Object[]> resultList = query.getResultList();
            //making the hashmap that we'll later return
            HashMap<ZipCode, String> resultMap = new HashMap<>();
            //for loop, checking if result is greater or equals 2, then checking if [0] is indeed from Zipcode, and if [1] is indeed from String,
            //with the use of instancof, which checks if an object is an instance of the specified type
            for (Object[] result : resultList) {

                if (result.length >= 2 && result[0] instanceof ZipCode && result[1] instanceof String) {
                    //then we put zip and cityname into zipcode and string respectively
                    resultMap.put((ZipCode) result[0], (String) result[1]);
                }
            }
            //aaaaand return resultmap
            return resultMap;
        }

    }



}

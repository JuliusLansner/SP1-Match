package dao;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import model.Adress;
import model.Person;

import java.util.List;

public class IAdressDAOImpl implements IAdressDAO {
    //CRUD if it makes sense
    //Follow singleton pattern
    private static EntityManagerFactory emf;
    private static IAdressDAOImpl adressDAO;


    private IAdressDAOImpl(EntityManagerFactory emf){
        this.emf = emf;
    }
    public static IAdressDAOImpl getInstance(EntityManagerFactory emf){
        if(adressDAO == null){
            adressDAO = new IAdressDAOImpl(emf);
        }
        return adressDAO;

    }
    @Override
    public List<Person> cityPersonLivingList(String cityName) {
        try(EntityManager em = emf.createEntityManager()){
            TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p JOIN p.adress a WHERE a.zipCode.cityName = :cityName",Person.class);
            query.setParameter("cityName",cityName);
            return query.getResultList();
        }

    }
}

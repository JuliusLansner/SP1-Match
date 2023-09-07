package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import model.Adress;
import model.Person;
import model.ZipCode;

import java.util.List;

public class IAdressDAOImpl implements IAdressDAO {
    //CRUD if it makes sense
    //Follow singleton pattern
    private static EntityManagerFactory emf;
    private static IAdressDAOImpl adressDAO;


    public IAdressDAOImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public static IAdressDAOImpl getInstance(EntityManagerFactory emf) {
        if (adressDAO == null) {
            adressDAO = new IAdressDAOImpl(emf);
        }
        return adressDAO;

    }

    //US-6
    @Override
    public List<Person> cityPersonLivingList(String cityName) {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p WHERE p.adress.zipcode.cityName = :cityName", Person.class);
            query.setParameter("cityName", cityName);
            return query.getResultList();
        }

    }

    public ZipCode zipByCity(String cityName) {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<ZipCode> query = em.createQuery("SELECT z FROM ZipCode z WHERE z.cityName = :cityName", ZipCode.class);
            query.setParameter("cityName", cityName);
            List<ZipCode> results = query.getResultList();

            return results.isEmpty() ? null : results.get(0);

        }
    }

    @Override
    public void addAddressToPerson(String streetname, int streetnumber, String name, String cityName) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Adress adress = new Adress();
            adress.setStreetName(streetname);
            adress.setStreetNumber(streetnumber);

            ZipCode zipCode = zipByCity(cityName);
            if(zipCode != null){
                adress.setZipcode(zipCode);
            }

            em.persist(adress);

            TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p WHERE p.name = :name", Person.class);
            query.setParameter("name", name);

            Person person = query.getSingleResult();
            person.setAdress(adress);


            em.getTransaction().commit();

        }


    }
}

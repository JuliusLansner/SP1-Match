package dao;

import config.HibernateConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class IAdressDAOImplTest {
    private static EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
    private static IAdressDAO adressDAO = IAdressDAOImpl.getInstance(emf);

    @BeforeEach
    void setUp() {

        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();


            em.createQuery("DELETE FROM Person").executeUpdate();
            em.createQuery("DELETE FROM ContactInfo").executeUpdate();
            em.createQuery("DELETE FROM Adress").executeUpdate();
            em.createQuery("DELETE FROM ZipCode").executeUpdate();

            em.createNativeQuery(
                    "INSERT INTO adress (adress_id,street_name,street_number) VALUES " +
                            "(1,'lmeostreet',5), " +
                            "(2,'maldevej',10), " +
                            "(4,'fuck',20), " +
                            "(3,'mikkelvej',15);").executeUpdate();
            em.createNativeQuery(
                    "INSERT INTO zipcode (adress_adress_id,zip,city_name) VALUES " +
                            "(1,1000,'hejvej'), " +
                            "(2,2000,'jehvej'), " +
                            "(4,2000,'jehvej'), " +
                            "(3,3000,'hejjev');").executeUpdate();

            em.createNativeQuery(
                    "INSERT INTO contactinfo (contactinfo_id, email, phone_number) VALUES " +
                            "(1, '@Mogens', 67543456), " +
                            "(2, '@Jørgen', 98564324), " +
                            "(6, '@asd', 11111111), " +
                            "(5, '@dsa', 22222222), " +
                            "(4, '@123', 33333333), " +
                            "(3, '@Alice', 98675434);").executeUpdate();


            em.createNativeQuery(
                    "INSERT INTO person (person_id, age, name,contactinfo_contactinfo_id,adress_adress_id) VALUES " +
                            "(1, 26, 'Mogens',1,1), " +
                            "(2, 30, 'Jørgen',2,2), " +
                            "(6, 30, 'asd',6,2), " +
                            "(5, 30, 'dsa',5,2), " +
                            "(4, 30, '123',4,2), " +
                            "(3, 22, 'Alice',3,3);").executeUpdate();








            em.getTransaction().commit();
        }
    }



    @Test
    void cityPersonLivingList() {

        List<Person> pl = adressDAO.cityPersonLivingList("jehvej");
        String yo = adressDAO.cityPersonLivingList("jehvej").toString();
        System.out.println(pl);
        System.out.println(yo);
        System.out.println(":D");
    }
}
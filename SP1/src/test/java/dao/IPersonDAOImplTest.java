package dao;

import config.HibernateConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import model.ContactInfo;
import model.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IPersonDAOImplTest {
private EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
private IPersonDAOImpl iPersonDAO = IPersonDAOImpl.getInstance(emf);
    @BeforeEach
    void setUp() {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();


            em.createQuery("DELETE FROM Person").executeUpdate();
            em.createQuery("DELETE FROM ContactInfo").executeUpdate();
            em.createQuery("DELETE FROM Adress").executeUpdate();
            em.createQuery("DELETE FROM ZipCode").executeUpdate();

            em.createNativeQuery(
                    "INSERT INTO contactinfo (contactinfo_id, email, phone_number) VALUES " +
                            "(1, '@Mogens', 67543456), " +
                            "(2, '@Jørgen', 98564324), " +
                            "(3, '@Alice', 98675434), " +
                            "(4, '@Bob', 76564587), " +
                            "(5, '@Charlie', 65467896), " +
                            "(6, '@David', 67542786), " +
                            "(7, '@Eve', 56341975)"
            ).executeUpdate();

            em.createNativeQuery(
                    "INSERT INTO person (person_id, age, name,contactinfo_contactinfo_id) VALUES " +
                            "(1, 26, 'Mogens',1), " +
                            "(2, 30, 'Jørgen',2), " +
                            "(3, 22, 'Alice',3), " +
                            "(4, 35, 'Bob',4), " +
                            "(5, 28, 'Charlie',5), " +
                            "(6, 40, 'David',6), " +
                            "(7, 27, 'Eve',7)"
            ).executeUpdate();


            em.getTransaction().commit();
        }
    }

    @Test
    void createPerson() {
       iPersonDAO.createPerson("TesterDude",25);

       try(EntityManager em = emf.createEntityManager()){

           List<Person> allPersons = em.createQuery("SELECT p FROM Person p").getResultList();
           Person person = new Person();

           for(Person p : allPersons){
               if(p.getName().equals("TesterDude")){
                   person = p;
               }
           }

           assertEquals(person.getName(),"TesterDude");
       }
    }

    @Test
    void deletePerson() {
    iPersonDAO.deletePerson(1);
    try(EntityManager em = emf.createEntityManager()){
        Person person = em.find(Person.class,1);
        assertNull(person);
    }
    }

    @Test
    void updatePerson() {
        iPersonDAO.updatePerson(1,"Mogens",27);
        try(EntityManager em = emf.createEntityManager()){
            Person person = em.find(Person.class,1);

            assertEquals(person.getAge(),27);
        }
    }

    @Test
    void findPerson() {


        try(EntityManager em = emf.createEntityManager()){

            Person foundPerson = em.find(Person.class,1);
            Person person = iPersonDAO.findPerson(67543456);

            assertEquals(person.getName(),foundPerson.getName());
        }
    }
}
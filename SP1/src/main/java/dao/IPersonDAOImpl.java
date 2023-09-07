package dao;

import config.HibernateConfig;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import model.Person;

import java.util.List;

public class IPersonDAOImpl implements IPersonDAO {
    private static EntityManagerFactory emf;
    private static IPersonDAOImpl iPersonDAO;


    private IPersonDAOImpl(EntityManagerFactory emf){
        this.emf=emf;
    }

    public static IPersonDAOImpl getInstance(EntityManagerFactory emf){
        if(iPersonDAO == null){
            iPersonDAO = new IPersonDAOImpl(emf);
        }
        return iPersonDAO;
    }
    @Override
    public void createPerson(String name, int age) {
        try(EntityManager em = emf.createEntityManager()){
            Person person = new Person(name,age);

            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();
        }
    }

    @Override
    public void deletePerson(int id) {
        try(EntityManager em = emf.createEntityManager()){
            Person person = em.find(Person.class,id);
            em.getTransaction().begin();
            em.remove(person);
            em.getTransaction().commit();
        }
    }

    @Override
    public void updatePerson(int id, String newName, int newAge) {
    try(EntityManager em = emf.createEntityManager()){
        Person person = em.find(Person.class,id);
        person.setName(newName);
        person.setAge(newAge);
        em.getTransaction().begin();
        em.persist(person);
        em.getTransaction().commit();
    }
    }

    @Override
    public Person findPerson(int phoneNumber) {
        try(EntityManager em = emf.createEntityManager()){
             return em.createQuery("SELECT p FROM Person p WHERE p.contactInfo.phoneNumber = :phone", Person.class)
                    .setParameter("phone",phoneNumber)
                    .getSingleResult();
        }
    }

}

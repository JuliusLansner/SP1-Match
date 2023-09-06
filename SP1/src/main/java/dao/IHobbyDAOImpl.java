package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import model.Hobby;
import model.Person;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class IHobbyDAOImpl implements IHobbyDAO {

    private static EntityManagerFactory emf;
    private static IHobbyDAOImpl iHobbyDAO;

    private IHobbyDAOImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public static IHobbyDAOImpl getInstance(EntityManagerFactory emf) {
        if (iHobbyDAO == null) {
            iHobbyDAO = new IHobbyDAOImpl(emf);
        }
        return iHobbyDAO;
    }

    public void createHobby(Hobby hobby) {
        try (EntityManager entityManager = emf.createEntityManager()) {
            entityManager.getTransaction().begin();
            entityManager.persist(hobby);
            entityManager.getTransaction().commit();
        }
    }

    public Hobby findHobby(String hobbyName) {
        try (EntityManager entityManager = emf.createEntityManager()) {
            TypedQuery<Hobby> query = entityManager.createQuery("SELECT h from Hobby h where h.name = :hobbyName", Hobby.class);
            query.setParameter("hobbyName", hobbyName);
            try {
                return query.getSingleResult();
            } catch (NoResultException e) {
                return null;
            }
        }
    }

    public void updateHobby(Hobby hobby) {
        try (EntityManager entityManager = emf.createEntityManager()) {
            entityManager.getTransaction().begin();
            entityManager.merge(hobby);
            entityManager.getTransaction().commit();
        }
    }

    public void deleteHobby(String hobbyName) {
        try (EntityManager entityManager = emf.createEntityManager()) {
            entityManager.getTransaction().begin();
            Hobby hobby = entityManager.find(Hobby.class, hobbyName);
            if (hobby != null) {
                // Remove all the people associated with the hobby
                for (Person person : hobby.getPersons()) {
                    hobby.removePerson(person);
                }
                entityManager.remove(hobby);
            }
            entityManager.getTransaction().commit();
        }
    }

    public List<Hobby> getAllHobbies() {
        try (EntityManager entityManager = emf.createEntityManager()) {
            TypedQuery<Hobby> query = entityManager.createQuery("select h from Hobby h", Hobby.class);
            return query.getResultList();
        }
    }

    @Override
    public List<Person> personsWithGivenHobby(String hobbyName) {
        try (EntityManager entityManager = emf.createEntityManager()) {
            TypedQuery<Person> query = entityManager.createQuery("select p from Person p join p.hobbies h where h.name = :hobbyName", Person.class);
            query.setParameter("hobbyName", hobbyName);
            return query.getResultList();
        }
    }

    @Override
    public Long amountOfHobbyists(String hobbyName) {
        try (EntityManager entityManager = emf.createEntityManager()) {
            TypedQuery<Long> query = entityManager.createQuery("select count(p) from Person p join p.hobbies h where h.name = :hobbyName", Long.class);
            query.setParameter("hobbyName", hobbyName);
            return query.getSingleResult();
        }
    }

    @Override
    public HashMap<Hobby, Long> allHobbiesAndInterest() {
        List<Hobby> allHobbies = getAllHobbies();
        HashMap<Hobby, Long> allHobbiesAndInterested = new HashMap<>();

        for (Hobby hobby : allHobbies) {
            long interestedPeople = amountOfHobbyists(hobby.getName());
            allHobbiesAndInterested.put(hobby, interestedPeople);
        }
        return allHobbiesAndInterested;
    }
}

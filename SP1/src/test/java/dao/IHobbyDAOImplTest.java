package dao;

import config.HibernateConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import model.Hobby;
import model.HobbyType;
import model.Person;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IHobbyDAOImplTest {

    private static EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
    private static IHobbyDAOImpl hobbyDAO = IHobbyDAOImpl.getInstance(emf);

    @AfterAll
    static void tearDown() {
        if (emf != null) {
            emf.close();
        }
    }

    @Test
    void createHobby() {
        Hobby hobby = new Hobby();
        hobby.setName("pingvinBowling");
        hobby.setWikilink("www.pingvinBowling.com");
        hobby.setCategory("test");
        hobby.setType(HobbyType.INDOOR);

        hobbyDAO.createHobby(hobby);

        Hobby savedHobby = hobbyDAO.findHobby("pingvinBowling");
        assertNotNull(savedHobby);
        assertEquals("pingvinBowling", savedHobby.getName());
    }

    @Test
    void findHobby() {
        Hobby hobby = hobbyDAO.findHobby("Painting");
        assertNotNull(hobby);
        assertEquals("Painting", hobby.getName());
    }

    @Test
    void updateHobby() {
        Hobby hobby = hobbyDAO.findHobby("pingvinBowling");
        hobby.setName("børneBowling");
        hobbyDAO.updateHobby(hobby);

        Hobby updatedHobby = hobbyDAO.findHobby("børneBowling");
        assertNotNull(updatedHobby);
        assertEquals("børneBowling", updatedHobby.getName());
    }

    @Test
    void deleteHobby() {
        Hobby hobbyToDelete = new Hobby();
        hobbyToDelete.setName("ToDeleteHobby");
        hobbyToDelete.setWikilink("www.to-delete-hobby.com");
        hobbyToDelete.setCategory("test");
        hobbyToDelete.setType(HobbyType.INACTIVE);

        hobbyDAO.createHobby(hobbyToDelete);

        Hobby savedHobby = hobbyDAO.findHobby("ToDeleteHobby");
        assertNotNull(savedHobby);

        hobbyDAO.deleteHobby("ToDeleteHobby");

        Hobby deletedHobby = hobbyDAO.findHobby("ToDeleteHobby");
        assertNull(deletedHobby);
    }

    @Test
    void getAllHobbies() {
        List<Hobby> allHobbies = hobbyDAO.getAllHobbies();
        assertNotNull(allHobbies);
    }

    @Test
    void personsWithGivenHobby() {
        List<Person> personList = hobbyDAO.personsWithGivenHobby("Painting");
        assertNotNull(personList);
        assertEquals(1, personList.size());
    }

    @Test
    void amountOfHobbyists() {
        long counter = hobbyDAO.amountOfHobbyists("Painting");
        assertTrue(counter > 0);
        assertEquals(1, counter);
    }

    @Test
    void allHobbiesAndInterest() {
        HashMap<Hobby, Long> hobbiesAndInterest = hobbyDAO.allHobbiesAndInterest();
        assertNotNull(hobbiesAndInterest);
    }
}
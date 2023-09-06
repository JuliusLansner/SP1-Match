package dao;

import config.HibernateConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import model.Hobby;
import model.HobbyType;
import model.Person;
import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IHobbyDAOImplTest {

    private static EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
    private static IHobbyDAOImpl hobbyDAO = IHobbyDAOImpl.getInstance(emf);

    private int firstTestHobbyId;

    @BeforeEach
    void setUp() {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();

            em.createQuery("DELETE FROM Person").executeUpdate();
            em.createQuery("DELETE FROM Hobby").executeUpdate();

            Hobby testHobby1 = new Hobby();
            testHobby1.setName("pingvinBowling");
            testHobby1.setWikilink("www.iceColdBowlers.com");
            testHobby1.setCategory("sport");
            testHobby1.setType(HobbyType.SPORT);

            Hobby testHobby2 = new Hobby();
            testHobby2.setName("bearThrowing");
            testHobby2.setWikilink("www.furryThrow.com");
            testHobby2.setCategory("sport");
            testHobby2.setType(HobbyType.SPORT);

            em.persist(testHobby1);
            em.persist(testHobby2);

            firstTestHobbyId = testHobby1.getId();

            Person person1 = new Person("Olfert", 30);
            Person person2 = new Person("Sandra", 25);

            person1.getHobbies().add(testHobby1);
            person2.getHobbies().add(testHobby2);

            em.persist(person1);
            em.persist(person2);

            em.getTransaction().commit();
        }
    }

    @AfterAll
    static void tearDown() {
        if (emf != null) {
            emf.close();
        }
    }

    @Test
    void createHobby() {
        Hobby hobby = new Hobby();
        hobby.setName("hesteVisker");
        hobby.setWikilink("www.hviskeTiske.com");
        hobby.setCategory("test");
        hobby.setType(HobbyType.OUTDOOR);

        hobbyDAO.createHobby(hobby);

        Hobby savedHobby = hobbyDAO.findHobby(hobby.getId());
        assertNotNull(savedHobby);
        assertEquals("hesteVisker", savedHobby.getName());
    }

    @Test
    void findHobby() {
        Hobby hobby = hobbyDAO.findHobby(firstTestHobbyId);
        assertNotNull(hobby);
        assertEquals("pingvinBowling", hobby.getName());
    }

    @Test
    void updateHobby() {
        Hobby hobby = hobbyDAO.findHobby(firstTestHobbyId);
        assertNotNull(hobby);

        hobby.setName("børneBowling");
        hobbyDAO.updateHobby(hobby);

        Hobby updatedHobby = hobbyDAO.findHobby(firstTestHobbyId);
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

        Hobby savedHobby = hobbyDAO.findHobby(hobbyToDelete.getId());
        assertNotNull(savedHobby);

        hobbyDAO.deleteHobby(hobbyToDelete.getId());

        Hobby deletedHobby = hobbyDAO.findHobby(hobbyToDelete.getId());
        assertNull(deletedHobby);
    }

    @Test
    void getAllHobbies() {
        List<Hobby> allHobbies = hobbyDAO.getAllHobbies();
        assertNotNull(allHobbies);
    }

    @Test
    void personsWithGivenHobby() {
        List<Person> personList = hobbyDAO.personsWithGivenHobby("pingvinBowling");
        assertNotNull(personList);
        assertEquals(1, personList.size());
    }

    @Test
    void amountOfHobbyists() {
        long counter = hobbyDAO.amountOfHobbyists("pingvinBowling");
        assertTrue(counter > 0);
        assertEquals(1, counter);
    }

    @Test
    void allHobbiesAndInterest() {
        HashMap<Hobby, Long> hobbiesAndInterest = hobbyDAO.allHobbiesAndInterest();
        assertNotNull(hobbiesAndInterest);
    }
}
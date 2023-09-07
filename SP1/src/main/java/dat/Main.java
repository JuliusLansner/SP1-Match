package dat;

import config.HibernateConfig;
import dao.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import model.Person;

public class Main {
    private static EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
    public static void main(String[] args) {
/*
        IPersonDAO personDAO = IPersonDAOImpl.getInstance(emf);
        IAdressDAO adressDAO = IAdressDAOImpl.getInstance(emf);
        personDAO.createPerson("betnia",27);

        adressDAO.addAddressToPerson("leskor",21,"betnia","Herlev");

*/

        System.out.println("Hello world!");
    }
}
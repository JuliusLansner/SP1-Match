package dat;

import config.HibernateConfig;
import dao.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import model.Person;

public class Main {
    private static EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
    public static void main(String[] args) {
        

        System.out.println("Hello world!");
    }
}
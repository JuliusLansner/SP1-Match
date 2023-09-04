package dat;

import config.HibernateConfig;
import jakarta.persistence.EntityManagerFactory;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = HibernateConfig.getEntityManagerFactoryConfig();


        System.out.println("Hello world!");
    }
}
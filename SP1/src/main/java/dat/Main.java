package dat;

import config.HibernateConfig;
import jakarta.persistence.EntityManagerFactory;

public class Main {
    static EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
    public static void main(String[] args) {
    }
}
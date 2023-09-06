package dat;

import config.HibernateConfig;
import dao.IAdressDAO;
import dao.IAdressDAOImpl;
import dao.IPersonDAOImpl;
import dao.IZipDAOImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import model.Person;

public class Main {
    static EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
    public static void main(String[] args) {


    }
}
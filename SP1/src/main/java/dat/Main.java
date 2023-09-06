package dat;

import config.HibernateConfig;
import dao.IAdressDAO;
import dao.IAdressDAOImpl;
import dao.IPersonDAOImpl;
import dao.IZipDAOImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import model.ContactInfo;
import model.Person;

public class Main {
    static EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
    public static void main(String[] args) {

        ContactInfo contactInfo = new ContactInfo("@Malde",65764534);

        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            ContactInfo contactInfo1 = em.find(ContactInfo.class,1);
            contactInfo1.setPhoneNumber(76546754);
            em.getTransaction().commit();
        }
    }
}
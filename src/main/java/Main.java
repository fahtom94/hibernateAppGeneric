import dao.GenericDaoImpl;
import dao.IGenericDao;
import entity.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import util.HibernateUtil;

import java.util.List;
import java.util.Properties;

/**
 * Created by Aleksandr Makarov on 05.02.2018.
 */
public class Main {

    // Create the SessionFactory when you start the application.
    private static final SessionFactory SESSION_FACTORY;

    /**
     * Initialize the SessionFactory instance.
     */
    static {
        // Create a Configuration object.
        Configuration config = new Configuration();
        // Configure using the application resource named hibernate.cfg.xml.
        config.configure();
        // Extract the properties from the configuration file.
        Properties prop = config.getProperties();

        // Create StandardServiceRegistryBuilder using the properties.
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(prop);

        // Build a ServiceRegistry
        ServiceRegistry registry = builder.build();

        // Create the SessionFactory using the ServiceRegistry
        SESSION_FACTORY = config.buildSessionFactory(registry);
    }

    public static void main(String[] args) {
        /*List<User> users = readAll();
        if (users != null) {
            for (User usr : users) {
                System.out.println(usr);
            }
        }

        // NEVER FORGET TO CLOSE THE SESSION_FACTORY
        SESSION_FACTORY.close();*/

        IGenericDao<User, Long> userDao = new GenericDaoImpl<User, Long>(User.class, HibernateUtil.getSessionFactory());
        List<User> users = userDao.getAll();
        for (User user : users) {
            System.out.println(user);
        }

        HibernateUtil.getSessionFactory().close();

    }


    public static List<User> readAll() {
        List<User> users = null;
        // Create a session
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = null;
        try {
            // Begin a transaction
            transaction = session.beginTransaction();
            users = session.createQuery("FROM User").list();
            // Commit the transaction
            transaction.commit();
        } catch (HibernateException ex) {
            // If there are any exceptions, roll back the changes
            if (transaction != null) {
                transaction.rollback();
            }
            // Print the Exception
            ex.printStackTrace();
        } finally {
            // Close the session
            session.close();
        }
        return users;
    }
}

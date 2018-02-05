package util;

import org.hibernate.HibernateError;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Created by Aleksandr Makarov on 05.02.2018.
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(
                    configuration.getProperties());

            sessionFactory = configuration.buildSessionFactory(builder.build());
        } catch (Throwable e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static Session getSession() throws HibernateException{
        return sessionFactory.openSession();
    }
}

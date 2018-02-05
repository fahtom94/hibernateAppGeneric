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

    public static void main(String[] args) {

        IGenericDao<User, Long> userDao = new GenericDaoImpl<User, Long>(User.class, HibernateUtil.getSessionFactory());
        List<User> users = userDao.getAll();
        for (User user : users) {
            System.out.println(user.getName());
        }

        HibernateUtil.getSessionFactory().close();

    }
}

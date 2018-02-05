package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Aleksandr Makarov on 05.02.2018.
 */
public class GenericDaoImpl<T, PK extends Serializable> implements IGenericDao<T, PK> {

    private SessionFactory sessionFactory;
    private Class<T> persistentClass;

    public GenericDaoImpl(Class<T> clazz, SessionFactory sessionFactory) {
        //[TODO] вынести определение persistentClass
        persistentClass = clazz;
        this.sessionFactory = sessionFactory;
        if (sessionFactory == null)
            throw new RuntimeException("Session Factory is null");
    }

    public T get(Class<T> clazz, PK id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        T elem = (T) session.get(clazz, id);
        session.getTransaction().commit();
        return elem;
    }

    public T save(T object) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(object);
        session.getTransaction().commit();
        return object;
    }

    public void update(T object) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.update(object);
        session.getTransaction().commit();
    }

    public void delete(T object) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.delete(object);
        session.getTransaction().commit();
    }

    public Class<T> getPersistentClass() {
        return persistentClass;
    }

    public List<T> getAll() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<T> list = session.createQuery("FROM " + persistentClass.getName()).list();
        session.getTransaction().commit();
        return list;
    }

    public List<T> query(String hsql, Map<String, Object> params) {
        //after
        return null;
    }
}

package dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Aleksandr Makarov on 05.02.2018.
 */
public interface IGenericDao<T,PK extends Serializable> {
    public T get(Class<T> clazz, PK id);
    public List<T> getAll();
    public T save(T object);
    public void update(T object);
    public void delete(T object);
    public List<T> query(String hsql, Map<String, Object> params);
}

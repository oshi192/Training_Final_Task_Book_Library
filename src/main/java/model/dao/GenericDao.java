package model.dao;

import model.entity.User;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T> extends AutoCloseable {
    void create (T entity);
    T findById(int id);
    List<T> findAll(int offset, int limit);
    void update(T entity);
    void delete(int id);
    void close();
}

package model.dao;

import model.entity.User;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T> extends AutoCloseable {
    void create (T entity);
    Optional<User> findById(int id);
    List<T> findAll(int shift);
    void update(T entity);
    void delete(int id);
    void close();
}

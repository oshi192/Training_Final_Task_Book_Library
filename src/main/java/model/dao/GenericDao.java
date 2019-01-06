package model.dao;

import model.entity.User;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T> extends AutoCloseable {
    Optional<T> get(int id);

    List<T> getAll();
    List<T> getAllPaginate(int limit, int offset);

    void save(T t);

    void update(T t, String[] params);

    void delete(T t);
}

package model.dao;

import exception.RecordChangeException;
import model.entity.User;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T> extends AutoCloseable {
    T get(int id);

    List<T> getAll();
    List<T> getAllPaginate(int limit, int offset);

    void save(T t);

    void update(T t) throws RecordChangeException;

    void delete(T t) throws RecordChangeException;
}

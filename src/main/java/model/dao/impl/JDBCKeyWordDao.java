package model.dao.impl;

import model.dao.KeyWordDao;
import model.entity.KeyWord;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class JDBCKeyWordDao implements KeyWordDao {
    private Connection connection;

    public JDBCKeyWordDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<KeyWord> findByValue(String value) {
        return Optional.empty();
    }

    @Override
    public void create(KeyWord entity) {

    }

    @Override
    public KeyWord findById(int id) {
        return null;
    }

    @Override
    public List<KeyWord> findAll(int offset, int limit) {
        return null;
    }



    @Override
    public void update(KeyWord entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close() {

    }
}

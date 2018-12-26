package model.dao.impl;

import model.dao.KeyWordDao;
import model.entity.KeyWord;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class JDBCKeyWordDao implements KeyWordDao {
    private Connection connection;

    @Override
    public Optional<KeyWord> findByValue(String value) {
        return Optional.empty();
    }

    @Override
    public Optional<KeyWord> get(int id) {
        return Optional.empty();
    }

    @Override
    public List<KeyWord> getAll() {
        return null;
    }

    @Override
    public void save(KeyWord keyWord) {

    }

    @Override
    public void update(KeyWord keyWord, String[] params) {

    }

    @Override
    public void delete(KeyWord keyWord) {

    }

    @Override
    public void close() throws Exception {

    }
}

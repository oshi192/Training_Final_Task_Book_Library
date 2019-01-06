package model.dao;

import model.entity.KeyWord;

import java.util.Optional;


public interface KeyWordDao extends GenericDao<KeyWord> {

    Optional<KeyWord> findByValue(String value);
}

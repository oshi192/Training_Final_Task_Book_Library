package model.dao;

import model.entity.Author;

import java.util.Optional;

public interface AuthorDao extends GenericDao<Author> {
    Optional<Author> findByName(String name);
    Optional<Author> findBySurname(String surName);

}

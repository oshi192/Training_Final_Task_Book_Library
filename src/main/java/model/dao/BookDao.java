package model.dao;

import model.entity.Author;
import model.entity.Book;
import model.entity.KeyWord;

import java.util.Optional;

public interface BookDao extends GenericDao<Book> {
    Optional<Author> findByAuthor(String authorName);
    Optional<Author> findByName(String name);

}

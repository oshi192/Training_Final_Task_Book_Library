package model.dao;

import model.entity.Author;
import model.entity.User;

import java.util.List;
import java.util.Optional;

public interface BookAndAuthors extends GenericDao<BookAndAuthors> {
    List<Author> findAllAuthorsByBookId(int bookId);

}

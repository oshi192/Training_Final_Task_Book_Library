package model.dao.impl;

import model.dao.BookAndAuthors;
import model.dao.mapper.AuthorMapper;
import model.dao.mapper.BookMapper;
import model.entity.Author;
import model.entity.Book;
import model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCBookAndAuthors implements BookAndAuthors {
    private Connection connection;
    private static final String FIND_ALL_BY_BOOK_ID= "SELECT author.* FROM author join authors2book on " +
            "author.author_id = authors2book.author_id where authors2book.books_book_id = ?;";
    private static final String COUNT = "SELECT COUNT(id) as count FROM books";

    public JDBCBookAndAuthors(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Author> findAllAuthorsByBookId(int bookId) {
        List<Author> result = new ArrayList<>();
        try(PreparedStatement ps = connection.prepareCall(FIND_ALL_BY_BOOK_ID)){
            ps.setInt( 1, bookId);
            ResultSet rs;
            rs = ps.executeQuery();
            AuthorMapper mapper = new AuthorMapper();
            while (rs.next()){
                result.add(mapper.extractFromResultSet(rs));
            }
        }catch (Exception ex){
            //todo my exception
            throw new RuntimeException(ex);
        }
        return result;
    }

    @Override
    public void create(BookAndAuthors entity) {

    }

    @Override
    public Optional<User> findById(int id) {
        return Optional.empty();
    }

    @Override
    public List<BookAndAuthors> findAll(int shift) {
        return null;
    }

    @Override
    public void update(BookAndAuthors entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close() {

    }
}

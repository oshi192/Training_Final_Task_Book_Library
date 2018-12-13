package model.dao.impl;

import model.dao.IBookAndAuthors;
import model.entity.BookAndAuthors;
import model.dao.mapper.BookMapper;
import model.entity.Book;
import model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCBookAndAuthors implements IBookAndAuthors {
    private Connection connection;
    private static final String FIND_ALL= "SELECT * FROM books;";
    private static final String COUNT = "SELECT COUNT(id) as count FROM books";

    public JDBCBookAndAuthors(Connection connection) {
        this.connection = connection;
    }

    public List<BookAndAuthors> findAllbookAndAuthor() {
        List<BookAndAuthors> result = new ArrayList<>();
        JDBCAuthorDao authorDao = new JDBCAuthorDao(connection);
        try(PreparedStatement ps = connection.prepareCall(FIND_ALL)){
            ResultSet rs;
            rs = ps.executeQuery();
            BookMapper mapper = new BookMapper();
            while (rs.next()){
                Book book = mapper.extractFromResultSet(rs);
                result.add(new BookAndAuthors(book,authorDao.findAllAuthorsByBookId(book.getId())));
            }
        }catch (Exception ex){
            //todo my exception
            throw new RuntimeException(ex);
        }
        return result;
    }


    @Override
    public void create(IBookAndAuthors entity) {

    }

    @Override
    public Optional<User> findById(int id) {
        return Optional.empty();
    }

    @Override
    public List<IBookAndAuthors> findAll(int shift) {
        return null;
    }

    @Override
    public void update(IBookAndAuthors entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close() {

    }
}

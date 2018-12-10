package model.dao.impl;

import model.dao.BookDao;
import model.dao.mapper.BookMapper;
import model.dao.mapper.UserMapper;
import model.entity.Author;
import model.entity.Book;
import model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCBookDao implements BookDao {
    private Connection connection;
    private static final String FIND_ALL_PAGINATE = "SELECT * FROM books LIMIT 10 OFFSET ?";
    private static final String COUNT = "SELECT COUNT(id) as count FROM books";

    public JDBCBookDao(Connection connection) {
this.connection=connection;
    }

    @Override
    public Optional<Author> findByAuthor(String authorName) {
        return Optional.empty();
    }

    @Override
    public Optional<Author> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public void create(Book entity) {

    }

    @Override
    public Optional<User> findById(int id) {
        return null;
    }

    @Override
    public List<Book> findAll(int offset) {
        List<Book> result = new ArrayList<>();
        try(PreparedStatement ps = connection.prepareCall(FIND_ALL_PAGINATE)){
            ps.setInt( 1, offset);
            ResultSet rs;
            rs = ps.executeQuery();
            BookMapper mapper = new BookMapper();
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
    public void update(Book entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close() {

    }

    public int getCount() {
        try(PreparedStatement ps = connection.prepareCall(COUNT)){
            ResultSet rs;
            rs = ps.executeQuery();
            return rs.getInt("count");
        } catch (SQLException e) {
            //todo my exception
            e.printStackTrace();
        }
        return 0;
    }
}

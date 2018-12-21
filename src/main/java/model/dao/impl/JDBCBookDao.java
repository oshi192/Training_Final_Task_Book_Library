package model.dao.impl;

import model.dao.BookDao;
import model.dao.mapper.BookMapper;
import model.entity.Author;
import model.entity.Book;
import util.ResourceBundleManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCBookDao implements BookDao {
    private Connection connection;
    private static final String BOOKS_FIND_ALL_PAGINATE = "BOOKS_FIND_ALL_PAGINATE";
    private static final String BOOKS_COUNT = "BOOKS_COUNT";

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
    public Book findById(int id) {
        return null;
    }

    @Override
    public List<Book> findAll(int offset, int limit) {
        List<Book> result = new ArrayList<>();
        String query = ResourceBundleManager.getSqlString(BOOKS_FIND_ALL_PAGINATE);
        try(PreparedStatement ps = connection.prepareCall(query)){
            System.out.println("query" +query+" setLimit: "+limit+" offset:"+offset);
            ps.setInt( 1, limit);
            System.out.println("offset: "+offset);
            ps.setInt( 2, offset);
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
        close();
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
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getCount() {
        String query = ResourceBundleManager.getSqlString(BOOKS_COUNT);
        try(PreparedStatement ps = connection.prepareCall(query)){
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

package model.dao.impl;

import model.dao.AuthorDao;
import model.dao.mapper.AuthorMapper;
import model.entity.Author;
import model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static util.QueryManager.executeQuery;

public class JDBCAuthorDao implements AuthorDao {
    private static final String ADD_AUTHOR_QUERY = "INSERT INTO author(first_name, second_name, patronymic_name) VALUES(?, ?, ?, ?)";
    private static final String UPDATE_AUTHOR_QUERY = "UPDATE author SET first_name = ?, second_name = ?, patronymic_name = ? WHERE id = ?";
    private static final String DELETE_AUTHOR_QUERY = "DELETE FROM author WHERE id = ?";
    private static final String GET_COUNT = "SELECT COUNT(id) as count FROM author";
    private static final String FIND_ALL_BY_BOOK_ID = "SELECT author.* FROM author join authors2book on " +
            "author.author_id = authors2book.author_id where authors2book.books_book_id = ?;";
    private static final String FIND_AUTHOR_BY_ID = "SELECT * FROM author WHERE id = ?";
    private static final String FIND_AUTHORS_BY_ID_ARRAY = "SELECT * FROM author WHERE id IN ?";
    private static final String FIND_AUTHOR_BY_BOOK_ID = "SELECT * FROM author RIGHT JOIN book_author ON book_author.author_id = author.id WHERE book_author.book_id = ?";
    private static final String FIND_ALL = "SELECT * FROM author";
    private static final String FIND_ALL_PAGINATE = "SELECT * FROM author LIMIT 10 OFFSET ?";

    private final static String AUTHOR_ID = "id";
    private final static String AUTHOR_FIRST_NAME = "first_name";
    private final static String AUTHOR_SECOND_NAME = "second_name";
    private final static String AUTHOR_PATRONIMYC_NAME = "patronymic_name";
    private Connection connection;

    public JDBCAuthorDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Author> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public Optional<Author> findBySurname(String surName) {
        return Optional.empty();
    }

    @Override
    public void create(Author author) {
        //todo  check this
        executeQuery(connection, ADD_AUTHOR_QUERY, preparedStatement -> {
            preparedStatement.setString(1, author.getFirstName());
            preparedStatement.setString(2, author.getSecondName());
            preparedStatement.setString(3, author.getPatronymicName());
        });
    }

    @Override
    public Optional<User> findById(int id) {
        return null;
    }

    @Override
    public List<Author> findAll(int offset) {
        return null;
    }

    @Override
    public void update(Author author) {
        executeQuery(connection, UPDATE_AUTHOR_QUERY, preparedStatement -> {
            preparedStatement.setString(1, author.getFirstName());
            preparedStatement.setString(2, author.getSecondName());
            preparedStatement.setString(3, author.getPatronymicName());
            preparedStatement.setInt(4, author.getId());
        });
    }

    @Override
    public void delete(int id) {
        executeQuery(connection, DELETE_AUTHOR_QUERY, preparedStatement -> {
            preparedStatement.setInt(1, id);
        });
    }

    @Override
    public void close() {

    }

    public List<Author> findAllAuthorsByBookId(int bookId) {
        List<Author> result = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareCall(FIND_ALL_BY_BOOK_ID)) {
            ps.setInt(1, bookId);
            ResultSet rs;
            rs = ps.executeQuery();
            AuthorMapper mapper = new AuthorMapper();
            while (rs.next()) {
                result.add(mapper.extractFromResultSet(rs));
            }
        } catch (Exception ex) {
            //todo my exception
            throw new RuntimeException(ex);
        }
        return result;
    }
}

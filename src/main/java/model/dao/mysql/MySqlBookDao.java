package model.dao.mysql;

import config.ResourceBundleManager;
import controller.util.QueryBuilder;
import model.connectionpool.ConnectionPoolHolder;
import model.dao.BookDao;
import model.dao.mapper.AuthorMapper;
import model.dao.mapper.BookMapper;
import model.dao.mapper.UserMapper;
import model.entity.Author;
import model.entity.Book;
import model.entity.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class MySqlBookDao implements BookDao {
    private static final Logger logger = Logger.getLogger(MySqlBookDao.class);
    private static UserMapper userMapper = new UserMapper();
    private static BookMapper bookMapper = new BookMapper();
    private static AuthorMapper authorMapper = new AuthorMapper();

    @Override
    public Book get(int id) {
        logger.error("get by id:" + id);
        Book book=null;
        try (Connection connection = ConnectionPoolHolder.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareCall(
                     ResourceBundleManager.getSqlString(ResourceBundleManager.BOOK_FIND_BY_ID))) {
            ps.setInt(1,id);
            logger.info("searching all books....." + ps.toString());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                book = bookMapper.mapGet(rs);
            }
        } catch (SQLException e) {
            logger.error("fail..." + e);
            e.printStackTrace();
        }
        logger.info("found book: " + book);
        return book;
    }

    @Override
    public List<Book> getAll() {
        return null;
    }
    public List<Book> getAllByUserId(int userId,int limit, int offset) {
        Map<Integer, Book> books = new HashMap<>();
        try (Connection connection = ConnectionPoolHolder.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareCall(
                     ResourceBundleManager.getSqlString(ResourceBundleManager.BOOK_FIND_ALL_BY_USER_ID))) {
            ps.setInt(1, limit);
            ps.setInt(2, offset);
            ps.setInt(3, userId);
            logger.info("searching all books by userId" + ps.toString());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Book book = bookMapper.mapGet(rs);
                Author author = authorMapper.mapGet(rs);
                if (books.get(book.getId()) != null) {
                    book = books.get(book.getId());
                    book.setAuthors(" "+book.getAuthors() + ", " + author.toString());
                } else {
                    book.setAuthors(" "+author.toString());
                    books.put(book.getId(), book);
                }
            }
        } catch (SQLException e) {
            logger.error("fail..." + e);
            e.printStackTrace();
        }
        List<Book> booksList = new ArrayList<>(books.values());
        logger.info("found books: " + booksList.size());
        return booksList;
    }

    @Override
    public List<Book> getAllPaginate(int limit, int offset) {
        Map<Integer, Book> books = new HashMap<>();
        try (Connection connection = ConnectionPoolHolder.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareCall(
                     ResourceBundleManager.getSqlString(ResourceBundleManager.BOOK_FIND_ALL_CLEAN))) {
            ps.setInt(1, limit);
            ps.setInt(2, offset);
            logger.info("searching all books....." + ps.toString());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Book book = bookMapper.mapGet(rs);
                Author author = authorMapper.mapGet(rs);
                if (books.get(book.getId()) != null) {
                    book = books.get(book.getId());
                    book.setAuthors(" "+book.getAuthors() + ", " + author.toString());
                } else {
                    book.setAuthors(" "+author.toString());
                    books.put(book.getId(), book);
                }
            }
        } catch (SQLException e) {
            logger.error("fail..." + e);
            e.printStackTrace();
        }
        List<Book> booksList = new ArrayList<>(books.values());
        logger.info("found books: " + booksList.size());
        return booksList;
    }

    @Override
    public void save(Book book) {
        logger.info("try create book: "+ book);
        try (Connection connection = ConnectionPoolHolder.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareCall(
                     ResourceBundleManager.getSqlString(ResourceBundleManager.BOOK_CREATE))) {
            bookMapper.mapCreate(ps,book);
            logger.info("query: "+ ps);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("fail: "+ book);
        }
    }

    @Override
    public void update(Book book) {
        logger.info("try update book: "+ book);
        try (Connection connection = ConnectionPoolHolder.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareCall(
                     ResourceBundleManager.getSqlString(ResourceBundleManager.BOOK_UPDATE))) {
            bookMapper.mapCreate(ps,book);
            logger.info("query: "+ ps);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("fail: "+ book);
        }
    }

    @Override
    public void delete(Book book) {
        logger.info("try delete book: "+ book);
        try (Connection connection = ConnectionPoolHolder.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareCall(
                     ResourceBundleManager.getSqlString(ResourceBundleManager.BOOK_DELETE))) {
            ps.setInt(1,book.getId());
            logger.info("query: "+ ps);
            ps.executeUpdate();
            logger.info("success! ");
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("fail: "+ book);
        }
    }

    @Override
    public void close() throws Exception {

    }

    public int getCount(String countQuery) {
        List<Book> books = new ArrayList<>();
        int count = 0;
        logger.info("books count....." + countQuery);
        try (Connection connection = ConnectionPoolHolder.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareCall(countQuery)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt("count");
            }
            logger.info("success!...count" + count);
        } catch (SQLException ex) {
            logger.error("fail..." + ex);
        }
        return count;
    }

    public List<Book> getAllRequestsPaginate(int limit, int offset) {
        return null;
    }

    public List<Book> getAllPaginateWithStatus(Book.Status taken, int limit, int offset) {
        Map<Integer, Book> books = new HashMap<>();
        try (Connection connection = ConnectionPoolHolder.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareCall(
                     ResourceBundleManager.getSqlString(ResourceBundleManager.ADMIN_BOOKS_WITH_STATUS))) {
            ps.setInt(1, limit);
            ps.setInt(2, offset);
            ps.setInt(3, taken.id);
            logger.info("searching all books with status " + taken.name() + ": " + ps.toString());
            ResultSet rs = ps.executeQuery();logger.info("fetxh size)"+rs.getFetchSize());
            while (rs.next()) {
                Book book = bookMapper.mapGet(rs);
                Author author = authorMapper.mapGet(rs);
                User user = userMapper.mapGet(rs);
                if (books.get(book.getId()) != null) {
                    book = books.get(book.getId());
                    book.setAuthors(" "+book.getAuthors() + ", " + author.toString());
                } else {
                    book.setUser(user);
                    book.setAuthors(" "+author.toString());
                    books.put(book.getId(), book);
                }
            }
        } catch (SQLException e) {
            logger.error("fail..." + e);
            e.printStackTrace();
        }
        List<Book> booksList = new ArrayList<>(books.values());
        logger.info("found books: " + booksList.size());
        return booksList;
    }

    public List<Book> getAllWithStatusByUserId(int userId, Book.Status taken, int limit, int offset){
        Map<Integer, Book> books = new HashMap<>();
        try (Connection connection = ConnectionPoolHolder.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareCall(
                     ResourceBundleManager.getSqlString(ResourceBundleManager.BOOKS_WITH_STATUS_BY_USER_ID))) {
            ps.setInt(1, limit);
            ps.setInt(2, offset);
            ps.setInt(3, taken.id);
            ps.setInt(4, userId);
            logger.info("searching all books with status " + taken.name() + ": " + ps.toString());
            ResultSet rs = ps.executeQuery();logger.info("fetxh size)"+rs.getFetchSize());
            while (rs.next()) {
                Book book = bookMapper.mapGet(rs);
                Author author = authorMapper.mapGet(rs);
                if (books.get(book.getId()) != null) {
                    book = books.get(book.getId());
                    book.setAuthors(" "+book.getAuthors() + ", " + author.toString());
                } else {
                    book.setAuthors(" "+author.toString());
                    books.put(book.getId(), book);
                }
            }
        } catch (SQLException e) {
            logger.error("fail..." + e);
            e.printStackTrace();
        }
        List<Book> booksList = new ArrayList<>(books.values());
        logger.info("found books: " + booksList.size());
        return booksList;
    }

    public List<String> getAllSections() {
        List<String>sections=new ArrayList<>();
        try (Connection connection = ConnectionPoolHolder.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareCall(
                     ResourceBundleManager.getSqlString(ResourceBundleManager.BOOKS_SECTIONS_ENUM))) {
            logger.info("searching sections : " + ps.toString());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                sections.add(rs.getString(1));
            }
        } catch (SQLException e) {
            logger.error("fail..." + e);
            e.printStackTrace();
        }
        return sections;
    }
}

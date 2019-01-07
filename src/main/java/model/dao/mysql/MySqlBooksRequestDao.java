package model.dao.mysql;

import config.ResourceBundleManager;
import controller.util.QueryBuilder;
import model.connectionpool.ConnectionPoolHolder;
import model.dao.BooksRequestDao;
import model.dao.mapper.AuthorMapper;
import model.dao.mapper.BookMapper;
import model.dao.mapper.UserMapper;
import model.entity.*;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class MySqlBooksRequestDao implements BooksRequestDao {
    private static final Logger logger = Logger.getLogger(MySqlBooksRequestDao.class);
    private static BookMapper bookMapper = new BookMapper();
    private static AuthorMapper authorMapper = new AuthorMapper();
    private static UserMapper userMapper = new UserMapper();

    private static final String ADMIN_BOKS_REQUEST_CLEAR = "adim-books-request";
    private static final String BOKS_REQUEST_BY_USER_ID_CLEAR = "user-books-requests";
    private static final String TAKEN_BOKS_REQUEST = "adim-taken-books-create";
    private static final String BOKS_REQUEST_REMOVE = "adim-books-request-remove";

    @Override
    public Optional<BooksRequest> get(int id) {
        return Optional.empty();
    }

    @Override
    public List<BooksRequest> getAll() {
        return null;
    }

    public List<Book> getAllbyUserId(int userId,int limit, int offset) {
        QueryBuilder qb = new QueryBuilder(ResourceBundleManager.getSqlString(BOKS_REQUEST_BY_USER_ID_CLEAR).replace("?",""+userId));
        qb.addPagination(limit, offset);
        Map<Integer,Book> books = new HashMap<>();
        try {
            logger.info("executing query: "+qb.getQuery());
            ResultSet resultSet = qb.execute();
            while(resultSet.next()){
                Book book = bookMapper.mapGet(resultSet);
                Author author = authorMapper.mapGet(resultSet);
                if(books.get(book.getId())!=null){
                    book = books.get(book.getId());
                    book.setAuthors(book.getAuthors()+", "+author.toString());
                }else{
                    book.setAuthors(author.toString());
                    books.put(book.getId(),book);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<Book> booksList = new ArrayList<>(books.values());
        logger.info("found taken books: "+booksList.size());
        return booksList;
    }

    @Override
    public List<BooksRequest> getAllPaginate(int limit, int offset) {
        QueryBuilder qb = new QueryBuilder(ResourceBundleManager.getSqlString(ADMIN_BOKS_REQUEST_CLEAR));
        qb.addPagination(limit, offset);
        Map<Integer,BooksRequest> books = new HashMap<>();
        try {
            ResultSet resultSet = qb.execute();
            while(resultSet.next()){
                BooksRequest booksRequest = new BooksRequest( bookMapper.mapGet(resultSet));
                User user = userMapper.mapGet(resultSet);
                user.setPassword("");
                booksRequest.setUser(user);
                Author author = authorMapper.mapGet(resultSet);
                if(books.get(user.getId())!=null){
                    booksRequest = books.get(user.getId());
                    booksRequest.setAuthors(booksRequest.getAuthors()+", "+author.toString());
                }else{
                    booksRequest.setAuthors(author.toString());
                    books.put(user.getId(),booksRequest);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<BooksRequest> booksRequests = new ArrayList<>(books.values());
        logger.info("found taken books: "+booksRequests.size());
        return booksRequests;
    }

    @Override
    public void save(BooksRequest booksRequest) {

    }

    @Override
    public void update(BooksRequest booksRequest, String[] params) {

    }

    @Override
    public void delete(BooksRequest booksRequest) {
        try(Connection connection = ConnectionPoolHolder.getDataSource().getConnection();
            PreparedStatement deleteStatement=connection.prepareStatement(ResourceBundleManager.getSqlString(BOKS_REQUEST_REMOVE));) {
            deleteStatement.setInt(1,booksRequest.getUser().getId());
            deleteStatement.setInt(2,booksRequest.getId());
            logger.info("executing query: "+deleteStatement.toString());
            deleteStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("error while deleting...");
        }

    }

    public void confirm(int adminId,int userId, int bookId) throws SQLException {
        PreparedStatement addTakenBooksStatement = null;
        PreparedStatement deleteStatement = null;
        Connection connection = ConnectionPoolHolder.getDataSource().getConnection();
        try {
            connection.setAutoCommit(false);
            addTakenBooksStatement=connection.prepareStatement(ResourceBundleManager.getSqlString(TAKEN_BOKS_REQUEST));
            addTakenBooksStatement.setInt(1,userId);
            addTakenBooksStatement.setInt(2,adminId);
            addTakenBooksStatement.setInt(3,bookId);
            addTakenBooksStatement.setDate(4, Date.valueOf(LocalDate.now()));
            addTakenBooksStatement.setDate(5, Date.valueOf(LocalDate.now().plusMonths(1)));
            logger.info("executing query add to takenBooks: "+addTakenBooksStatement.toString());
            addTakenBooksStatement.executeUpdate();
            deleteStatement=connection.prepareStatement(ResourceBundleManager.getSqlString(BOKS_REQUEST_REMOVE));
            deleteStatement.setInt(1,userId);
            deleteStatement.setInt(2,bookId);
            logger.info("executing query delete from books requests: "+deleteStatement.toString());
            deleteStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            if (connection != null){
                try {
                    logger.info("Transaction is being rolled back");
                    connection.rollback();
                } catch(SQLException excep) {
                    logger.error(excep);
                }
            }
        }finally {
            if (addTakenBooksStatement != null) {
                addTakenBooksStatement.close();
            }
            if (deleteStatement != null) {
                deleteStatement.close();
            }
            connection.setAutoCommit(true);
        }
    }

    @Override
    public void close() throws Exception {

    }
    public int getCount(String countQuery) {
        int count=0;
        logger.info("booksRequests count....." + countQuery);
        try (Connection connection = ConnectionPoolHolder.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareCall(countQuery)) {
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                count = rs.getInt("count");
            }
            logger.info("success!...count"+count );
        } catch (SQLException ex) {
            logger.error("fail..." + ex);
        }
        return count;
    }
}

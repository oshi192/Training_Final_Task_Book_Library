package model.dao.mysql;

import config.ResourceBundleManager;
import controller.util.QueryBuilder;
import model.connectionpool.ConnectionPoolHolder;
import model.dao.BookDao;
import model.dao.mapper.AuthorMapper;
import model.dao.mapper.BookMapper;
import model.entity.Author;
import model.entity.Book;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class MySqlBookDao implements BookDao {
    private static final Logger logger = Logger.getLogger(MySqlBookDao.class);
    private static BookMapper bookMapper = new BookMapper();
    private static AuthorMapper authorMapper = new AuthorMapper();
    private static final String BOOK_FIND_ALL_PAGINATE = "BOOK_FIND_ALL_PAGINATE";
    private static final String BOOK_FIND_ALL_CLEAN = "book-find-all-clean";
    private static final String AUTHORS_BY_BOOK_ID = "AUTHORS_BY_BOOK_ID";
    private static final String BOOKS_COUNT = "BOOKS_COUNT";

    @Override
    public Optional<Book> get(int id) {
        return Optional.empty();
    }

    @Override
    public List<Book> getAll() {
        return null;
    }

    @Override
    public List<Book> getAllPaginate(int limit, int offset) {
        QueryBuilder qb = new QueryBuilder(ResourceBundleManager.getSqlString(BOOK_FIND_ALL_CLEAN));
        qb.addPagination(limit, offset);
        Map<Integer,Book> books = new HashMap<>();
        logger.info("searching all books....." + qb.getQuery());
        try {
            ResultSet rs = qb.execute();
            while (rs.next()) {
                Book book = bookMapper.mapGet(rs);
                Author author = authorMapper.mapGet(rs);
                if(books.get(book.getId())!=null){
                    book = books.get(book.getId());
                    book.setAuthors(book.getAuthors()+", "+author.toString());
                }else{
                    book.setAuthors(author.toString());
                    books.put(book.getId(),book);
                }
            }
        } catch (SQLException ex) {
            logger.error("fail..." + ex);
        }
        List<Book> booksList = new ArrayList<>(books.values());
        logger.info("found books: "+booksList.size());
        return booksList;
    }

    @Override
    public void save(Book book) {

    }

    @Override
    public void update(Book book, String[] params) {

    }

    @Override
    public void delete(Book book) {

    }

    @Override
    public void close() throws Exception {

    }

    public int getCount() {
        List<Book> books = new ArrayList<>();
        int count=0;
        String query = ResourceBundleManager.getSqlString(BOOKS_COUNT);
        logger.info("books count....." + query);
        try (Connection connection = ConnectionPoolHolder.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareCall(query)) {
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                count = rs.getInt("count");
            }
            logger.info("success!...count" +count);
        } catch (SQLException ex) {
            logger.error("fail..." + ex);
        }
        return count;
    }
}

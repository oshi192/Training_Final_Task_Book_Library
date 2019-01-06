package model.dao.mysql;

import config.ResourceBundleManager;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MySqlBookDao implements BookDao {
    private static final Logger logger = Logger.getLogger(MySqlBookDao.class);
    private static BookMapper mapper = new BookMapper();
    private static AuthorMapper authorMapper = new AuthorMapper();
    private static final String BOOK_FIND_ALL_PAGINATE = "BOOK_FIND_ALL_PAGINATE";
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


    public List<Book> getAll(int limit, int offset) {
        List<Book> books = new ArrayList<>();
        String query = ResourceBundleManager.getSqlString(BOOK_FIND_ALL_PAGINATE);
        logger.info("searching all books....." + query);
        try (Connection connection = ConnectionPoolHolder.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareCall(query)) {
            ps.setInt(1, limit);
            ps.setInt(2, offset);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                books.add(mapper.mapGet(rs));
            }
            logger.info("success!...found books now search authors:" + books);
            connection.close();
        } catch (SQLException ex) {
            logger.error("fail..." + ex);
            return null;
        }
        findAuthors(books);
        return books;
    }


    public List<Book> findAuthors(List<Book> books) {
        for (Book book : books) {
            List<Author> authors = new ArrayList<>();
            String query = ResourceBundleManager.getSqlString(AUTHORS_BY_BOOK_ID);
            try (Connection connection = ConnectionPoolHolder.getDataSource().getConnection();
                 PreparedStatement ps = connection.prepareCall(query)) {
                logger.info("query:"+query);
                ps.setInt(1, book.getId());
                ResultSet rs = ps.executeQuery();
                System.out.println("size:"+authors.size() +"next");
                while (rs.next()) {
                    authors.add(authorMapper.mapGet(rs));
                }
                logger.info("find authors for book:"+book);
                StringBuffer stringBuffer = new StringBuffer("");
                                        System.out.println("size:"+authors.size() );
                if (authors.size() > 0 && authors.get(0)!=null) {
                    stringBuffer.append(authors.get(0).toString());
                    for (int i = 0; i < authors.size(); i++) {
                        stringBuffer.append(", ");
                        stringBuffer.append(authors.get(i).toString());
                        System.out.println(i+"getPatronymicName"+authors.get(i).getPatronymicName());
                    }
                }else{
                    logger.error("fail.adding authors to books.");

                }

               book.setAuthors(stringBuffer.toString());
            } catch (Exception e) {
                logger.error("fail.adding authors to books." + e);
                e.printStackTrace();
            }

        }
        return books;
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
            logger.info("success!...count" + books);
        } catch (SQLException ex) {
            logger.error("fail..." + ex);
            return 0;
        }
        return count;
    }
}

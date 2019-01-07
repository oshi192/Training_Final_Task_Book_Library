package model.dao.mysql;

import config.ResourceBundleManager;
import controller.util.QueryBuilder;
import model.connectionpool.ConnectionPoolHolder;
import model.dao.TakenBooksDao;
import model.dao.mapper.AuthorMapper;
import model.dao.mapper.TakenBooksMapper;
import model.entity.Author;
import model.entity.Book;
import model.entity.TakenBooks;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class MySqlTakenBooksDao implements TakenBooksDao {
    private static final Logger logger = Logger.getLogger(MySqlTakenBooksDao.class);
    private static TakenBooksMapper mapper = new TakenBooksMapper();
    private static AuthorMapper authorMapper = new AuthorMapper();
    private static final String ADMIN_TAKEN_BOOKS_CLEAR = "adim-taken-books";
    private static final String TAKEN_BOOKS_BY_USER_ID_CLEAR = "taken-books-by-user-id";

    @Override
    public Optional<TakenBooks> get(int id) {
        return Optional.empty();
    }

    @Override
    public List<TakenBooks> getAll() {
        return null;
    }

    @Override
    public List<TakenBooks> getAllPaginate(int limit, int offset) {
        QueryBuilder qb = new QueryBuilder(ResourceBundleManager.getSqlString(ADMIN_TAKEN_BOOKS_CLEAR));
        qb.addPagination(limit, offset);
        Map<Integer,TakenBooks> books = new HashMap<>();
        try {
            ResultSet resultSet = qb.execute();
            while(resultSet.next()){
                TakenBooks takenBooks = mapper.mapGet(resultSet);
                Author author = authorMapper.mapGet(resultSet);
                if(books.get(takenBooks.getId())!=null){
                    takenBooks = books.get(takenBooks.getId());
                    takenBooks.setAuthors(takenBooks.getAuthors()+", "+author.toString());
                }else{
                    takenBooks.setAuthors(author.toString());
                    books.put(takenBooks.getId(),takenBooks);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<TakenBooks> takenBooks = new ArrayList<>(books.values());
        logger.info("found taken books: "+takenBooks.size());
        return takenBooks;
    }

    @Override
    public void save(TakenBooks takenBooks) {

    }

    @Override
    public void update(TakenBooks takenBooks, String[] params) {

    }

    @Override
    public void delete(TakenBooks takenBooks) {

    }

    @Override
    public void close() throws Exception {

    }
    public int getCount(String queryCount) {
        int count=0;
        logger.info("takenBooks count....." + queryCount);
        try (Connection connection = ConnectionPoolHolder.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareCall(queryCount)) {
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

    public List<TakenBooks> getAllPaginateByUserId(int userId, int limit, int offset) {
        QueryBuilder qb = new QueryBuilder(ResourceBundleManager.getSqlString(TAKEN_BOOKS_BY_USER_ID_CLEAR)
                .replace("?",""+userId));
        qb.addPagination(limit, offset);
        Map<Integer,TakenBooks> books = new HashMap<>();
        try {
            logger.info("executing query: "+qb.getQuery());
            ResultSet resultSet = qb.execute();
            while(resultSet.next()){
                TakenBooks takenBooks = mapper.mapGet(resultSet);
                Author author = authorMapper.mapGet(resultSet);
                if(books.get(takenBooks.getId())!=null){
                    takenBooks = books.get(takenBooks.getId());
                    takenBooks.setAuthors(takenBooks.getAuthors()+", "+author.toString());
                }else{
                    takenBooks.setAuthors(author.toString());
                    books.put(takenBooks.getId(),takenBooks);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<TakenBooks> takenBooks = new ArrayList<>(books.values());
        logger.info("found taken books: "+takenBooks.size());
        return takenBooks;
    }
}

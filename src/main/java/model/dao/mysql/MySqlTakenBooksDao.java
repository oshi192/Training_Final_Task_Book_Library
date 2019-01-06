package model.dao.mysql;

import config.ResourceBundleManager;
import controller.util.QueryBuilder;
import model.connectionpool.ConnectionPoolHolder;
import model.dao.TakenBooksDao;
import model.dao.mapper.AuthorMapper;
import model.dao.mapper.BookMapper;
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
    private static final String ADMIN_TAKEN_BOOKS_COUNT = "adim-taken-books-count";
    private static final String ADMIN_TAKEN_BOOKS_CLEAR = "adim-taken-books";

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
    public int getCount() {
        int count=0;
        String query = ResourceBundleManager.getSqlString(ADMIN_TAKEN_BOOKS_COUNT);
        logger.info("takenBooks count....." + query);
        try (Connection connection = ConnectionPoolHolder.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareCall(query)) {
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

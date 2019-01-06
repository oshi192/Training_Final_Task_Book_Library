package model.dao.impl;

import config.ResourceBundleManager;
import model.dao.TakenBookDao;
import model.dao.mapper.TakenBookMapper;
import model.dao.mapper.UserTakenBookMapper;
import model.entity.Author;
import model.entity.TakenBook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCTakenBookDao implements TakenBookDao {
    private static final String GET_ALL_PAGINATE = "takenBooks-getAll";
    private Connection connection;

    public JDBCTakenBookDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(TakenBook entity) {

    }

    @Override
    public void update(TakenBook takenBook, String[] params) {

    }

    @Override
    public void delete(TakenBook takenBook) {

    }


    @Override
    public Optional<TakenBook> get(int id) {
        return Optional.empty();
    }

    @Override
    public List<TakenBook> getAll() {
        return null;
    }



    public List<TakenBook> findAll(int shift) {
        String sqlRequest = ResourceBundleManager.getSqlString(GET_ALL_PAGINATE);
//        int maxBookon = ResourceBundleManager.getSqlString(GET_ALL_PAGINATE);
//        String sqlRequest = ResourceBundleManager.getSqlString(GET_ALL_PAGINATE);
        List<TakenBook> result = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareCall(sqlRequest)) {
            ResultSet rs;
            ps.setInt(1, 10);
            ps.setInt(2, shift);
            rs = ps.executeQuery();
            TakenBookMapper mapper = new TakenBookMapper();
            while (rs.next()) {
                TakenBook takenBook = mapper.mapGet(rs);
                result.add(takenBook);
//                List<Author> authors = new JDBCAuthorDao(connection)
//                        .findAllAuthorsByBookId(takenBook.getBook().getId());
//                takenBook.setAuthors(authors);
            }
            close();
            JDBCAuthorDao authorDao = new JDBCAuthorDao(connection);
            for(TakenBook takenBook : result) {
                List<Author> authors = authorDao.findAllAuthorsByBookId(takenBook.getBook().getId());
                takenBook.setAuthors(authors);
            }
            authorDao.close();
        } catch (Exception ex) {
            //todo my exception
            throw new RuntimeException(ex);
        }
        return result;
    }


    @Override
    public void close() {

    }

    public List<TakenBook> findAllByUserId(int shift, int id) {
        String sqlRequest = ResourceBundleManager.getSqlString("user-my-book");
        System.out.println("sqlRequest " + sqlRequest);
        List<TakenBook> result = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareCall(sqlRequest)) {
            ResultSet rs;
            ps.setInt(1, id);
            ps.setInt(2, 10);
            ps.setInt(3, shift);
            rs = ps.executeQuery();
            UserTakenBookMapper mapper = new UserTakenBookMapper();
            while (rs.next()) {
                TakenBook takenBook = mapper.mapGet(rs);
                result.add(takenBook);
            }
        } catch (Exception ex) {
            //todo my exception
            throw new RuntimeException(ex);
        }
//        for (TakenBook tb : result) {
//            List<Author> authors = new JDBCAuthorDao(connection)
//                    .findAllAuthorsByBookId(tb.getBook().getId());
//            tb.setAuthors(authors);
//            tb.setAuthors(authors);
//        }
        return result;
    }
}

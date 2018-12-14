package model.dao.impl;

import model.dao.TakenBookDao;
import model.dao.mapper.TakenBookMapper;
import model.dao.mapper.UserTakenBookMapper;
import model.entity.Author;
import model.entity.TakenBook;
import util.ResourceBundleManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class JDBCTakenBookDao implements TakenBookDao {
    private static final String GET_ALL_PAGINATE = "takenBooks-getAll";
    private Connection connection;

    public JDBCTakenBookDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(TakenBook entity) {

    }

    @Override
    public TakenBook findById(int id) {
        return null;
    }


    @Override
    public List<TakenBook> findAll(int shift) {
        String sqlRequest = ResourceBundleManager.getSqlString(GET_ALL_PAGINATE);
//        int maxBookon = ResourceBundleManager.getSqlString(GET_ALL_PAGINATE);
//        String sqlRequest = ResourceBundleManager.getSqlString(GET_ALL_PAGINATE);
        List<TakenBook> result = new ArrayList<>();
        try(PreparedStatement ps = connection.prepareCall(sqlRequest)){
            ResultSet rs;
            ps.setInt(1,10);
            ps.setInt(2,shift);
            rs = ps.executeQuery();
            TakenBookMapper mapper = new TakenBookMapper();
            while (rs.next()){
                TakenBook takenBook = mapper.extractFromResultSet(rs);
                result.add(takenBook);
//                List<Author> authors = new JDBCAuthorDao(connection)
//                        .findAllAuthorsByBookId(takenBook.getBook().getId());
//                takenBook.setAuthors(authors);
            }
        }catch (Exception ex){
            //todo my exception
            throw new RuntimeException(ex);
        }
        return result;
    }

    @Override
    public void update(TakenBook entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close() {

    }

    public List<TakenBook> findAllByUserId(int shift, int id) {
        String sqlRequest = ResourceBundleManager.getSqlString("user-my-book");
        System.out.println("sqlRequest "+sqlRequest);
        List<TakenBook> result = new ArrayList<>();
        try(PreparedStatement ps = connection.prepareCall(sqlRequest)){
            ResultSet rs;
            ps.setInt(1,id);
            ps.setInt(2,10);
            ps.setInt(3,shift);
            rs = ps.executeQuery();
            UserTakenBookMapper mapper = new UserTakenBookMapper();
            while (rs.next()){
                TakenBook takenBook = mapper.extractFromResultSet(rs);
                result.add(takenBook);
//                List<Author> authors = new JDBCAuthorDao(connection)
//                        .findAllAuthorsByBookId(takenBook.getBook().getId());
//                takenBook.setAuthors(authors);
            }
        }catch (Exception ex){
            //todo my exception
            throw new RuntimeException(ex);
        }
        return null;
    }
}

package model.dao.mysql;

import config.ResourceBundleManager;
import controller.util.QueryBuilder;
import model.connectionpool.ConnectionPoolHolder;
import model.dao.UserDao;
import model.dao.mapper.UserMapper;
import model.entity.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class MySqlUserDao implements UserDao {
    final static Logger logger = Logger.getLogger(MySqlUserDao.class);
    private String FIND_BY_EMAIL = "user-find-by-email";
    private String FIND_BY_ID = "user-find-by-id";
    private String SAVE = "user-insert";
    private static UserMapper mapper = new UserMapper();

    public MySqlUserDao() {

    }

    @Override
    public User findByEmail(String email) {
        User user = null;
        String query = ResourceBundleManager.getSqlString(FIND_BY_EMAIL).replace("?",email);
        logger.info("searching ny email....." + query);
        QueryBuilder queryBuilder = new QueryBuilder(query);
        try  {
            ResultSet rs = queryBuilder.execute();
            if (rs.next()) {
                user = mapper.mapGet(rs);
            }
            logger.info("success!...user:" + user);
        } catch (SQLException ex) {
            System.out.println(ex);
            logger.error("fail...");
            ex.printStackTrace();
            return null;
        }

        return user;
    }


    @Override
    public User get(int id) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public List<User> getAllPaginate(int limit, int offset) {
        return null;
    }

    @Override
    public void save(User user) {
        String query = ResourceBundleManager.getSqlString(SAVE);
        try (Connection connection = ConnectionPoolHolder.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareCall(query);) {
            mapper.mapCreate(ps, user);
            ps.execute();
        } catch (SQLException ex) {
            logger.error("cannot save user: " + user.toString());
        }
    }
    public User findById(int id) {
        User result=null;
        String query = ResourceBundleManager.getSqlString(FIND_BY_ID);
        try(Connection connection = ConnectionPoolHolder.getDataSource().getConnection();
            PreparedStatement ps = connection.prepareCall(query);){
            ps.setInt( 1, id);
            ResultSet rs;
            rs = ps.executeQuery();
            if (rs.next()){
                result = mapper.mapGet(rs);
            }
        }catch (Exception ex){
            //todo my exception
            throw new RuntimeException(ex);
        }
        return result;
    }
    @Override
    public void update(User user) {

    }

    @Override
    public void delete(User user) {

    }

    @Override
    public void close() throws Exception {

    }


//    @Override
//    protected void prepareStatementForInsert(PreparedStatement statement, User object) throws PersistException {
//        try {
////            statement.setInt(1, object.getId());
//            statement.setString(6, object.getEmail());
//            statement.setString(1, object.getPhoneNumber());
//            statement.setString(2, object.getPassword());
//            statement.setString(3, object.getName());
//            statement.setString(4, object.getSurname());
//            statement.setString(5, object.getRole());
//        } catch (Exception e) {
//            throw new PersistException(e);
//        }
//    }
//
//    @Override
//    protected void prepareStatementForUpdate(PreparedStatement statement, User object) throws PersistException {
//        try {
//            statement.setInt(1, object.getId());
//            statement.setString(2, object.getEmail());
//            statement.setString(3, object.getPhoneNumber());
//            statement.setString(4, object.getPassword());
//            statement.setString(5, object.getName());
//            statement.setString(5, object.getSurname());
//            statement.setString(5, object.getRole());
//        } catch (Exception e) {
//            throw new PersistException(e);
//        }
//    }


}

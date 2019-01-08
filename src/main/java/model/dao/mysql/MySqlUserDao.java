package model.dao.mysql;

import config.ResourceBundleManager;
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

public class MySqlUserDao implements UserDao {
    final static Logger logger = Logger.getLogger(MySqlUserDao.class);

    private static UserMapper mapper = new UserMapper();

    public MySqlUserDao() {

    }

    @Override
    public User findByEmail(String email) {
        User user = null;
        try (Connection connection = ConnectionPoolHolder.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareCall(
                     ResourceBundleManager.getSqlString(ResourceBundleManager.FIND_BY_EMAIL))) {
            ps.setString(1, email);
            logger.info("search user by email:"+ps.toString());
            ResultSet rs = ps.executeQuery();
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
        try (Connection connection = ConnectionPoolHolder.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareCall(ResourceBundleManager.getSqlString(ResourceBundleManager.SAVE))) {
            mapper.mapCreate(ps, user);
            ps.execute();
        } catch (SQLException ex) {
            logger.error("cannot save user: " + user.toString());
        }
    }
    public User findById(int id) {
        User result=null;
        try(Connection connection = ConnectionPoolHolder.getDataSource().getConnection();
            PreparedStatement ps = connection.prepareCall(ResourceBundleManager.getSqlString(ResourceBundleManager.FIND_BY_ID));){
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

}

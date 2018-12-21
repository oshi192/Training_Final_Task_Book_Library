package model.dao.impl;

import model.dao.UserDao;
import model.dao.mapper.UserMapper;
import model.dao.mapper.UserTakenBookMapper;
import model.entity.TakenBook;
import model.entity.User;
import util.ResourceBundleManager;

import java.sql.*;
import java.util.*;

public class JDBCUserDao implements UserDao {
    private Connection connection;

    public JDBCUserDao(Connection connection) {
        this.connection=connection;
    }

    @Override
    public User findByEmail(String email) {
        User result = null;
        try(PreparedStatement ps = connection.prepareCall("SELECT * FROM users WHERE email = ?")){
            ps.setString( 1, email);
            ResultSet rs;
            rs = ps.executeQuery();
            UserMapper mapper = new UserMapper();
            if (rs.next()){
                result = mapper.extractFromResultSet(rs);
            }
        }catch (Exception ex){
            //todo my exception
            throw new RuntimeException(ex);
        }
        return result;
    }

    @Override
    public void create(User user) {
        try(PreparedStatement ps = connection.prepareStatement
                ("insert into library.users values(DEFAULT, ?,?, ?, ?, ?, ?);")){
            ps.setString(1, user.getEmail());
            ps.setString( 2, user.getPhoneNumber());
            ps.setString( 3, user.getName());
            ps.setString( 4, user.getSurname());
            ps.setString( 5, user.getPassword());
            ps.setString( 6, user.getRole().name());
            ps.execute();
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public User findById(int id) {
        Optional<User> result = Optional.empty();
        try(PreparedStatement ps = connection.prepareCall("SELECT * FROM users WHERE user_id = ?")){
            ps.setInt( 1, id);
            ResultSet rs;
            rs = ps.executeQuery();
            UserMapper mapper = new UserMapper();
            if (rs.next()){
                result = Optional.of(mapper.extractFromResultSet(rs));
            }
        }catch (Exception ex){
            //todo my exception
            throw new RuntimeException(ex);
        }
        return result.get();
    }

    @Override
    public List<User> findAll(int offset, int limit) {
        return null;
    }


    public List<User> findAllUsersWithBook(int offset) {
//        String sqlRequest = ResourceBundleManager.getSqlString("all-users-with-book");
//        System.out.println("sqlRequest " + sqlRequest);
//        List<TakenBook> result = new ArrayList<>();
//        try (PreparedStatement ps = connection.prepareCall(sqlRequest)) {
//            ResultSet rs;
//            ps.setInt(1, id);
//            ps.setInt(2, 10);
//            ps.setInt(3, shift);
//            rs = ps.executeQuery();
//            UserTakenBookMapper mapper = new UserTakenBookMapper();
//            while (rs.next()) {
//                TakenBook takenBook = mapper.extractFromResultSet(rs);
//                result.add(takenBook);
//            }
//        } catch (Exception ex) {
//            //todo my exception
//            throw new RuntimeException(ex);
//        }
        return null;
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

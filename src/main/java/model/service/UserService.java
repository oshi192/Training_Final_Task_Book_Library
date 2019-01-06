package model.service;

import model.dao.DaoFactory;
import model.dao.mysql.MySqlUserDao;
import model.entity.User;

import java.sql.SQLException;
import java.util.List;

public class UserService {
    private DaoFactory daoFactory = DaoFactory.getInstance();
    public UserService(){}
    public void create(User user){

    }
    public List<User> getAllUser() throws SQLException, ClassNotFoundException {
        return null;
    }

    public User getUser(String email) throws SQLException, ClassNotFoundException {
        return daoFactory.createUserDao().findByEmail(email);
    }
}

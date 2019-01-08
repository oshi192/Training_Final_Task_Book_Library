package model.service;

import model.dao.mysql.MySqlUserDao;
import model.entity.User;

import java.sql.SQLException;

public class UserService {
    public UserService(){}
    public void create(User user){

    }

    public User getUser(String email) throws SQLException, ClassNotFoundException {
        return new MySqlUserDao().findByEmail(email);
    }
}

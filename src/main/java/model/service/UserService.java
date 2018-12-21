package model.service;

import model.dao.DaoFactory;
import model.dao.UserDao;
import model.dao.impl.JDBCUserDao;
import model.entity.User;

import java.sql.SQLException;
import java.util.List;

public class UserService {
    private DaoFactory daoFactory = DaoFactory.getInstance();
    public UserService(){}
    public void create(User user){

    }
    public List<User> getAllUser() throws SQLException, ClassNotFoundException {
        try (UserDao dao = daoFactory.createUserDao()) {
            return dao.findAll(0,0);//todo
        }
    }

    public User getUser(String email) throws SQLException, ClassNotFoundException {
        return daoFactory.createUserDao().findByEmail(email);
    }
}

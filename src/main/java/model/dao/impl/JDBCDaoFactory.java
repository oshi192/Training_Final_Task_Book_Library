package model.dao.impl;

import model.connectionpool.ConnectionPoolHolder;
import model.dao.*;
import model.dao.mysql.MySqlUserDao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {

    private DataSource dataSource = ConnectionPoolHolder.getDataSource();

    private Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public AuthorDao createAuthorDao() {
        return new JDBCAuthorDao(getConnection());
    }

    @Override
    public BookDao createBookDao() {
        return new JDBCBookDao(getConnection());
    }

    @Override
    public KeyWordDao createKeyWordDao() {
        return new JDBCKeyWordDao();
    }

    @Override
    public UserDao createUserDao() {
        return new MySqlUserDao();
    }
}

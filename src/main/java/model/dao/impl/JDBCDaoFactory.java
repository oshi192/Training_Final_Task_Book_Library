package model.dao.impl;

import model.dao.*;
import util.ConnectionPoolHolder;


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
        return new JDBCKeyWordDao(getConnection());
    }

    @Override
    public UserDao createUserDao() {
        return new JDBCUserDao(getConnection());
    }
}

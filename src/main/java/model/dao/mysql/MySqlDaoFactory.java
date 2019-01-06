package model.dao.mysql;

import config.ResourceBundleManager;
import model.dao.*;
import model.entity.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class MySqlDaoFactory extends DaoFactory {
    private String DB_LOGIN="db.user";
    private String DB_PASSWORD="db.password";
    private String DB_URL="db.url";
    private String DB_DRIVER="db.driver";

    private String user = ResourceBundleManager.getConfig(DB_LOGIN);//Логин пользователя
    private String password = ResourceBundleManager.getConfig(DB_PASSWORD);//Пароль пользователя
    private String url = ResourceBundleManager.getConfig(DB_URL);//URL адрес
    private String driver = ResourceBundleManager.getConfig(DB_DRIVER);//Имя драйвера
//    private Map<Class, DaoCreator> creators;

    public Connection getContext()  {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {

        }
        return  connection;
    }

    public GenericDao getDao(Connection connection, Class dtoClass) {
//        DaoCreator creator = creators.get(dtoClass);
//        if (creator == null) {
//
//        }
        return null;//creator.create(connection);
    }

    public MySqlDaoFactory() {
        try {
            Class.forName(driver);//Регистрируем драйвер
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

//        creators = new HashMap<Class, DaoCreator>();
//        creators.put(User.class, new DaoCreator<Connection>() {
//            @Override
//            public GenericDao create(Connection connection) {
//                return new MySqlUserDao();
//            }
//        });
    }

    @Override
    public AuthorDao createAuthorDao() {
        return null;
    }

    @Override
    public BookDao createBookDao() {
        return null;
    }

    @Override
    public KeyWordDao createKeyWordDao() {
        return null;
    }

    @Override
    public UserDao createUserDao() {
        return null;
    }
}

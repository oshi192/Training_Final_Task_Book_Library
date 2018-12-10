package controller.command;


import model.dao.impl.JDBCUserDao;
import model.entity.User;
import util.Configuration;
import util.ConnectionPoolHolder;
import util.Md5;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;


public class LoginSubmitCommand implements Command {

    private static final String EMAIL_HEADER = "email";
    private static final String PASSWORD_HEADER = "password";
    private static final String ATTR_NAME_ERROR_MESSAGE = "errorMessage";

    @Override
    public String execute(HttpServletRequest request,HttpServletResponse response) {
        String page;
        String username = request.getParameter(EMAIL_HEADER);
        String passwordRaw = request.getParameter(PASSWORD_HEADER);
        System.out.println(username+" |"+passwordRaw+"|"+Md5.md5Password(passwordRaw));
        User user = checkUser(username, passwordRaw, request);

        if (Objects.nonNull(user)) {

            request.getSession().setAttribute("user", user);
            //page = new SearchpageCommand().execute(request);
            page = new RegisterCommand().execute(request,response);
            request.setAttribute(ATTR_NAME_ERROR_MESSAGE, "");

        } else {

            page = Configuration.getProperty(
                    Configuration.LOGIN_PAGE_PATH);
        }
        return page;
    }

    public User checkUser(String email, String insertedPassword, HttpServletRequest request) {
        Connection connection = null;
        try {
            connection = ConnectionPoolHolder.getDataSource().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        User user = new JDBCUserDao(connection).findByEmail(email).get();
        String ATTR_NAME_ERROR_MESSAGE = "errorMessage";
        if (Objects.isNull(user)) {
            request.setAttribute(ATTR_NAME_ERROR_MESSAGE, "No user found");
            return user;
        }
        String savedPassword = user.getPassword();
        System.out.println("savedPassword: "+savedPassword);
        if (savedPassword.equals(insertedPassword)) {
            //todo md5
            System.out.println("equals password :"+Md5.md5Password(insertedPassword));
            return user;
        } else {
            System.out.println("not equals password "+Md5.md5Password(insertedPassword));
            request.setAttribute(ATTR_NAME_ERROR_MESSAGE, "Wrong password");
            return null;
        }
    }

}

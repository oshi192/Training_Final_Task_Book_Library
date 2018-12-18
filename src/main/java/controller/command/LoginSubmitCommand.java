package controller.command;


import model.dao.impl.JDBCUserDao;
import model.entity.User;
import util.Configuration;
import util.ConnectionPoolHolder;
import util.Md5;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;


public class LoginSubmitCommand implements Command {

    private static final String EMAIL_HEADER = "email";
    private static final String PASSWORD_HEADER = "password";
    private static final String ATTR_NAME_ERROR_MESSAGE = "errorMessage";

    @Override
    public String execute(HttpServletRequest request,HttpServletResponse response) {
        System.out.println("------- starting login submit -------");
        HttpSession session = request.getSession(false);
        String loginURI = request.getContextPath() + "/login";

        boolean loggedIn = session != null && session.getAttribute("user") != null;
        boolean loginRequest = request.getRequestURI().equals(loginURI);
        System.out.println("\tloggedIn "+loggedIn+" loginRequest "+"  session != null "+( session != null));
        System.out.println("------- ending login submit -------");
        String page;
        String username = request.getParameter(EMAIL_HEADER);
        String passwordRaw = request.getParameter(PASSWORD_HEADER);
        System.out.println(username+" |"+passwordRaw+"|"+Md5.md5Password(passwordRaw));
        User user = checkUser(username, passwordRaw, request);
        if (Objects.nonNull(user)) {
            user.setPassword("");
            request.getSession().setAttribute("user", user);
            System.out.println("\tset user in session: ");
            page = new HomepageCommand().execute(request,response);
            System.out.println("\tgo to page "+page);
            request.setAttribute(ATTR_NAME_ERROR_MESSAGE, "");
        } else {
            page = Configuration.getProperty(Configuration.LOGIN_PAGE_PATH);
        }
        return page;
    }

    public User checkUser(String email, String insertedPassword, HttpServletRequest request) {
        Connection connection = null;
        try {
            connection = ConnectionPoolHolder.getDataSource().getConnection();
            User user = new JDBCUserDao(connection).findByEmail(email);
            if (Objects.isNull(user)) {
                request.setAttribute(ATTR_NAME_ERROR_MESSAGE, "No user found");
                return user;
            }
            String savedPassword = user.getPassword();
            System.out.println("\tsavedPassword: "+savedPassword);
            if (savedPassword.equals(insertedPassword)) {
                //todo md5
                System.out.println("\tequals password :"+Md5.md5Password(insertedPassword));
                return user;
            } else {
                System.out.println("\tnot equals password "+Md5.md5Password(insertedPassword));
                request.setAttribute(ATTR_NAME_ERROR_MESSAGE, "Wrong password");
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}

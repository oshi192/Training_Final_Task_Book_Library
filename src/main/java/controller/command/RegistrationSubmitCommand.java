package controller.command;

import model.dao.impl.JDBCUserDao;
import model.entity.User;
import util.Configuration;
import util.ConnectionPoolHolder;
import util.ResourceBundleManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

public class RegistrationSubmitCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("******* RegistrationSubmitCommand *******");
        String page = null;
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm-password");
        String firstName = request.getParameter("first-name");
        String surname = request.getParameter("surname");
        String phone = request.getParameter("phone-number");

        User user = new User();
        user.setRole(User.Role.USER);
        user.setPassword(password);
        user.setName(firstName);
        user.setSurname(surname);
        user.setEmail(email);
        user.setPhoneNumber(phone);
        System.out.println("user from registration page: " + user.toString());
        if (Objects.nonNull(password) && password.equals(confirmPassword)) {
            try {
                JDBCUserDao userDao = new JDBCUserDao(ConnectionPoolHolder.getDataSource().getConnection());
                User userTmp = userDao.findByEmail(user.getEmail());
                if (Objects.isNull(userTmp)) {
                    userDao.create(user);
                    request.setAttribute("message", ResourceBundleManager.getMessage("msg-registration-successful"));
                    page = "redirect:login";
                    System.out.println("msg-registration-successful");
                } else {
                    request.setAttribute("errorMessage", ResourceBundleManager.getMessage("error-already-register"));
                    page = Configuration.getProperty(Configuration.REGISTRATION_PAGE_PATH);
                }
            } catch (SQLException e) {
                page = Configuration.getProperty(Configuration.REGISTRATION_PAGE_PATH);
                e.printStackTrace();
            }

        } else {
            request.setAttribute("newUser", user);
            request.setAttribute("errorMessage", ResourceBundleManager.getMessage("error-not-equals-passwords"));
            page = Configuration.getProperty(Configuration.REGISTRATION_PAGE_PATH);
            System.out.println("msg-registration-fail");
        }
        System.out.println("******* end RegistrationSubmitCommand *******");
        return page;
    }
}

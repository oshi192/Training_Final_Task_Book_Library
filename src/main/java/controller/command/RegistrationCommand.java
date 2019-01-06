package controller.command;

import config.ResourceBundleManager;
import model.dao.mysql.MySqlUserDao;
import model.entity.User;
import util.Configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class RegistrationCommand implements Command {
    @Override
    public String executeGet(HttpServletRequest request,HttpServletResponse response) {
        return Configuration.getProperty(Configuration.REGISTRATION_PAGE_PATH);
    }

    @Override
    public String executePost(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("******* RegistrationSubmitCommand *******");
        String page = null;
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm-password");
        String firstName = request.getParameter("first-name");
        String surname = request.getParameter("surname");
        String phone = request.getParameter("phone-number");

        User user = new User();
        user.setRole(User.Role.USER.name());
        user.setPassword(password);
        user.setName(firstName);
        user.setSurname(surname);
        user.setEmail(email);
        user.setPhoneNumber(phone);
        System.out.println("user from registration page: " + user.toString());
        if (Objects.nonNull(password) && password.equals(confirmPassword)) {
            MySqlUserDao userDao = new MySqlUserDao();
            User userTmp = userDao.findByEmail(user.getEmail());
            if (Objects.isNull(userTmp)) {
                userDao.save(user);
                request.setAttribute("message", ResourceBundleManager.getMessage("msg-registration-successful"));
                page = "redirect:login";
                System.out.println("msg-registration-successful");
            } else {
                request.setAttribute("errorMessage", ResourceBundleManager.getMessage("error-already-register"));
                page = Configuration.getProperty(Configuration.REGISTRATION_PAGE_PATH);
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

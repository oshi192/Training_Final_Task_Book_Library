package controller.command;

import controller.util.LogInOutUtils;
import model.dao.mysql.MySqlUserDao;
import model.entity.User;
import org.apache.log4j.Logger;
import util.Configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class LogInCommand implements Command {
    private static Logger logger = Logger.getLogger(LogInCommand.class);
    private static LogInOutUtils utils = new LogInOutUtils();

    @Override
    public String executeGet(HttpServletRequest request, HttpServletResponse response) {

        return Configuration.getProperty(Configuration.LOGIN_PAGE_PATH);
    }

    @Override
    public String executePost(HttpServletRequest request, HttpServletResponse response) {
        String page = null;
        String email = request.getParameter("email");
        String pass = request.getParameter("password");
        logger.info(request.getParameterNames().toString());
        logger.info("email = " + email + " pass = " + pass);
        Map<String, String> messages = new HashMap<>();
        if (email == null || email.isEmpty()) {
            messages.put("email", "Please enter email");
        }
        if (pass == null || pass.isEmpty()) {
            messages.put("password", "Please enter password");
        }
        if (messages.isEmpty()) {
            User user = new MySqlUserDao()
                    .findByEmail(email);
            logger.info("found user:" + user);
            if (user != null) {
                if (user.getPassword().equals(pass)) {
                    user.setPassword("");
                    logIn(request, user);
                    //request.getSession().setAttribute("user", user);
                    page = "redirect:";
                } else {
                    messages.put("password", "Wrong password! please try again");
                    page = Configuration.getProperty(Configuration.LOGIN_PAGE_PATH);
                }
            } else {
                messages.put("email", "Unknown email, please try again");
                page = Configuration.getProperty(Configuration.LOGIN_PAGE_PATH);
            }
        }

        request.setAttribute("messages", messages);
        logger.info("errorMessages: " + messages.toString());
        return page;
    }

    //todo rename or remove
    private void logIn(HttpServletRequest request, User user) {
        Map<Integer, HttpSession> loggedUsers = utils.getLoggedUsers();
        destroyPreviousSession(loggedUsers, user.getId());
        loggedUsers.put(user.getId(), request.getSession());
        utils.setLoggedUsers(loggedUsers);
        sessionSetup(request, user);
    }

    private void destroyPreviousSession(Map<Integer, HttpSession> loggedUsers, int userId) {
        if (loggedUsers.containsKey(userId)) {
            loggedUsers.get(userId).invalidate();//todo read more about
        }
    }

    private void sessionSetup(HttpServletRequest request, User user) {
        HttpSession session = request.getSession();
        session.setAttribute("userId", user.getId());
        session.setAttribute("role", user.getRole());
        session.setAttribute("firstName", user.getName());
        session.setAttribute("surName", user.getSurname());
        session.setAttribute("user", user);
    }
}


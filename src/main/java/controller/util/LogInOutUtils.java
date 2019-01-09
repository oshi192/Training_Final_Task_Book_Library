package controller.util;

import controller.servlets.LibraryServlet;
import model.entity.User;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LogInOutUtils {
    public void logOut(HttpSession session) {
        Map<Integer, HttpSession> loggedUsers = getLoggedUsers();
        loggedUsers.remove(getUserId(session));
        setLoggedUsers(loggedUsers);
        session.removeAttribute("user");
    }
    private static ServletContext context = LibraryServlet.getContext();

    private Integer getUserId(HttpSession session) {
        User user = (User)session.getAttribute("user");
        return user==null?0:user.getId();
    }
    public void setLoggedUsers(Map<Integer, HttpSession> loggedUsers) {
        context.setAttribute("loggedUsers", loggedUsers);
    }
    public Map<Integer, HttpSession> getLoggedUsers() {
        return (ConcurrentHashMap<Integer, HttpSession>) context.getAttribute("loggedUsers");
    }
}

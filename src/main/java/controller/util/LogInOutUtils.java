package controller.util;

import controller.servlets.AddServlet;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class LogInOutUtils {
    public void logOut(HttpSession session) {
        Map<Integer, HttpSession> loggedUsers = getLoggedUsers();
        loggedUsers.remove(getUserId(session));
        setLoggedUsers(loggedUsers);
        session.removeAttribute("userId");
    }
    private static ServletContext context = AddServlet.getContext();

    private Integer getUserId(HttpSession session) {
        return (int) Optional.ofNullable(session.getAttribute("userId")).orElse(0);
    }
    public void setLoggedUsers(Map<Integer, HttpSession> loggedUsers) {
        context.setAttribute("loggedUsers", loggedUsers);
    }
    public Map<Integer, HttpSession> getLoggedUsers() {
        return (ConcurrentHashMap<Integer, HttpSession>) context.getAttribute("loggedUsers");
    }
}

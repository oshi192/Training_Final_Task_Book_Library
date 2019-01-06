package controller.util;

import controller.servlets.AddServlet;
import model.entity.User;
import model.service.UserService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import java.util.HashSet;
import java.util.Set;

public class ServletUtil {
    private static ServletContext context = AddServlet.getContext();

    public static void saveUserDataToSession(HttpServletRequest request, User.Role role, String name, int id) {
        HttpSession session = request.getSession();
        session.setAttribute("userId", id);
        session.setAttribute("userEmail", name);
        session.setAttribute("userRole", role);
    }

    public static void setUser(HttpServletRequest request, String name) throws Exception {
        HttpSession session = request.getSession();
        User user = new UserService().getUser(name);
        session.setAttribute("user", user);
    }

    public static void logIn(HttpServletRequest request, String userEmail){
        Set<String> loggedUsers = getLoggedUsers(request);
        loggedUsers.add(userEmail);
        setLoggedUsers(request, loggedUsers);
    }

    public static void logOut(HttpServletRequest request, String userName){
        Set<String> loggedUsers = getLoggedUsers(request);
        loggedUsers.remove(userName);
        setLoggedUsers(request, loggedUsers);
    }

    private static Set<String> getLoggedUsers(HttpServletRequest request) {
        return (Set<String>) context.getAttribute("loggedUsers");
    }

    public static Set<String> getLoggedUsers(HttpSessionEvent httpSessionEvent) {
        return (HashSet<String>) httpSessionEvent.getSession().getServletContext()
                .getAttribute("loggedUsers");
    }

    private static void setLoggedUsers(HttpServletRequest request, Set<String> loggedUsers){
        request.getServletContext()
                .setAttribute("loggedUsers", loggedUsers);
    }

    public static void setLoggedUsers(HttpSession session, Set<String> loggedUsers){
        session.getServletContext()
                .setAttribute("loggedUsers", loggedUsers);
    }

    public static String getUserName(HttpSession session){
        return (String) session.getAttribute("userName");
    }

}

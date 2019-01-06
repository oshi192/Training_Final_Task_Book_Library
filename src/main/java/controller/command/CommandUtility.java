package controller.command;

import model.dao.mysql.MySqlUserDao;
import model.entity.User;
import model.entity.User.Role;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

class CommandUtility {
    static void setUserRole(HttpServletRequest request,
                            User.Role role, String email) {
        HttpSession session = request.getSession();
        ServletContext context = request.getServletContext();
        context.setAttribute("userEmail", email);
        session.setAttribute("role", role);
    }

    public static String findUserAndGetRole(String email, String pass) {

        MySqlUserDao userDao = new MySqlUserDao();
        User user = userDao.findByEmail(email);
        if (user.getPassword().equals(pass)) {
            return user.getRole();
        }

        return Role.GUEST.name();
    }

}

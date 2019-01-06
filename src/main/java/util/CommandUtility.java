package util;

import model.dao.mysql.MySqlUserDao;
import model.entity.User;
import model.entity.User.Role;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CommandUtility {
    public static void setUserRole(HttpServletRequest request,
                                   String role, String email) {
        HttpSession session = request.getSession();
        ServletContext context = request.getServletContext();
        context.setAttribute("userEmail", email);
        session.setAttribute("role", role);
    }

    public static String findUserAndGetRole(String email,String pass){
        try {//todo
            MySqlUserDao userDao = new MySqlUserDao();
            User user = userDao.findByEmail(email);
            if(user.getPassword().equals(pass))return user.getRole();
        } catch (Exception e) {
            //todo logger and my exception
            e.printStackTrace();
        }
        return Role.GUEST.name() ;
    }

}

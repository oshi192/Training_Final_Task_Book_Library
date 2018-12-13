package controller.command;

import model.dao.impl.JDBCUserDao;
import model.entity.User;
import model.entity.User.Role;
import util.ConnectionPoolHolder;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.HashSet;

class CommandUtility {
    static void setUserRole(HttpServletRequest request,
                            User.Role role, String email) {
        HttpSession session = request.getSession();
        ServletContext context = request.getServletContext();
        context.setAttribute("userEmail", email);
        session.setAttribute("role", role);
    }

    public static Role findUserAndGetRole(String email,String pass){
        try {
            JDBCUserDao userDao = new JDBCUserDao(ConnectionPoolHolder.getDataSource().getConnection());
            User user = userDao.findByEmail(email);
            if(user.getPassword().equals(pass))return user.getRole();
        } catch (SQLException e) {
            //todo logger and my exception
            e.printStackTrace();
        }
        return Role.GUEST;
    }

}

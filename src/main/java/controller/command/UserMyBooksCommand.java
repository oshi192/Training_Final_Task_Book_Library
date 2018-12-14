package controller.command;

import model.dao.impl.JDBCTakenBookDao;
import model.entity.TakenBook;
import model.entity.User;
import util.ConnectionPoolHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class UserMyBooksCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            System.out.println("admin taken books");
            User user = (User) request.getSession().getAttribute("user");
            List<TakenBook> list = new JDBCTakenBookDao(ConnectionPoolHolder
                    .getDataSource().getConnection())
                    .findAllByUserId(0, user.getId());
            request.setAttribute("UserTakenBooks", list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "/jsp/user/mybooks.jsp";
    }


}

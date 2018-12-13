package controller.command;

import model.dao.impl.JDBCBookAndAuthors;
import model.entity.BookAndAuthors;
import model.entity.User;
import util.ConnectionPoolHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

public class CabinetCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        String page = ("/" + user.getRole().name().toLowerCase()).replaceAll("/guest", "");
        try {
            List<BookAndAuthors> list= new JDBCBookAndAuthors(ConnectionPoolHolder.getDataSource().getConnection())
                    .findAllbookAndAuthor();
            request.setAttribute("BookAndAuthors",list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "/jsp" + page + "/book-list.jsp";
    }
}

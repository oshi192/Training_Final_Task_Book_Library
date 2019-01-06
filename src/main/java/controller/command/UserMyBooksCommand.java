package controller.command;

import model.dao.impl.JDBCTakenBookDao;
import model.entity.TakenBook;
import model.entity.User;
import util.Configuration;
import util.ConnectionPoolHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserMyBooksCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<TakenBook> list=new ArrayList<>();
        try {
            System.out.println("user taken books");
            User user = (User) request.getSession().getAttribute("user");
            list = new JDBCTakenBookDao(ConnectionPoolHolder
                    .getDataSource().getConnection())
                    .findAllByUserId(0, user.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("UserTakenBooks", list);
        return Configuration.getProperty(Configuration.USER_MYBOOK_PATH);
    }


}

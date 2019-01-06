package controller.command;

import model.connectionpool.ConnectionPoolHolder;
import model.dao.impl.JDBCTakenBookDao;
import model.entity.TakenBook;
import util.Configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class AdminTakenBooksCommand implements Command {
    @Override
    public String execute(HttpServletRequest request,HttpServletResponse response) {
        try {
            System.out.println("admin taken books");
            List<TakenBook> list= new JDBCTakenBookDao(ConnectionPoolHolder
                    .getDataSource().getConnection())
                    .findAll(0);
            request.setAttribute("TakenBooks", list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Configuration.getProperty(Configuration.ADMIN_TAKENBOOKS_PATH);
    }

}

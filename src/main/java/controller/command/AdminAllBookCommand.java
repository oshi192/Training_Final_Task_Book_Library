package controller.command;

import model.dao.impl.JDBCBookDao;
import model.dao.impl.JDBCUserDao;
import model.entity.Book;
import util.Configuration;
import util.ConnectionPoolHolder;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AdminAllBookCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int page = 1;
        int recordsPerPage = 10;
        if(request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        int noOfRecords = 12;
        try {
            noOfRecords = new JDBCBookDao(ConnectionPoolHolder.getDataSource().getConnection()).getCount();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        noOfRecords = 12;
        JDBCBookDao dao = null;
        try {
            dao = new JDBCBookDao(ConnectionPoolHolder.getDataSource().getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<Book> list = dao.findAll((page-1)*recordsPerPage,recordsPerPage);

        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        System.out.println("count: "+noOfRecords+" recordsPerPage:"+recordsPerPage+" noOfPages:"+noOfPages+" page:"+page);
        request.setAttribute("bookList", list);
        request.setAttribute("numOfPages", noOfPages);
        request.setAttribute("currentPage", page);
        return Configuration.getProperty(Configuration.ADMIN_ALLBOOKS_PATH);
    }
}

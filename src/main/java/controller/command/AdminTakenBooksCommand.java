package controller.command;

import model.dao.impl.JDBCBookDao;
import model.dao.impl.JDBCTakenBookDao;
import model.entity.Book;
import model.entity.TakenBook;
import org.apache.commons.lang3.StringUtils;
import util.Configuration;
import util.ConnectionPoolHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

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
//    JDBCBookDao bookdao = null;
//        try {
//        bookdao = new JDBCBookDao(ConnectionPoolHolder.getDataSource().getConnection());
//    } catch (SQLException e) {
//        e.printStackTrace();//todo throw my exception
//    }
//
//        if (Objects.isNull(request.getAttribute("books"))) {
//        List<Book> books;
//        String currentPage = request.getParameter("page");
//        int itemsOnPage = 10;
//        if (StringUtils.isNoneBlank(currentPage)) {
//            int page = Integer.parseInt(currentPage);
//            int offset = (page - 1) * 10;
//            books = bookdao.findAll(offset);
//        } else {
//            currentPage = "0";
//            books = bookdao.findAll(0);
//        }
//
//        int booksCount = bookdao.getCount();
//        int numberOfPages = (int) Math.ceil((double) booksCount / 10);
//
//        request.setAttribute("books", books);
//        request.setAttribute("numberOfPages", numberOfPages);
//        request.setAttribute("currentPage", currentPage);
//        request.setAttribute("itemsOnPage", itemsOnPage);
//    }
//
//    //        request = addBookListToSearch(request);
//    String page = Configuration.getProperty(
//            Configuration.BOOK_SEARCH_PAGE_PATH);
//        return page;
}

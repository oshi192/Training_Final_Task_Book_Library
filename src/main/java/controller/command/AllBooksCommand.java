package controller.command;

import config.ResourceBundleManager;
import controller.util.Pagination;
import controller.util.QueryBuilder;
import model.dao.mysql.MySqlBookDao;
import model.dao.mysql.MySqlTakenBooksDao;
import model.entity.Book;
import org.apache.log4j.Logger;
import util.Configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllBooksCommand implements Command {
    private static final Logger logger = Logger.getLogger(AllBooksCommand.class);
    private final static String BOOKS_WITH_AUTHORS_CLEAN = "BOOKS_WITH_AUTHORS_CLEAN";

    @Override
    public String executeGet(HttpServletRequest request, HttpServletResponse response) {

        int count = new MySqlBookDao().getCount();
        Pagination.addPagination(count, request, response);
        int limit = request.getParameter("recordsOnPage") == null ? 10 : Integer.parseInt(request.getParameter("recordsOnPage"));
        int offset = request.getParameter("currentPage") == null ? 0 : (Integer.parseInt(request.getParameter("currentPage")) - 1) * limit;
        List<Book> books = new MySqlBookDao().getAllPaginate(limit, offset);//todo remove
        request.setAttribute("Books", books);

        return Configuration.getProperty(Configuration.ALLBOOKS_PATH);
    }


    @Override
    public String executePost(HttpServletRequest request, HttpServletResponse response) {
        int bookId;
        if (request.getParameter("edit") != null) {
            bookId=Integer.parseInt(request.getParameter("edit"));
            logger.info("edit book: "+bookId);
            //new MySqlBookDao().update();
        }
        if (request.getParameter("remove") != null) {
            bookId=Integer.parseInt(request.getParameter("remove"));
            Book book=new Book();
            book.setId(bookId);
            logger.info("remove book: "+bookId);
            new MySqlBookDao().delete(book);
        }
        if (request.getParameter("take") != null) {
            bookId=Integer.parseInt(request.getParameter("take"));
            logger.info("take book: "+bookId);
            // todo new MySqlTakenBooksDao().take(bookId);
        }
        return executeGet(request,response);
    }
}

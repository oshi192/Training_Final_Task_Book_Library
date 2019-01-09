package controller.command;

import config.ResourceBundleManager;
import controller.util.Pagination;
import model.dao.mysql.MySqlBookDao;
import model.entity.Book;
import org.apache.log4j.Logger;
import controller.util.Configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.List;

public class BooksRequestsCommand implements Command {
    private final static Logger logger = Logger.getLogger(BooksRequestsCommand.class);

    @Override
    public String executeGet(HttpServletRequest request, HttpServletResponse response) {
        int count = new MySqlBookDao().getCount(
                ResourceBundleManager.getSqlString(ResourceBundleManager.ADMIN_BOKS_REQUESTS_COUNT));
        int limit = request.getParameter("recordsOnPage") == null ? 10 : Integer.parseInt(request.getParameter("recordsOnPage"));
        int offset = request.getParameter("currentPage") == null ? 0 : (Integer.parseInt(request.getParameter("currentPage")) - 1) * limit;
        Pagination.addPagination(count, request, response);
        List<Book> books = new MySqlBookDao().getAllPaginateWithStatus(Book.Status.REQUEST,limit,offset);
        request.setAttribute("BooksRequests", books);
        return Configuration.getProperty(Configuration.BOOKS_REQUESTS_PAGE_PATH);
    }

    @Override
    public String executePost(HttpServletRequest request, HttpServletResponse response) {
        Book book=null;
        if (request.getParameter("submit") != null) {
            logger.info("submit" + request.getParameter("submit"));
            book = new MySqlBookDao().get(Integer.parseInt(request.getParameter("submit")));
            if(book.getStatus()==Book.Status.REQUEST.id){
                book.setStatus(Book.Status.TAKEN.id);
                LocalDate date =LocalDate.now();
                book.setTakeBeginDate(date.toString());
                book.setTakeEndDate(date.plusMonths(1).toString());
                new MySqlBookDao().update(book);
                logger.info("update book:"+book);
            }
        }
        if (request.getParameter("denied") != null) {
            logger.info("denied" + request.getParameter("denied"));
             book = new MySqlBookDao().get(Integer.parseInt(request.getParameter("denied")));
            if(book.getStatus()==Book.Status.REQUEST.id){
                book.setStatus(Book.Status.FREE.id);
                new MySqlBookDao().update(book);
                logger.info("update book:"+book);
            }
        }
        logger.info("return to get method"+book);
        return executeGet(request, response);
    }
}

package controller.command;

import config.ResourceBundleManager;
import controller.util.Pagination;
import model.dao.mysql.MySqlBookDao;
import model.entity.Book;
import model.entity.User;
import org.apache.log4j.Logger;
import util.Configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class MyRequestsCommand implements Command {
    private final static Logger logger = Logger.getLogger(BooksRequestsCommand.class);

    @Override
    public String executeGet(HttpServletRequest request, HttpServletResponse response) {
        int userId = ((User) (request.getSession().getAttribute("user"))).getId();
        int count = new MySqlBookDao().getCount(
                ResourceBundleManager.getSqlString(ResourceBundleManager.BOOK_REQUEST_COUNT_BY_USER_ID));
        Pagination.addPagination(count, request, response);
        int limit = request.getParameter("recordsOnPage") == null ? 10 : Integer.parseInt(request.getParameter("recordsOnPage"));
        int offset = request.getParameter("currentPage") == null ? 0 : (Integer.parseInt(request.getParameter("currentPage")) - 1) * limit;
        List<Book> booksRequests = new MySqlBookDao().getAllWithStatusByUserId(userId,Book.Status.REQUEST, limit, offset);//todo remove
        request.setAttribute("BooksRequests", booksRequests);
        return Configuration.getProperty(Configuration.MY_REQUESTS_PAGE_PATH);
    }

    @Override
    public String executePost(HttpServletRequest request, HttpServletResponse response) {
        if (request.getParameter("remove") != null) {
            logger.info("remove" + request.getParameter("remove"));
            int bookId = Integer.parseInt(request.getParameter("remove"));
            Book book = new MySqlBookDao().get(bookId);
            int userId=((User) (request.getSession().getAttribute("user"))).getId();
            book.setUserId(userId);
            book.setStatus(Book.Status.FREE.id);
            new MySqlBookDao().update(book);
        }
        return executeGet(request, response);
    }
}

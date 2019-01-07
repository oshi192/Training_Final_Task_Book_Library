package controller.command;

import config.ResourceBundleManager;
import controller.util.Pagination;
import model.dao.mysql.MySqlBooksRequestDao;
import model.entity.BooksRequest;
import model.entity.User;
import org.apache.log4j.Logger;
import util.Configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class BooksRequestsCommand implements Command {
    private static final String ADMIN_BOKS_REQUEST_COUNT = "adim-books-request-count";
    private final static Logger logger = Logger.getLogger(BooksRequestsCommand.class);

    @Override
    public String executeGet(HttpServletRequest request, HttpServletResponse response) {
        int count = new MySqlBooksRequestDao().getCount(ResourceBundleManager.getSqlString(ADMIN_BOKS_REQUEST_COUNT));
        Pagination.addPagination(count, request, response);
        int limit = request.getParameter("recordsOnPage") == null ? 10 : Integer.parseInt(request.getParameter("recordsOnPage"));
        int offset = request.getParameter("currentPage") == null ? 0 : (Integer.parseInt(request.getParameter("currentPage")) - 1) * limit;
        List<BooksRequest> booksRequests = new MySqlBooksRequestDao().getAllPaginate(limit, offset);
        request.setAttribute("BooksRequests", booksRequests);
        return Configuration.getProperty(Configuration.BOOKS_REQUESTS_PAGE_PATH);
    }

    @Override
    public String executePost(HttpServletRequest request, HttpServletResponse response) {
        if (request.getParameter("submit") != null) {
            logger.info("submit" + request.getParameter("submit"));
            String[] tokens = request.getParameter("submit").split(",");
            try {
                new MySqlBooksRequestDao().confirm(
                        ((User) (request.getSession().getAttribute("user"))).getId(),
                        Integer.parseInt(tokens[1]),
                        Integer.parseInt(tokens[0]));
            } catch (SQLException e) {
                e.printStackTrace();
                logger.error("cannot confirm take book " + request.getParameter("confirm"));
            }
        }
        if (request.getParameter("denied") != null) {
            logger.info("denied" + request.getParameter("denied"));
            try {
                String[] tokens = request.getParameter("denied").split(",");
                User user = new User();
                user.setId(Integer.parseInt(tokens[0]));
                BooksRequest booksRequest = new BooksRequest();
                booksRequest.setUser(user);
                booksRequest.setId(Integer.parseInt(tokens[1]));
                new MySqlBooksRequestDao().delete(booksRequest);
            } catch (Exception e) {
                logger.error("cannot remove element: denied " + request.getParameter("denied"));
            }
        }
        return executeGet(request, response);
    }
}

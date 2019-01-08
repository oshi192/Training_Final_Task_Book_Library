package controller.command;

import config.ResourceBundleManager;
import controller.util.Pagination;
import model.dao.mysql.MySqlBookDao;
import model.entity.Book;
import model.entity.User;
import util.Configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class MyBooksCommand implements Command {

    @Override
    public String executeGet(HttpServletRequest request, HttpServletResponse response) {
        MySqlBookDao bookDao = new MySqlBookDao();
        int userId = ((User) (request.getSession().getAttribute("user"))).getId();
        int count = bookDao.getCount(
                ResourceBundleManager.getSqlString(ResourceBundleManager.BOOK_REQUEST_BY_USER_ID_COUNT)
                        .replace("?",""+userId));
        Pagination.addPagination(count,request,response);
        int limit = request.getParameter("recordsOnPage")==null?10:Integer.parseInt(request.getParameter("recordsOnPage"));
        int offset = request.getParameter("currentPage")==null?0:(Integer.parseInt(request.getParameter("currentPage"))-1)*limit;
        List<Book> takenBooks = bookDao.getAllByUserId(userId,limit,offset);//todo remove
        request.setAttribute("TakenBooks",takenBooks);
        return Configuration.getProperty(Configuration.MY_BOOKS_PAGE_PATH);
    }

    @Override
    public String executePost(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}

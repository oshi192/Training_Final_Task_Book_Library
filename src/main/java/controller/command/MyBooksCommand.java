package controller.command;

import config.ResourceBundleManager;
import controller.util.Pagination;
import model.dao.mysql.MySqlTakenBooksDao;
import model.entity.TakenBooks;
import model.entity.User;
import util.Configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class MyBooksCommand implements Command {
    private static final String ADMIN_TAKEN_BOOKS_COUNT = "adim-taken-books-count";

    @Override
    public String executeGet(HttpServletRequest request, HttpServletResponse response) {
        int userId = ((User) (request.getSession().getAttribute("user"))).getId();
        int count = new MySqlTakenBooksDao().getCount(ResourceBundleManager.getSqlString(ADMIN_TAKEN_BOOKS_COUNT));
        Pagination.addPagination(count,request,response);
        int limit = request.getParameter("recordsOnPage")==null?10:Integer.parseInt(request.getParameter("recordsOnPage"));
        int offset = request.getParameter("currentPage")==null?0:(Integer.parseInt(request.getParameter("currentPage"))-1)*limit;
        List<TakenBooks> takenBooks = new MySqlTakenBooksDao().getAllPaginateByUserId(userId,limit,offset);//todo remove
        request.setAttribute("TakenBooks",takenBooks);
        return Configuration.getProperty(Configuration.MY_BOOKS_PAGE_PATH);
    }

    @Override
    public String executePost(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}

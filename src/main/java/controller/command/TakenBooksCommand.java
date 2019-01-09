package controller.command;

import config.ResourceBundleManager;
import controller.util.Pagination;
import model.dao.mysql.MySqlBookDao;
import model.entity.Book;
import controller.util.Configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class TakenBooksCommand implements Command {

    @Override
    public String executeGet(HttpServletRequest request, HttpServletResponse response) {
        int count = new MySqlBookDao().getCount(
                ResourceBundleManager.getSqlString(ResourceBundleManager.ADMIN_TAKEN_BOOKS_COUNT));
        Pagination.addPagination(count,request,response);
        int limit = request.getParameter("recordsOnPage")==null?10:Integer.parseInt(request.getParameter("recordsOnPage"));
        int offset = request.getParameter("currentPage")==null?0:(Integer.parseInt(request.getParameter("currentPage"))-1)*limit;
        List<Book> books = new MySqlBookDao().getAllPaginateWithStatus(Book.Status.TAKEN,limit,offset);//todo remove
        request.setAttribute("TakenBooks",books);
        return Configuration.getProperty(Configuration.TAKEN_BOOKS_PAGE_PATH);
    }

    @Override
    public String executePost(HttpServletRequest request, HttpServletResponse response) {
        return executeGet(request,response);
    }
}

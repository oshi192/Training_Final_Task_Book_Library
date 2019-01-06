package controller.command;

import config.ResourceBundleManager;
import controller.manager.Pagination;
import controller.util.QueryBuilder;
import model.dao.mysql.MySqlBookDao;
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
private final  static  String BOOKS_WITH_AUTHORS_CLEAN="BOOKS_WITH_AUTHORS_CLEAN";
    @Override
    public String executeGet(HttpServletRequest request, HttpServletResponse response) {

        int count = new MySqlBookDao().getCount();
        Pagination.addPagination(count,request,response);
        int limit = request.getParameter("recordsOnPage")==null?10:Integer.parseInt(request.getParameter("recordsOnPage"));
        int offset = request.getParameter("currentPage")==null?0:(Integer.parseInt(request.getParameter("currentPage"))-1)*limit;
        List<Book> books = new MySqlBookDao().getAllPaginate(
                //Integer.parseInt(request.getParameter("recordsOnPage")),
                //(Integer.parseInt(request.getParameter("currentPage"))- 1) * Integer.parseInt(request.getParameter("recordsOnPage"))
                limit,offset);//todo remove
        request.setAttribute("Books",books);

        return Configuration.getProperty(Configuration.ALLBOOKS_PATH);
    }


       @Override
    public String executePost(HttpServletRequest request, HttpServletResponse response) {
        int count = new MySqlBookDao().getCount();
        Pagination.addPagination(count,request,response);
        QueryBuilder qm=new QueryBuilder(ResourceBundleManager.getSqlString(BOOKS_WITH_AUTHORS_CLEAN));
        String condition;
        Map<String,String> properties = new HashMap<>();
        properties.put("book_name",request.getParameter("book_name"));
        properties.put("second_name",request.getParameter("second_name"));
        properties.put("tags",request.getParameter("tags"));
        try {
            qm.addSearchBooksProperties(properties);
            int p=Integer.parseInt((request.getParameter("recordsOnPage"))==null?"10":(request.getParameter("recordsOnPage")));
            int offset=(request.getParameter("currentPage")==null?0:((Integer.parseInt(request.getParameter("currentPage"))- 1) * p));
            qm.addPagination(p,offset);
            System.out.println(">>>>>>query"+qm.getQuery());
            ResultSet rs = qm.execute();
            List<Book> books = new MySqlBookDao().getAllFromResultSet(rs);
            System.out.println(">>>>>>booksSize()"+books.size());
            request.setAttribute("Books",books);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Configuration.getProperty(Configuration.ALLBOOKS_PATH);
    }
}

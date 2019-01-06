package controller.util;

import model.dao.mysql.MySqlBookDao;
import model.entity.Book;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class Pagination {
    public static String addPagination(int count, HttpServletRequest request, HttpServletResponse response){
        int activeGroupId = 0;
        int currentPage = 1;
        int recordsOnPage = 10;
        int firstPage = 1;
        int lastPage = 10;
        int pages = 1;

        if (request.getParameter("firstPage") != null) {
            firstPage = Integer.parseInt(request.getParameter("firstPage"));
        }
        if (request.getParameter("lastPage") != null) {
            lastPage = Integer.parseInt(request.getParameter("lastPage"));
        }
        if (request.getParameter("currentPage") != null) {
            currentPage = Integer.parseInt(request.getParameter("currentPage"));
        }
        if (request.getParameter("recordsOnPage") != null) {
            recordsOnPage = Integer.parseInt(request.getParameter("recordsOnPage"));
        }
//        int count = new MySqlBookDao().getCount();
//        List<Book> books = new MySqlBookDao().getAll(recordsOnPage,(currentPage-1)*recordsOnPage);

        pages=count/recordsOnPage+1;
        firstPage=(currentPage)>=3?currentPage-2:1;
        lastPage=(pages-currentPage)>0?((pages-currentPage)>1?currentPage+2:currentPage+1):currentPage;//todo remove kostul`
        System.out.println("count:"+count+"firstPage:"+firstPage+" lastPage:"+lastPage+" currentPage:"+currentPage+" recordsOnPage:"+recordsOnPage);
        request.setAttribute("currentPage",currentPage);
        request.setAttribute("pages",pages);
        request.setAttribute("recordsOnPage",recordsOnPage);
        request.setAttribute("firstPage",firstPage);
        request.setAttribute("lastPage",lastPage);
//        request.setAttribute("Books",books);
        return null;
    }
}

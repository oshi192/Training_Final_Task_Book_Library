package controller.command;

import config.ResourceBundleManager;
import controller.util.Pagination;
import controller.util.SearchUtil;
import exception.RecordChangeException;
import model.dao.mapper.BookMapper;
import model.dao.mysql.MySqlBookDao;
import model.entity.Book;
import model.entity.User;
import org.apache.log4j.Logger;
import util.Configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AllBooksCommand implements Command {
    private static final Logger logger = Logger.getLogger(AllBooksCommand.class);
    private final static String BOOKS_WITH_AUTHORS_CLEAN = "BOOKS_WITH_AUTHORS_CLEAN";
    private static SearchUtil search = new SearchUtil();

    @Override
    public String executeGet(HttpServletRequest request, HttpServletResponse response) {
        int count = new MySqlBookDao().getCount(
                ResourceBundleManager.getSqlString(ResourceBundleManager.BOOKS_COUNT));
        int limit = request.getParameter("recordsOnPage") == null ? 10 : Integer.parseInt(request.getParameter("recordsOnPage"));
        int offset = request.getParameter("currentPage") == null ? 0 : (Integer.parseInt(request.getParameter("currentPage")) - 1) * limit;
        Pagination.addPagination(count, request, response);

        String s = search.checkNotNullSearchAtributes(request)?search.addSearchStatement(request,""):"";

        List<Book> books = new MySqlBookDao().getAll(s,limit, offset);
        request.setAttribute("Books", books);
        List<String> sections = new MySqlBookDao().getAllSections();
        request.setAttribute("Sections", sections);
        return Configuration.getProperty(Configuration.ALLBOOKS_PATH);
    }


    @Override
    public String executePost(HttpServletRequest request, HttpServletResponse response) {
        logger.info("edit:["+request.getParameter("edit")+"] "+
                "create:["+request.getParameter("create")+"] "+
                "remove:["+request.getParameter("remove")+"] "+
                "take:["+request.getParameter("take")+"] ");
        try{
            if (request.getParameter("edit") != null) {
                editRecord(request);
            }
            if (request.getParameter("create") != null) {
                createRecord(request);
            }
            if (request.getParameter("remove") != null) {
                removeRecord(request);
            }
            if (request.getParameter("take") != null) {
                takeStatusIntoRecord(request);
            }
        } catch (RecordChangeException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage",e.getMessage());
        }

        return executeGet(request, response);
    }

    private void createRecord(HttpServletRequest request) {
//        int bookId = Integer.parseInt(request.getParameter("create"));
        logger.info("create book: ");
        Book book = new BookMapper().mapGetFromRequest(request);
        //book.setId(bookId);
        new MySqlBookDao().save(book);
    }

    private void editRecord(HttpServletRequest request) {
        int bookId = Integer.parseInt(request.getParameter("edit"));
        logger.info("edit book: " + bookId);
        Book book = new BookMapper().mapGetFromRequest(request);
        book.setId(bookId);
        new MySqlBookDao().update(book);
    }

    private void removeRecord(HttpServletRequest request) throws RecordChangeException {
        int bookId = Integer.parseInt(request.getParameter("remove"));
        Book book = new Book();
        book.setId(bookId);
        logger.info("remove book: " + bookId);
        new MySqlBookDao().delete(book);
    }

    private void takeStatusIntoRecord(HttpServletRequest request) throws RecordChangeException {
        int bookId = Integer.parseInt(request.getParameter("take"));
        logger.info("take book: " + bookId);
        Book book = new MySqlBookDao().get(bookId);
        book.setStatus(Book.Status.REQUEST.id);
        int userId = ((User) (request.getSession().getAttribute("user"))).getId();
        book.setUserId(userId);
        logger.info("update book: " + book);
        if(new MySqlBookDao().get(book.getId()).getStatus()!=Book.Status.FREE.id) {
            throw new RecordChangeException("cannot take book! The record had been changed!"+book);
        }
        new MySqlBookDao().update(book);
    }
}

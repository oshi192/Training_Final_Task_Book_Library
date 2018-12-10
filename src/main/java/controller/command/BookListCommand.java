package controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BookListCommand implements Command {
    @Override
    public String execute(HttpServletRequest request,HttpServletResponse response) {
        return "/jsp/book-list.jsp";
    }
}

package controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ErrorHandlerCommand implements Command{
    @Override
    public String executeGet(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html; charset=utf-8");
 return "WEB-INF/view/errorpage.jsp";
    }

    @Override
    public String executePost(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}

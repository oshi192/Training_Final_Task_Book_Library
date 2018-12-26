package controller.command;

import util.Configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminAllBookCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        new AllBooksCommand().execute(request,response);
        return Configuration.getProperty(Configuration.ADMIN_ALLBOOKS_PATH);
    }
}

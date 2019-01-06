package controller.command;

import util.Configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyBooksCommand implements Command {
    @Override
    public String executeGet(HttpServletRequest request, HttpServletResponse response) {
        return Configuration.getProperty(Configuration.MY_BOOKS_PAGE_PATH);
    }

    @Override
    public String executePost(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}

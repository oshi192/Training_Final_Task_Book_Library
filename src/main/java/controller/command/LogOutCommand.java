package controller.command;


import controller.servlets.AddServlet;
import controller.util.LogInOutUtils;
import util.Configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LogOutCommand implements Command {
    @Override
    public String executeGet(HttpServletRequest request, HttpServletResponse response) {
        new LogInOutUtils().logOut(request.getSession());
        request.setAttribute("user",null);
        return Configuration.getProperty(Configuration.INDEX_PAGE_PATH);
    }

    @Override
    public String executePost(HttpServletRequest request, HttpServletResponse response) {
        new LogInOutUtils().logOut(request.getSession());
        request.setAttribute("user",null);
        return Configuration.getProperty(Configuration.INDEX_PAGE_PATH);
    }

}

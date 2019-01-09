package controller.command;


import controller.util.LogInOutUtils;
import controller.util.Configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LogOutCommand implements Command {
    @Override
    public String executeGet(HttpServletRequest request, HttpServletResponse response) {
        new LogInOutUtils().logOut(request.getSession());
        return Configuration.getProperty(Configuration.INDEX_PAGE_PATH);
    }

    @Override
    public String executePost(HttpServletRequest request, HttpServletResponse response) {
        return executeGet(request,response);
    }

}

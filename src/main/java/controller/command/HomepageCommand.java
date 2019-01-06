package controller.command;

import util.Configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomepageCommand implements Command{

    @Override
    public String execute(HttpServletRequest request,HttpServletResponse response) {
        return Configuration.getProperty(Configuration.INDEX_PAGE_PATH);
    }
}

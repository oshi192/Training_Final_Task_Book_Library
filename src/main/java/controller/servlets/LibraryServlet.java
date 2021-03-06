package controller.servlets;

import controller.command.Command;
import controller.command.HomepageCommand;
import controller.manager.CommandMannager;
import org.apache.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/")

public class LibraryServlet extends HttpServlet {
    private static ServletContext context;
    private static Logger logger = Logger.getLogger(LibraryServlet.class);
    private final static String EMPTY = "";
    private final static String REDIRECT = "redirect:";
    private final static String SERVLET = ".*/library/";

    private enum RequestType {
        GET, POST
    }

    public void init(ServletConfig servletConfig) {
        context = servletConfig.getServletContext();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("servlet get request");
        processRequest(req, resp, RequestType.GET);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("servlet post request");
        processRequest(req, resp, RequestType.POST);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response, RequestType type)
            throws ServletException, IOException {
        String path = request.getRequestURI();
        path = path.replaceAll(".*/library/", EMPTY);
        Command command = CommandMannager.getCommands().getOrDefault(path, new HomepageCommand());
        logger.info(command.getClass().getName());
        String page;
        if (type == RequestType.GET) {
            page = command.executeGet(request, response);
        } else {
            page = command.executePost(request, response);
        }
        logger.info("path: " + path + " >> page: " + page + " ");
        if (page.contains(REDIRECT)) {
            page = page.replaceAll(REDIRECT, EMPTY);
            logger.info("redirect to page: " + page);
            response.sendRedirect(page);
        } else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }

    public static ServletContext getContext() {
        return context;
    }

}

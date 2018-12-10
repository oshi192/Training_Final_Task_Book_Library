package controller.servlets;

import controller.command.*;
import controller.manager.CommandMannager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;

public class AddServlet extends HttpServlet {
    //    private Map<String, Command> commands = new HashMap<>();
    private static Logger logger =  LoggerFactory.getLogger(AddServlet.class);

    public void init(ServletConfig servletConfig) {

        servletConfig.getServletContext()
                .setAttribute("loggedUsers", new HashSet<String>());

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("addServlet get");
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("addServlet post");
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("processRequest");
        String path = request.getRequestURI();
        path = path.replaceAll(".*/app/", "");
        Command command = CommandMannager.getCommands().getOrDefault(path, (rq,rs) -> "/index.jsp");
        System.out.println(command.getClass().getName());
        String page = command.execute(request,response);
        System.out.println("path: " + path + " >> page: " + page + " ");
        request.getRequestDispatcher(page).forward(request, response);
    }
}

package controller.servlets;

import controller.command.Command;
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
import java.util.HashSet;
//@WebServlet(urlPatterns = "/library/*" )

public class AddServlet extends HttpServlet {
    private static ServletContext context;
    public static ServletContext getContext() {
        return context;
    }
    private static Logger logger =  Logger.getLogger(AddServlet.class);

    public void init(ServletConfig servletConfig) {
        context = servletConfig.getServletContext();
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
        path = path.replaceAll(".*/library/", "");
        Command command = CommandMannager.getCommands().getOrDefault(path, (rq,rs) -> "/index.jsp");
        System.out.println(command.getClass().getName());
        String page = command.execute(request,response);
        System.out.println("path: " + path + " >> page: " + page + " ");
        if(page.contains("redirect")){
            page=page.replaceAll("redirect:","");
            System.out.println("redirect to page: "+page);
            response.sendRedirect(page);
        }else {
//            if(!response.isCommitted())
                request.getRequestDispatcher(page).forward(request, response);
        }
    }
}

package controller.command;

import model.dao.mysql.MySqlUserDao;
import model.entity.User;
import org.apache.log4j.Logger;
import util.Configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class LogInCommand implements Command {
    private static Logger logger = Logger.getLogger(LogInCommand.class);
//  /  @Override
//    public String execute(HttpServletRequest request) {
//        return Configuration.getProperty(
//                Configuration.LOGIN_PAGE_PATH);
//    }

//    @Override
//    public String execute(HttpServletRequest request) {
//        String email = request.getParameter("email");
//        String pass = request.getParameter("pass");
//        System.out.println(email+" "+pass);
//        if( email == null || email.equals("") || pass == null || pass.equals("")  ){
//            System.out.println("Not");//todo logger
//            return Configuration.getProperty(Configuration.LOGIN_PAGE_PATH);
//        }
//        String role = CommandUtility.findUserAndGetRole(email,pass);
//        if(User.Role.ADMIN.name().equals(role) || User.Role.USER.name().equals(role)){
//            System.out.println("log in "+role);
//            return "/WEB-INF/error.jsp";
//        }else{
//            return Configuration.getProperty(Configuration.INDEX_PAGE_PATH);
//        }
//    }
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        String pass = request.getParameter("password");
        System.out.println(request.getParameterNames().toString());
        System.out.println("email = "+email);
        System.out.println("pass = "+pass);
        Map<String, String> messages = new HashMap<>();
        if (email == null || email.isEmpty()) {
            messages.put("email", "Please enter email");
        }
        if (pass == null || pass.isEmpty()) {
            messages.put("password", "Please enter password");
        }
        if (messages.isEmpty()) {
            User user = null;
            user = new MySqlUserDao()
                    .findByEmail(email);
            System.out.println("user"+user);
            if (user != null) {
                request.getSession().setAttribute("user", user);
                return "redirect:homepage";
            } else {
                messages.put("email", "Unknown email, please try again");
            }
        }

        request.setAttribute("messages", messages);
        System.out.println(messages.toString());
        return Configuration.getProperty(Configuration.LOGIN_PAGE_PATH);
    }
}

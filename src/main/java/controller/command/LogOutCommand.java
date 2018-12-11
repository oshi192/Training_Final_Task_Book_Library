package controller.command;


import model.entity.User;
import util.CommandUtility;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LogOutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request,HttpServletResponse response) {
        CommandUtility.setUserRole(request, User.Role.GUEST, "");
        return "/index.jsp";
    }
}

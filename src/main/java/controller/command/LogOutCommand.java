package controller.command;


import util.CommandUtility;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static model.entity.User.Role.UNKNOWN;

public class LogOutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request,HttpServletResponse response) {
        CommandUtility.setUserRole(request, UNKNOWN, "Guest");
        return "/index.jsp";
    }
}

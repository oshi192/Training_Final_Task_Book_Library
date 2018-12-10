package controller.command.admin;

import controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminBookListCommand implements Command {
    @Override
    public String execute(HttpServletRequest request,HttpServletResponse responce) {
        return "jsp/book-list.jsp";
    }
}

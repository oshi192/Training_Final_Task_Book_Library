package controller.command;

import model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CabinetCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        String page ;
        if(User.Role.ADMIN.name().equals(user.getRole())){
            page=new AdminTakenBooksCommand().execute(request,response);
        }else{
            page=new UserMyBooksCommand().execute(request,response);
        }
        return page;
    }
}

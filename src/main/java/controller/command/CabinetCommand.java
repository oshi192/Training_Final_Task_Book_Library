package controller.command;

import model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CabinetCommand implements Command {

    @Override
    public String executeGet(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        String page ;
        if(User.Role.ADMIN.name().equals(user.getRole())){
            page=new AdminTakenBooksCommand().executeGet(request,response);
        }else{
            page=new UserMyBooksCommand().executeGet(request,response);
        }
        return page;
    }

    @Override
    public String executePost(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}

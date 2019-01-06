package controller.command;


<<<<<<< HEAD
import model.entity.User;
import util.CommandUtility;
=======
>>>>>>> edfd3089b9d81b68c9a8db558a42af5700b5ef02
import util.Configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LogOutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request,HttpServletResponse response) {
<<<<<<< HEAD
        CommandUtility.setUserRole(request, User.Role.GUEST, "");
=======

>>>>>>> edfd3089b9d81b68c9a8db558a42af5700b5ef02
        return Configuration.getProperty(Configuration.INDEX_PAGE_PATH);
    }

}

package controller.command;

import util.Configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserMyBooksCommand implements Command {
    @Override
    public String executeGet(HttpServletRequest request, HttpServletResponse response) {
//        List<TakenBook> list=new ArrayList<>();
//        try {
//            System.out.println("user taken books");
//            User user = (User) request.getSession().getAttribute("user");
//            list = new JDBCTakenBookDao(ConnectionPoolHolder
//                    .getDataSource().getConnection())
//                    .findAllByUserId(0, user.getId());
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        request.setAttribute("UserTakenBooks", list);
        return Configuration.getProperty(Configuration.USER_MYBOOK_PATH);
    }

    @Override
    public String executePost(HttpServletRequest request, HttpServletResponse response) {
        return Configuration.getProperty(Configuration.USER_MYBOOK_PATH);
    }


}

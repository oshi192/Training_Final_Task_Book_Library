//package controller.command;
//
//import util.Configuration;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//public class AdminAllBookCommand implements Command {
//    @Override
//    public String executeGet(HttpServletRequest request, HttpServletResponse response) {
//        new AllBooksCommand().executeGet(request,response);
//        return Configuration.getProperty(Configuration.ADMIN_ALLBOOKS_PATH);
//    }
//
//    @Override
//    public String executePost(HttpServletRequest request, HttpServletResponse response) {
//        return null;
//    }
//}

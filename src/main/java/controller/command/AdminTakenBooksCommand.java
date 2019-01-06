//package controller.command;
//
//import util.Configuration;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//public class AdminTakenBooksCommand implements Command {
//    @Override
//    public String executeGet(HttpServletRequest request,HttpServletResponse response) {
////        try {
////            System.out.println("admin taken books");
////            List<TakenBook> list= new JDBCTakenBookDao(ConnectionPoolHolder
////                    .getDataSource().getConnection())
////                    .findAll(0);
////            request.setAttribute("TakenBooks", list);
////        } catch (SQLException e) {
////            e.printStackTrace();
////        }
//        return Configuration.getProperty(Configuration.ADMIN_TAKENBOOKS_PATH);
//    }
//
//    @Override
//    public String executePost(HttpServletRequest request, HttpServletResponse response) {
//        return null;
//    }
//
//}

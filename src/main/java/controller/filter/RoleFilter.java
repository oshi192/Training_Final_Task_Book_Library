package controller.filter;

import javax.servlet.*;
import java.io.IOException;

public class RoleFilter implements Filter {
    private static final String USER_ATTR = "user";
    private static final String ROLE_PARAM = "role";

    public RoleFilter() {
    }

    public void destroy() {
    }


    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException{
//        HttpServletRequest req = (HttpServletRequest) request;
//        HttpSession session = req.getSession();
//        User user = (User) session.getAttribute(USER_ATTR);
//        String role;
//        System.out.println("starting rolefilter... ");
//        if (Objects.isNull(user)) {
//            role = User.Role.UNKNOWN.name();
//        } else {
//            role = user.getRole();
//        }
//        AccessMapper mapper = AccessMapper.getInstance();
//        //boolean enoughRights = mapper.checkRights(req, role);
//       // System.out.println("role: " + role + " enoughRights: " + enoughRights);
//
//       // if (!enoughRights) {
//            String page = Configuration.getProperty(Configuration.INDEX_PAGE_PATH);
//
//            System.out.println("go back: " + page);
//            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(page);
////            try {
////                dispatcher.forward(request, response);
////            } catch (Exception e) {
////                e.printStackTrace();
////            }
//        }
        chain.doFilter(request, response);
    }


    public void init(FilterConfig fConfig) throws ServletException {
    }

}

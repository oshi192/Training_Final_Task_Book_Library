package controller.filter;

import controller.manager.AccessMapper;
import model.entity.User;
import util.Configuration;

import javax.servlet.*;
import java.io.IOException;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RoleFilter implements Filter {
    private static final String USER_ATTR = "user";
//    private static final String ROLE_PARAM = "role";

    public RoleFilter() {
    }

    public void destroy() {
    }


    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException{
        System.out.println("------- starting Role filtering -------");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(USER_ATTR);
        User.Role role;
        if (Objects.isNull(user)) {
            role = User.Role.GUEST;
        } else {
            role = user.getRole();
        }
        AccessMapper mapper = AccessMapper.getInstance();
        boolean enoughRights = mapper.checkRights(req, role);
        System.out.println("\trole: " + role + " enoughRights: " + enoughRights);
         if (!enoughRights) {
            String page = Configuration.getProperty(Configuration.INDEX_PAGE_PATH);
            System.out.println("\tgo back: " + page);
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(page);
            try {
                dispatcher.forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("------- ending Role filtering -------");
        chain.doFilter(request, response);
    }


    public void init(FilterConfig fConfig) throws ServletException {
    }

}

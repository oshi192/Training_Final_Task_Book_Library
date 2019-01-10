package controller.filter;

import controller.util.AccessMapper;
import model.entity.User;
import org.apache.log4j.Logger;
import controller.util.Configuration;

import javax.servlet.*;
import java.io.IOException;
import java.util.Objects;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * This class check access rights to page
 */
@WebFilter(filterName = "RoleFilter", urlPatterns = {"/*"})
public class RoleFilter implements Filter {
    private static final String USER_ATTR = "user";
    private final static Logger logger = Logger.getLogger(RoleFilter.class);

    public RoleFilter() {
    }

    public void destroy() {
    }


    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException{
        logger.info("------- starting Role filtering -------");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(USER_ATTR);
        User.Role role;
        if (Objects.isNull(user)) {
            role = User.Role.GUEST;
        } else {
            role = User.Role.valueOf(user.getRole());
        }
        AccessMapper mapper = AccessMapper.getInstance();
        boolean enoughRights = mapper.checkRights(req, role);
        logger.info("\trole: " + role + " enoughRights: " + enoughRights);
         if (!enoughRights) {
            String page = Configuration.getProperty(Configuration.INDEX_PAGE_PATH);
            logger.info("\tgo back: " + page);
            RequestDispatcher dispatcher = request.getRequestDispatcher(page);
            try {
                dispatcher.forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        logger.info("------- ending Role filtering -------");
        chain.doFilter(request, response);
    }


    public void init(FilterConfig fConfig) throws ServletException {
    }

}

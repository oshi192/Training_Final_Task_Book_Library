package controller.filter;

import config.ResourceBundleManager;
import controller.servlets.LibraryServlet;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Class set user locale
 */
@WebFilter(filterName = "LangFilter", urlPatterns = {"/*"})
public class LangFilter implements Filter {
    private final static Logger logger = Logger.getLogger(LangFilter.class);

    private static final String USER_LOCALE_ATTR = "userLocale";
    private static final String ENCODING = "UTF-8";
    private static final String LANG_PARAM = "lang";

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("------- starting Lang filter -------");
        servletResponse.setContentType("text/html");
        servletRequest.setCharacterEncoding(ENCODING);
        servletResponse.setCharacterEncoding(ENCODING);
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpSession session = req.getSession();
        Locale locale = (Locale) session.getAttribute(USER_LOCALE_ATTR);
        String lang = servletRequest.getParameter(LANG_PARAM);
        if (Objects.isNull(locale)) {
            logger.info("\tlocale is null");
            locale = req.getLocale();
        }
        if (StringUtils.isNoneBlank(lang)) {
            logger.info("\tlang is none blank");
            if (!lang.equals(locale.getLanguage())) {
                logger.info("\tseting new locale");
                locale = new Locale(lang);
            }
        }
        ResourceBundleManager.setNewLocale(locale);
        session.setAttribute(USER_LOCALE_ATTR, locale);
        req.setAttribute("locale",locale.getLanguage());
        logger.info("------- ending Lang filter -------");
        //todo remove this
        ServletContext context  = LibraryServlet.getContext();
        ConcurrentHashMap<Integer, HttpSession> logedUsers = (ConcurrentHashMap)context.getAttribute("loggedUsers");
        for (Map.Entry<Integer, HttpSession> set : logedUsers.entrySet()){
            logger.info("|\t|\t:"+set.getKey()+" | "+set.getValue().getAttribute("user").toString());
        }
        logger.info("locale:"+locale.getLanguage()+locale.getCountry()+
                locale.getDisplayName()+">>>>"+locale.toString()+"\t>>default>"+
                Locale.getDefault().toString()+Locale.getDefault().getLanguage());
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}

package controller.filter;

import config.ResourceBundleManager;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;
import java.util.Objects;

public class LangFilter implements Filter {
    private static final String USER_LOCALE_ATTR = "userLocale";
    private static final String ENCODING = "UTF-8";
    private static final String LANG_PARAM = "lang";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("------- starting Lang filter -------");
        servletResponse.setContentType("text/html");
        servletRequest.setCharacterEncoding(ENCODING);
        servletResponse.setCharacterEncoding(ENCODING);
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpSession session = req.getSession();
        Locale locale = (Locale) session.getAttribute(USER_LOCALE_ATTR);
        String lang = servletRequest.getParameter(LANG_PARAM);

        if (Objects.isNull(locale)) {
            System.out.println("\tlocale is null");
            locale = req.getLocale();
        }

        if (StringUtils.isNoneBlank(lang)) {
            System.out.println("\tlang is none blank");
            if (!lang.equals(locale.getLanguage())) {
                System.out.println("\tseting new locale");
                locale = new Locale(lang);
            }
        }

        ResourceBundleManager.setNewLocale(locale);
        session.setAttribute(USER_LOCALE_ATTR, locale);
        System.out.println("------- ending Lang filter -------");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}

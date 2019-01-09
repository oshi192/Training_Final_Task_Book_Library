package controller.util;

import model.entity.User;
import model.entity.User.Role;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class AccessMapper {
    private final static Logger logger = Logger.getLogger(AccessMapper.class);
    private static HashMap<User.Role, List<String>> rights;
    private static AccessMapper instance;

    private AccessMapper() {
        rights = setRights();
    }

    private HashMap<User.Role, List<String>> setRights() {

        rights = new HashMap<>();
        rights.put(Role.GUEST,Arrays.asList(
                "",
                "login",
                "registration",
                "submit-login",
                "submit-registration",
                "all-books"
        ));
        rights.put(Role.USER,Arrays.asList(
                "",
                "logout",
                "all-books",
                "my-books",
                "my-requests"
        ));
        rights.put(Role.ADMIN,Arrays.asList(
                "",
                "logout",
                "all-books",
                "all-users",
                "books-requests"
        ));
        return rights;
    }

    public static AccessMapper getInstance() {
        if (Objects.isNull(instance)) {
            instance = new AccessMapper();
        }
        return instance;
    }


    public boolean checkRights(HttpServletRequest request, Role role) {
        String page = request.getRequestURI();
        page = page.replaceAll(".*/library/", "");
        logger.info(">>>>page:["+page+"]");
        if (page.isEmpty()) {
            return true;
        }
        if(page.contains("resources")){
            return true;
        }
        return rights.get(role).contains(page);
    }
}

package controller.manager;

import model.entity.User;
import model.entity.User.Role;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class AccessMapper {
    private static HashMap<User.Role, List<String>> rights;
    private static AccessMapper instance;

    private AccessMapper() {
        rights = setRights();
    }

    private HashMap<User.Role, List<String>> setRights() {

        rights = new HashMap<>();
        rights.put(Role.GUEST,Arrays.asList("","login","registration","submit-login","submit-registration","all-books"));
        rights.put(Role.USER,Arrays.asList("","logout","all-books"));
        rights.put(Role.ADMIN,Arrays.asList("","logout","all-books","manage-books"));
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
        System.out.println(">>>>page:["+page+"]");
        if (Objects.isNull(page)) {
            return true;
        }
        if(page.contains("resources")){
            return true;
        }
        return rights.get(role).contains(page);
    }
}

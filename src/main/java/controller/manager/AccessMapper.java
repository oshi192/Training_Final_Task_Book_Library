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
    private static HashMap<String, List<Role>> rights;
    private static AccessMapper instance;

    private AccessMapper() {
        rights = setRights();

    }

    private HashMap<String, List<User.Role>> setRights() {

        rights = new HashMap<>();
        // Authentification & user management operations//
        rights.put("login", Arrays.asList(Role.GUEST));
        rights.put("logout", Arrays.asList(Role.USER, Role.ADMIN));
        rights.put("registration", Arrays.asList(Role.GUEST));
        rights.put("submit-registration", Arrays.asList(Role.GUEST));
        rights.put("", Arrays.asList(Role.GUEST, Role.USER, Role.ADMIN));
        rights.put("submit-login", Arrays.asList(Role.GUEST));
        rights.put("book-list", Arrays.asList(Role.GUEST, Role.USER, Role.ADMIN));
        rights.put("errorpage", Arrays.asList(Role.GUEST, Role.USER, Role.ADMIN));
        rights.put("book-list-admin", Arrays.asList(Role.ADMIN));
        rights.put("taken-books-admin", Arrays.asList(Role.ADMIN));
        rights.put("all-books", Arrays.asList(Role.GUEST, Role.USER, Role.ADMIN));
        rights.put("manage-books", Arrays.asList(Role.GUEST, Role.USER, Role.ADMIN));
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
        if(!rights.containsKey(page)){
            return false;
        }
        return rights.get(page).contains(role);
    }



}

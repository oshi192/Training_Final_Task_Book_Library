package controller.manager;

import controller.command.*;

import java.util.HashMap;
import java.util.Map;

public class CommandMannager {
    private static Map<String, Command> commands = new HashMap<>();

    static {
        commands.put("logout", new LogOutCommand());
        commands.put("login", new LogInCommand());
        commands.put("exception", new ExceptionCommand());
        commands.put("registration", new RegistrationCommand());
        commands.put("", new HomepageCommand());
        commands.put("login-submit", new LoginSubmitCommand());
        commands.put("submit-login", new LoginSubmitCommand());
        commands.put("submit-registration", new RegistrationSubmitCommand());
        commands.put("cabinet", new CabinetCommand());
<<<<<<< HEAD
=======
        commands.put("manage-books", new AdminAllBookCommand());
        commands.put("taken-books", new AdminTakenBooksCommand());
        commands.put("all-books", new AllBooksCommand());
>>>>>>> edfd3089b9d81b68c9a8db558a42af5700b5ef02
    }

    private CommandMannager() {
    }

    public static Map<String, Command> getCommands() {
        return commands;
    }
}

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
        commands.put("submit-registration", new RegistrationSubmitCommand());
        commands.put("cabinet", new CabinetCommand());
        commands.put("manage-books", new AdminAllBookCommand());
        commands.put("taken-books", new AdminTakenBooksCommand());
        commands.put("all-books", new AllBooksCommand());
    }

    private CommandMannager() {
    }

    public static Map<String, Command> getCommands() {
        return commands;
    }
}

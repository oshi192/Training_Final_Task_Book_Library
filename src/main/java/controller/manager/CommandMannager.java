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
//        commands.put("book-list", new BookListCommand());
        commands.put("register", new RegisterCommand());
        commands.put("homepage", new HomepageCommand());
        commands.put("login-submit", new LoginSubmitCommand());
        commands.put("book-list", new BookSearchCommand());
    }

    private CommandMannager() {
    }

    public static Map<String, Command> getCommands() {
        return commands;
    }
}

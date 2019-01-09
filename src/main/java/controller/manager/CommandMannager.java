package controller.manager;

import controller.command.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Class contains Map with (path key -> CommandObject) for servlet
 */
public class CommandMannager {
    private static Map<String, Command> commands = new HashMap<>();

    static {
        commands.put("logout", new LogOutCommand());
        commands.put("login", new LogInCommand());
        commands.put("exception", new ExceptionCommand());
        commands.put("registration", new RegistrationCommand());
        commands.put("", new HomepageCommand());
        commands.put("all-books", new AllBooksCommand());
        commands.put("all-users", new TakenBooksCommand());
        commands.put("books-requests", new BooksRequestsCommand());
        commands.put("my-books", new MyBooksCommand());
        commands.put("my-requests", new MyRequestsCommand());
        commands.put("errorHandler", new ErrorHandlerCommand());
    }

    private CommandMannager() {
    }

    public static Map<String, Command> getCommands() {
        return commands;
    }
}

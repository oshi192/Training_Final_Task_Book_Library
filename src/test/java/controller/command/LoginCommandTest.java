package controller.command;

import controller.util.Configuration;
import model.entity.User;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class LoginCommandTest {
    private LogInCommand loginCommand = new LogInCommand();
    private HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    private HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
    private HttpSession session = Mockito.mock(HttpSession.class);
    private ServletContext context = Mockito.mock(ServletContext.class);
    private ArrayList<User> users = new ArrayList<>();
    private static Logger logger = Logger.getLogger(LogInCommand.class);

    @Before
    public void setUp() {
        Mockito.when(request.getServletContext()).thenReturn(context);
        Mockito.when(context.getAttribute("loggedUsers")).thenReturn(users);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(request.getParameter("username")).thenReturn("admin@gmail.com");
        Mockito.when(request.getParameter("password")).thenReturn("Admin1234");
//        Mockito.spy();
   }
    @Test
    public void process() {
        String uri = loginCommand.executePost(request,response);
        assertEquals(Configuration.getProperty(Configuration.LOGIN_PAGE_PATH), uri);

    }
}

package Spring.Test.jdk.web.controllers;

import Spring.Test.jdk.web.AbstractDispatcherServletTest;
import org.junit.Test;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.SimpleServletHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ServletControllerTest extends AbstractDispatcherServletTest {
    @Test
    public void helloServletController() throws ServletException, IOException{
        setClasses(SimpleServletHandlerAdapter.class, HelloServlet.class);
        initRequest("/hello").addParameter("name","Spring");

        assertThat(runService().getContentAsString(), is("Hello Spring\n"));
    }

    @Component("/hello")
    static class HelloServlet extends HttpServlet{
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
            String name = req.getParameter("name");
            resp.getWriter().println("Hello " + name);
        }
    }
}

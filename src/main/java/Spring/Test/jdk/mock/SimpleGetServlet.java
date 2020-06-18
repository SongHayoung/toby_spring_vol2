package Spring.Test.jdk.mock;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SimpleGetServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String name = req.getParameter("name");

        resp.getWriter().println("<HTML><BODY>");
        resp.getWriter().println("Hello " + name);
        resp.getWriter().println("</BODY></HTML>");
    }
}

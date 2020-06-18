package Spring.Test.jdk.mock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ServletTest {
    @Test
    public void getMethodServlet() throws IOException, ServletException {
        MockHttpServletRequest req = new MockHttpServletRequest("GET", "/hello");
        req.addParameter("name", "Spring");
        MockHttpServletResponse res = new MockHttpServletResponse();

        SimpleGetServlet servlet = new SimpleGetServlet();
        servlet.service(req,res);
        servlet.init();

        assertThat(res.getContentAsString(), is("<HTML><BODY>\nHello Spring\n</BODY></HTML>\n"));
        assertThat(res.getContentAsString().indexOf("Hello Spring") > 0, is(true));
    }
}

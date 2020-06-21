package Spring.Test.jdk.web.controllers;

import Spring.Test.jdk.web.AbstractDispatcherServletTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.ModelAndView;

public class SimpleControllerTest extends AbstractDispatcherServletTest {
    @Test
    public void helloSimpleController() throws ServletException, IOException {
        setClasses(HelloController.class);
        initRequest("/hello").addParameter("name", "spring");
        runService();
        assertModel("message", "Hello spring");
        assertViewName("/WEB-INF/view/hello.jsp");
    }

    @Test(expected=Exception.class)
    public void noParameterHelloSimpleController() throws ServletException, IOException {
        setClasses(HelloController.class);
        initRequest("/hello");
        runService();
    }

    @Test
    public void helloControllerUnitTest() throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        params.put("name", "Spring");
        Map<String, Object> model = new HashMap<String, Object>();

        new HelloController().control(params, model);

        assertThat(model.get("message").toString(), is("Hello Spring"));
    }

    @org.springframework.stereotype.Controller
    static class HelloController extends SimpleController {
        public HelloController() {
            this.setRequiredParams(new String[] {"name"});
            this.setViewName("/WEB-INF/view/hello.jsp");
        }

        @RequestMapping("/hello")
        public void control(Map<String, String> params, Map<String, Object> model) throws Exception {
            model.put("message", "Hello " + params.get("name"));
        }
    }

    static abstract class SimpleController implements Controller {
        private String[] requiredParams;
        private String viewName;

        public void setRequiredParams(String[] requiredParams) {
            this.requiredParams = requiredParams;
        }

        public void setViewName(String viewName) {
            this.viewName = viewName;
        }

        final public ModelAndView handleRequest(HttpServletRequest req,
                                                HttpServletResponse res) throws Exception {
            if (viewName == null) throw new IllegalStateException();

            Map<String, String> params = new HashMap<String, String>();
            for(String param : requiredParams) {
                String value = req.getParameter(param);
                if (value == null) throw new IllegalStateException();
                params.put(param, value);
            }

            Map<String, Object> model = new HashMap<String, Object>();

            this.control(params, model);

            return new ModelAndView(this.viewName, model);
        }


        public abstract void control(Map<String, String> params, Map<String, Object> model) throws Exception;
    }
}

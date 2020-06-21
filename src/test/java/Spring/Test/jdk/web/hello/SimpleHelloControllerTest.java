package Spring.Test.jdk.web.hello;

import Spring.Test.jdk.web.AbstractDispatcherServletTest;
import org.junit.Test;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SimpleHelloControllerTest extends AbstractDispatcherServletTest {
    @Test
    public void helloController() throws ServletException, IOException{
        ModelAndView mav = setRelativeLocations("spring-servlet.xml")
                .setClasses(HelloSpring.class)
                .initRequest("/hello", RequestMethod.GET)
                .addParameter("name", "Spring")
                .runService()
                .getModelAndView();

        assertThat(mav.getViewName(), is("/WEB-INF/view/hello.jsp"));
        assertThat((String)mav.getModel().get("message"), is("Hello Spring"));
    }
}

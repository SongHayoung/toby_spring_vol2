package Spring.Test.jdk.web.Hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class HelloController implements Controller {
    @Autowired HelloSpring helloSpring;

    public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception{
        String name = req.getParameter("name");

        String message = this.helloSpring.sayHello(name);

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("message", message);

        return new ModelAndView("/WEB-INF/view/hello.jsp",model);
    }
}
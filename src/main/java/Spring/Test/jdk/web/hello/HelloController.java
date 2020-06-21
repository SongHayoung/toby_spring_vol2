package Spring.Test.jdk.web.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.InternalResourceView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@org.springframework.stereotype.Controller
public class HelloController implements Controller {
    @Autowired HelloSpring helloSpring;

    @RequestMapping("/hello")
    public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception{
        String name = req.getParameter("name");

        String message = this.helloSpring.sayHello(name);

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("message", message);

        View view = new InternalResourceView("/WEB-INF/view/hello.jsp");

        return new ModelAndView(view,model);
    }
}

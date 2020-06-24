package Spring.Test.jdk.web.hello;

import Spring.user.domain.User;
import org.apache.ibatis.executor.ReuseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.InternalResourceView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.*;

@org.springframework.stereotype.Controller
public class HelloController implements Controller {
    @Autowired HelloSpring helloSpring;
    @Autowired
    MessageSource messageSource;

    @Autowired
    LocaleResolver localeResolver;

    @RequestMapping("/hello")
    public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception{
        String name = req.getParameter("name");

        String message = this.helloSpring.sayHello(name);

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("message", message);

        View view = new InternalResourceView("hello.jsp");

        return new ModelAndView(view,model);
    }

    @RequestMapping("/test")
    public String testreturn(@Valid User req) {
        return "test";
    }

    @RequestMapping(value = "/main/i18n.do", method = RequestMethod.GET)
    public String i18n(Locale locale, HttpServletRequest request, Model model) {
        model.addAttribute("clientLocale", locale);
        model.addAttribute("sessionLocale", localeResolver.resolveLocale(request));
        model.addAttribute("siteCount", messageSource.getMessage("msg.first", null, locale));
        return "main/i18n";
    }

    @ResponseBody
    @RequestMapping(value="checkloginid/{loginId}", method=RequestMethod.GET)
    public Result checklogin(@PathVariable String loginId) {

        Result result = Result.builder().duplicated(true).availableId(loginId + (int)(Math.random()*1000)).build();

        return result;
    }
}

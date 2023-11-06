package bg.softuni.strengthstackshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/register")
    public ModelAndView register(){

        return new ModelAndView("register");
    }

    @GetMapping("/login")
    public ModelAndView login(){

        return new ModelAndView("login");
    }

}

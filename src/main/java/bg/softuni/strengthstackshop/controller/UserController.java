package bg.softuni.strengthstackshop.controller;

import bg.softuni.strengthstackshop.model.dto.user.UserRegisterBindingModel;
import bg.softuni.strengthstackshop.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("user/login")
    public ModelAndView login(){

        return new ModelAndView("login");
    }

    @PostMapping("user/login-error")
    public ModelAndView onFailure(
            @ModelAttribute("username") String username) {
        ModelAndView modelAndView = new ModelAndView("login");

        modelAndView.addObject("username", username);
        modelAndView.addObject("bad_credentials", "true");

        return modelAndView;
    }

    @GetMapping("user/register")
    public ModelAndView register(
            @ModelAttribute("userRegisterBindingModel") UserRegisterBindingModel userRegisterBindingModel){

        return new ModelAndView("register");

    }

    @PostMapping("user/register")
    public ModelAndView register(
            @ModelAttribute("userRegisterBindingModel") @Valid UserRegisterBindingModel userRegisterBindingModel,
            BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            return new ModelAndView("register");
        }

        boolean hasSuccessfulRegistration = userService.register(userRegisterBindingModel);

        if(!hasSuccessfulRegistration){
            ModelAndView modelAndView = new ModelAndView("register");
            modelAndView.addObject("hasRegistrationError", true);
            return modelAndView;
        }

        return new ModelAndView("redirect:/user/login");

    }


}

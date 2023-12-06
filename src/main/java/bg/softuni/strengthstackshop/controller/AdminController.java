package bg.softuni.strengthstackshop.controller;

import bg.softuni.strengthstackshop.model.dto.user.UserAdminViewDTO;
import bg.softuni.strengthstackshop.service.AdminService;
import bg.softuni.strengthstackshop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AdminController {

    private final AdminService adminService;
    private final UserService userService;

    public AdminController(AdminService adminService, UserService userService) {
        this.adminService = adminService;
        this.userService = userService;
    }

    @GetMapping("/adminPanel")
    public ModelAndView adminPanel(){
        ModelAndView modelAndView = new ModelAndView("adminPanel");

        List<UserAdminViewDTO> userAdminViewDTOS = adminService.getAllUsers();
        modelAndView.addObject("allUsers", userAdminViewDTOS);

        return modelAndView;
    }

    @PostMapping("/removeUser")
    public ModelAndView removeUser(@RequestParam("userId") Long userId){

        ModelAndView modelAndView = new ModelAndView();
        adminService.removeUserById(userId);

        modelAndView.setViewName("redirect:/adminPanel");

        return modelAndView;

    }


}

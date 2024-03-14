package bg.softuni.strengthstackshop.controller;

import bg.softuni.strengthstackshop.model.dto.user.UserAdminViewDTO;
import bg.softuni.strengthstackshop.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AdminController {

    private final AdminService adminService;

    private static final String REDIRECT_ADMIN_PANEL = "redirect:/admin-panel";

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/admin-panel")
    public ModelAndView adminPanel(){
        ModelAndView modelAndView = new ModelAndView("admin-panel");

        List<UserAdminViewDTO> userAdminViewDTOS = adminService.getAllUsers();
        modelAndView.addObject("allUsers", userAdminViewDTOS);

        return modelAndView;
    }

    @PostMapping("/remove-user")
    public ModelAndView removeUser(@RequestParam("userId") Long userId){

        ModelAndView modelAndView = new ModelAndView();
        adminService.removeUserById(userId);

        modelAndView.setViewName(REDIRECT_ADMIN_PANEL);

        return modelAndView;

    }

    @PostMapping("/change-role-to-admin")
    public ModelAndView changeRoleToAdmin(@RequestParam("userId") Long userId){

        ModelAndView modelAndView = new ModelAndView();
        adminService.changeRoleToAdmin(userId);

        modelAndView.setViewName(REDIRECT_ADMIN_PANEL);

        return modelAndView;
    }

    @PostMapping("/change-role-to-user")
    public ModelAndView changeRoleToUser(@RequestParam("userId") Long userId){

        ModelAndView modelAndView = new ModelAndView();
        adminService.changeRoleToUser(userId);

        modelAndView.setViewName(REDIRECT_ADMIN_PANEL);

        return modelAndView;
    }

}

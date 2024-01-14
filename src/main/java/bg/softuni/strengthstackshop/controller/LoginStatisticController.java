package bg.softuni.strengthstackshop.controller;

import bg.softuni.strengthstackshop.util.LoginStatisticInterceptor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class LoginStatisticController {

    private final LoginStatisticInterceptor loginStatisticInterceptor;

    public LoginStatisticController(LoginStatisticInterceptor loginStatisticInterceptor) {
        this.loginStatisticInterceptor = loginStatisticInterceptor;
    }

    @GetMapping("/login-statistic")
    public String loginStatistics(Model model, Principal principal) {
        if (isAdmin(principal)) {
            model.addAttribute("loginCounts", loginStatisticInterceptor.getLoginCountMap());
            return "login-statistic";
        } else {
            return "redirect:/";
        }
    }

    private boolean isAdmin(Principal principal) {
        if (principal != null) {
            return ((Authentication) principal).getAuthorities().stream()
                    .anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"));
        }
        return false;
    }
}

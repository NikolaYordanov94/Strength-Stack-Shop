package bg.softuni.strengthstackshop.controller;

import bg.softuni.strengthstackshop.model.dto.order.OrderHomeViewDTO;
import bg.softuni.strengthstackshop.model.entity.Order;
import bg.softuni.strengthstackshop.repository.OrderRepository;
import bg.softuni.strengthstackshop.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {
    private final OrderService orderService;

    public HomeController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/")
    public ModelAndView index(){

        return new ModelAndView("index");
    }

    @GetMapping("/home")
    public ModelAndView home(Principal principal){
        ModelAndView modelAndView = new ModelAndView();

        List<OrderHomeViewDTO> currentUserOrders = orderService
                .findOrdersByUsername(principal.getName());

        modelAndView.setViewName("home");
        modelAndView.addObject("currentUserOrders", currentUserOrders);


        return modelAndView;
    }


    @GetMapping("/contacts")
    public ModelAndView aboutUs(){

        return new ModelAndView("contacts");
    }

}

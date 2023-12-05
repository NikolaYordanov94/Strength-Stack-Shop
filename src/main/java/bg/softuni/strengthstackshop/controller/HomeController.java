package bg.softuni.strengthstackshop.controller;

import bg.softuni.strengthstackshop.model.entity.Order;
import bg.softuni.strengthstackshop.repository.OrderRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {

    private final OrderRepository orderRepository;

    public HomeController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/")
    public ModelAndView index(){

        return new ModelAndView("index");
    }

    @GetMapping("/home")
    public ModelAndView home(Principal principal){
        ModelAndView modelAndView = new ModelAndView();

        List<Order> currentUserOrders = orderRepository
                .findByUserUsername(principal.getName());

        modelAndView.setViewName("home");
        modelAndView.addObject("currentUserOrders", currentUserOrders);


        return modelAndView;
    }


    @GetMapping("/contacts")
    public ModelAndView aboutUs(){

        return new ModelAndView("contacts");
    }

}

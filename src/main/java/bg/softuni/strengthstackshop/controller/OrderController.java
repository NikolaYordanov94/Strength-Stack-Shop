package bg.softuni.strengthstackshop.controller;

import bg.softuni.strengthstackshop.model.entity.Order;
import bg.softuni.strengthstackshop.service.OrderService;
import bg.softuni.strengthstackshop.service.ProductService;
import bg.softuni.strengthstackshop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService, UserService userService, ProductService productService) {
        this.orderService = orderService;
    }

    @PostMapping("/removeProduct")
    public ModelAndView removeProductFromOrder(@RequestParam("productId") Long productId, Principal principal){
        ModelAndView modelAndView = new ModelAndView();
        orderService.removeProductFromOrder(productId, principal);

        modelAndView.setViewName("redirect:/product-buy");

        return modelAndView;
    }

    @GetMapping("/product-buy")
    public ModelAndView getCurrentActiveOrder(Principal principal){
        ModelAndView modelAndView = new ModelAndView();
        Order activeOrder = orderService.findActiveOrder(principal);

        modelAndView.addObject("currentOrderProducts", activeOrder.getProducts());
        modelAndView.addObject("currentOrder", activeOrder);

        modelAndView.setViewName("product-buy");

        return modelAndView;
    }

}

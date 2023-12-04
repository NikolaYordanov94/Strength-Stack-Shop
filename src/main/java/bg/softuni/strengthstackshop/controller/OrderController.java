package bg.softuni.strengthstackshop.controller;

import bg.softuni.strengthstackshop.model.entity.Order;
import bg.softuni.strengthstackshop.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class OrderController {

    private final OrderService orderService;


    public OrderController(OrderService orderService) {
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

    @PostMapping("/finishOrder")
    public ModelAndView finishCurrentActiveOrder(@RequestParam("orderId") Long orderId){
        ModelAndView modelAndView = new ModelAndView();
        Order currentOrder = orderService.findCurrentOrderById(orderId);

        orderService.deactivateCurrentActiveOrder(currentOrder.getUser());
        modelAndView.setViewName("redirect:/home");

        return modelAndView;
    }

}

package bg.softuni.strengthstackshop.controller;

import bg.softuni.strengthstackshop.model.dto.product.ProductAddBindingModel;
import bg.softuni.strengthstackshop.model.entity.Order;
import bg.softuni.strengthstackshop.model.entity.Product;
import bg.softuni.strengthstackshop.model.entity.User;
import bg.softuni.strengthstackshop.service.OrderService;
import bg.softuni.strengthstackshop.service.ProductService;
import bg.softuni.strengthstackshop.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class ProductController {

    private final ProductService productService;
    private final UserService userService;
    private final OrderService orderService;

    public ProductController(ProductService productService, UserService userService, OrderService orderService) {
        this.productService = productService;
        this.userService = userService;
        this.orderService = orderService;
    }

    @GetMapping("/product-add")
    public ModelAndView addProduct(
            @ModelAttribute("productAddBindingModel") ProductAddBindingModel productAddBindingModel){

        return new ModelAndView("product-add");
    }

    @PostMapping("/product-add")
    public ModelAndView addProduct(
            @ModelAttribute("productAddBindingModel") @Valid ProductAddBindingModel productAddBindingModel,
            BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            return new ModelAndView("product-add");
        }

        productService.add(productAddBindingModel);

        return new ModelAndView("redirect:/home");
    }

    @PostMapping("product-buy")
    public ModelAndView buyProduct(@RequestParam("productId") Long productId, Principal principal){
        ModelAndView modelAndView = new ModelAndView("product-buy");

        User user = userService.findByUsername(principal.getName());
        Product product = productService.findById(productId);
        Order activeOrder = orderService.findOrCreateActiveOrder(user, product);

        activeOrder.getProducts().add(product);
        activeOrder.setTotalPrice(activeOrder.getTotalPrice().add(product.getPrice()));
        orderService.saveOrderProduct(activeOrder, product);

        modelAndView.addObject("currentOrderProducts", activeOrder.getProducts());
        modelAndView.addObject("currentOrder", activeOrder);

        return modelAndView;
    }

    @GetMapping("/offers")
    public ModelAndView allProducts(){
        ModelAndView modelAndView = new ModelAndView("offers");

        modelAndView.addObject("allOffers", productService.getAllProducts());

        return modelAndView;
    }

    @GetMapping("/offers-supplements")
    public ModelAndView allSupplements(){
        ModelAndView modelAndView = new ModelAndView("offers-supplements");

        modelAndView.addObject("allSupplements", productService.getAllSupplements());

        return modelAndView;
    }

    @GetMapping("/offers-gear")
    public ModelAndView allGear(){
        ModelAndView modelAndView = new ModelAndView("offers-gear");

        modelAndView.addObject("allGear", productService.getAllGear());

        return modelAndView;
    }

    @GetMapping("/offers-clothes")
    public ModelAndView allClothes(){
        ModelAndView modelAndView = new ModelAndView("offers-clothes");

        modelAndView.addObject("allClothes", productService.getAllClothes());

        return modelAndView;
    }


    @GetMapping("/product-search")
    public ModelAndView searchProduct(){

        ModelAndView modelAndView = new ModelAndView("product-search");


        return modelAndView;
    }

    @PostMapping("/product-search")
    public ModelAndView searchProduct(ProductAddBindingModel productAddBindingModel){

        ModelAndView modelAndView = new ModelAndView("product-search");

        return modelAndView;
    }

}

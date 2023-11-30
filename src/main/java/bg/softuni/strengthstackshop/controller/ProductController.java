package bg.softuni.strengthstackshop.controller;

import bg.softuni.strengthstackshop.model.dto.product.ProductAddBindingModel;
import bg.softuni.strengthstackshop.model.entity.Product;
import bg.softuni.strengthstackshop.model.entity.User;
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

    public ProductController(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
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
        ModelAndView modelAndView = new ModelAndView();

        User user = userService.findByUsername(principal.getName());
        Product product = productService.findById(productId);




        return new ModelAndView();
    }

    @GetMapping("/offers")
    public ModelAndView allProducts(){
        ModelAndView modelAndView = new ModelAndView("offers");

        modelAndView.addObject("allOffers", productService.getAllProducts());

        return modelAndView;
    }

}

package bg.softuni.strengthstackshop.controller;

import bg.softuni.strengthstackshop.model.dto.product.ProductAddBindingModel;
import bg.softuni.strengthstackshop.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
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

}

package bg.softuni.strengthstackshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductController {

    @GetMapping("/product-add")
    public ModelAndView addProduct(){

        return new ModelAndView("product-add");
    }

}

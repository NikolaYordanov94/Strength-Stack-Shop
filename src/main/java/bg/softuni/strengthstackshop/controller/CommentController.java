package bg.softuni.strengthstackshop.controller;

import bg.softuni.strengthstackshop.model.entity.Product;
import bg.softuni.strengthstackshop.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    @GetMapping("/product-details")
    public ModelAndView productDetails(@RequestParam ("productId") Long productId){
        ModelAndView modelAndView = new ModelAndView();

        Product currentProduct = commentService.findProductById(productId);

        modelAndView.setViewName("product-details");
        modelAndView.addObject("currentProduct", currentProduct);

        return modelAndView;
    }

}

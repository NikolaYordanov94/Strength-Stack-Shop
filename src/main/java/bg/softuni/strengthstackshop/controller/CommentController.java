package bg.softuni.strengthstackshop.controller;

import bg.softuni.strengthstackshop.model.dto.comment.CommentCreateBindingModel;
import bg.softuni.strengthstackshop.model.entity.Product;
import bg.softuni.strengthstackshop.service.CommentService;
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
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    @GetMapping("/product-details")
    public ModelAndView productDetails(@RequestParam ("productId") Long productId,
                                       @ModelAttribute("commentCreateBindingModel")
                                       CommentCreateBindingModel commentCreateBindingModel){
        ModelAndView modelAndView = new ModelAndView();

        Product currentProduct = commentService.findProductById(productId);

        modelAndView.setViewName("product-details");
        modelAndView.addObject("currentProduct", currentProduct);

        return modelAndView;
    }

    @PostMapping("/comment-create")
    public ModelAndView createComment(@RequestParam ("productId") Long productId,
                                      @ModelAttribute("commentCreateBindingModel")
                                      @Valid CommentCreateBindingModel commentCreateBindingModel,
                                      BindingResult bindingResult, Principal principal){
        ModelAndView modelAndView = new ModelAndView();
        Product currentProduct = commentService.findProductById(productId);
        modelAndView.addObject("currentProduct", currentProduct);

//        if (bindingResult.hasErrors()) {
//            modelAndView.setViewName("product-details");
//            return modelAndView;
//        }

         commentService.createComment(commentCreateBindingModel, principal.getName(), productId);

        modelAndView.setViewName("redirect:/offers");
        return modelAndView;
    }

}

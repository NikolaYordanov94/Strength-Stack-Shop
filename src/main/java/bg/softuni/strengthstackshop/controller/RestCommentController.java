//package bg.softuni.strengthstackshop.controller;
//
//import bg.softuni.strengthstackshop.model.entity.Product;
//import bg.softuni.strengthstackshop.service.CommentService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class RestCommentController {
//
//    private final CommentService commentService;
//
//    public RestCommentController(CommentService commentService) {
//        this.commentService = commentService;
//    }
//
//    @GetMapping("/product-details/view/{id}")
//    public ResponseEntity<Product> getProductDetails(@PathVariable("id") Long id) {
//        Product currentProduct = commentService.findProductById(id);
//        return ResponseEntity.ok(currentProduct);
//    }
//}

//todo create Fetch with JS
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
//    @GetMapping("/product-details/{productId}")
//    public ResponseEntity<Product> getProductDetails(@PathVariable("productId") Long productId) {
//        Product currentProduct = commentService.findProductById(productId);
//        return ResponseEntity.ok(currentProduct);
//    }
//}

//todo create Fetch with JS
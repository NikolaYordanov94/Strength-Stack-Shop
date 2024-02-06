//package bg.softuni.strengthstackshop.controller;
//import bg.softuni.strengthstackshop.model.dto.comment.CommentShowDTO;
//import bg.softuni.strengthstackshop.service.CommentService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@CrossOrigin(origins = "http://localhost:8080")
//public class RestCommentController {
//
//    private final CommentService commentService;
//
//    public RestCommentController(CommentService commentService) {
//        this.commentService = commentService;
//    }
//
//    @GetMapping("/product-details/comments/{productId}")
//    public ResponseEntity<List<CommentShowDTO>> showComments(@PathVariable("productId") Long productId) {
//
//        return ResponseEntity.ok(commentService.findCommentsByProductId(productId));
//    }
//}

//todo create Fetch with JS
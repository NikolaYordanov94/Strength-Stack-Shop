package bg.softuni.strengthstackshop.service;

import bg.softuni.strengthstackshop.model.dto.comment.CommentCreateBindingModel;
import bg.softuni.strengthstackshop.model.dto.comment.CommentShowDTO;
import bg.softuni.strengthstackshop.model.entity.Product;

import java.util.List;

public interface CommentService {
    Product findProductById(Long productId);


    void createComment(CommentCreateBindingModel commentCreateBindingModel, String username, Long productId);

    void clearOldComments();

    List<CommentShowDTO> findCommentsByProductId(Long id);

}

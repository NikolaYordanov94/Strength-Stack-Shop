package bg.softuni.strengthstackshop.service;

import bg.softuni.strengthstackshop.model.dto.comment.CommentCreateBindingModel;
import bg.softuni.strengthstackshop.model.entity.Product;

public interface CommentService {
    Product findProductById(Long productId);


    void createComment(CommentCreateBindingModel commentCreateBindingModel, String username, Long productId);

}

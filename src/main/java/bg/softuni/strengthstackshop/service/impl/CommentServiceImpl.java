package bg.softuni.strengthstackshop.service.impl;

import bg.softuni.strengthstackshop.model.dto.comment.CommentCreateBindingModel;
import bg.softuni.strengthstackshop.model.dto.comment.CommentShowDTO;
import bg.softuni.strengthstackshop.model.entity.Comment;
import bg.softuni.strengthstackshop.model.entity.Product;
import bg.softuni.strengthstackshop.model.entity.User;
import bg.softuni.strengthstackshop.repository.CommentRepository;
import bg.softuni.strengthstackshop.repository.ProductRepository;
import bg.softuni.strengthstackshop.repository.UserRepository;
import bg.softuni.strengthstackshop.service.CommentService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final ProductRepository productRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public CommentServiceImpl(ProductRepository productRepository, CommentRepository commentRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Product findProductById(Long productId) {

        return productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + productId));
    }

    @Override
    public void createComment(CommentCreateBindingModel commentCreateBindingModel, String username, Long productId) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found with username: " + username));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + productId));

        Comment comment = new Comment();
        comment.setCommentDate(LocalDate.now());
        comment.setDescription(commentCreateBindingModel.getDescription());
        comment.setUser(user);
        comment.setProduct(product);

        commentRepository.save(comment);
        productRepository.save(product);
    }

    @Override
    public void clearOldComments() {

        List<Comment> oldComments = commentRepository.findAllByCommentDateBefore(LocalDateTime
                .now().minusYears(5)).orElse(new ArrayList<>());

        if (!oldComments.isEmpty()) {
            commentRepository.deleteAll(oldComments);
        }
    }

    @Override
    public List<CommentShowDTO> findCommentsByProductId(Long id) {

        return commentRepository.findAllByProduct_id(id)
                .stream()
                .map(comment -> modelMapper.map(comment, CommentShowDTO.class))
                .toList();

    }
}

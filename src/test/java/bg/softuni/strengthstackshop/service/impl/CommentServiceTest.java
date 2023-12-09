package bg.softuni.strengthstackshop.service.impl;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import bg.softuni.strengthstackshop.model.dto.comment.CommentCreateBindingModel;
import bg.softuni.strengthstackshop.model.entity.Comment;
import bg.softuni.strengthstackshop.model.entity.Product;
import bg.softuni.strengthstackshop.model.entity.User;
import bg.softuni.strengthstackshop.repository.CommentRepository;
import bg.softuni.strengthstackshop.repository.ProductRepository;
import bg.softuni.strengthstackshop.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class CommentServiceTest {

    @Mock
    private ProductRepository productRepository;
    @Mock
    private CommentRepository commentRepository;
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CommentServiceImpl commentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findProductById_ProductExists() {
        Long productId = 1L;
        Product mockProduct = new Product(); // Assuming Product is a valid class
        when(productRepository.findById(productId)).thenReturn(Optional.of(mockProduct));

        Product result = commentService.findProductById(productId);

        assertNotNull(result);
        assertEquals(mockProduct, result);
    }

    @Test
    void findProductById_ProductDoesNotExist() {
        Long productId = 1L;
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> commentService.findProductById(productId));
    }

    @Test
    void createComment_Success() {
        CommentCreateBindingModel model = new CommentCreateBindingModel();
        model.setDescription("Test Comment");
        String username = "testUser";
        Long productId = 1L;

        User mockUser = new User(); // Replace with your User class
        Product mockProduct = new Product(); // Replace with your Product class
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(mockUser));
        when(productRepository.findById(productId)).thenReturn(Optional.of(mockProduct));

        commentService.createComment(model, username, productId);

        verify(commentRepository).save(any(Comment.class));
    }

    @Test
    void createComment_UserNotFound() {
        CommentCreateBindingModel model = new CommentCreateBindingModel();
        String username = "nonExistentUser";
        Long productId = 1L;

        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> commentService.createComment(model, username, productId));
    }

    @Test
    void createComment_ProductNotFound() {
        CommentCreateBindingModel model = new CommentCreateBindingModel();
        String username = "testUser";
        Long productId = 1L;

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(new User())); // Assuming User is a valid class
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> commentService.createComment(model, username, productId));
    }

    @Test
    void clearOldComments_WithOldComments() {
        List<Comment> oldComments = List.of(new Comment()); // Assuming Comment is a valid class
        when(commentRepository.findAllByCommentDateBefore(any(LocalDateTime.class)))
                .thenReturn(Optional.of(oldComments));

        commentService.clearOldComments();

        verify(commentRepository).deleteAll(oldComments);
    }

    @Test
    void clearOldComments_NoOldComments() {
        when(commentRepository.findAllByCommentDateBefore(any(LocalDateTime.class)))
                .thenReturn(Optional.of(new ArrayList<>()));

        commentService.clearOldComments();

        verify(commentRepository, never()).deleteAll(anyList());
    }
}

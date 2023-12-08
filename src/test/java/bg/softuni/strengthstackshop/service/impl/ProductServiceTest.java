package bg.softuni.strengthstackshop.service.impl;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import bg.softuni.strengthstackshop.model.dto.product.ProductViewDTO;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import bg.softuni.strengthstackshop.repository.ProductRepository;
import bg.softuni.strengthstackshop.model.dto.product.ProductAddBindingModel;
import bg.softuni.strengthstackshop.model.entity.Product;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    public void testAddProduct() {
        // Arrange
        ProductAddBindingModel bindingModel = createProductAddBindingModel();
        // Act
        productService.add(bindingModel);
        // Assert
        verify(productRepository).save(any(Product.class));
    }

    @Test
    public void testGetAllProducts() {
        // Arrange
        when(productRepository.findAll()).thenReturn(Collections.singletonList(new Product()));
        // Act
        List<ProductViewDTO> products = productService.getAllProducts();
        // Assert
        assertFalse(products.isEmpty());
        verify(productRepository).findAll();
    }

    @Test
    public void testFindById_Success() {
        // Arrange
        Long productId = 1L;
        Product product = new Product();
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        // Act
        Product foundProduct = productService.findById(productId);
        // Assert
        assertNotNull(foundProduct);
    }

    @Test
    public void testFindById_NotFound() {
        // Arrange
        Long productId = 1L;
        when(productRepository.findById(productId)).thenReturn(Optional.empty());
        // Act & Assert
        assertThrows(EntityNotFoundException.class, () -> productService.findById(productId));
    }

    private ProductAddBindingModel createProductAddBindingModel() {
        ProductAddBindingModel model = new ProductAddBindingModel();

        return model;
    }
}

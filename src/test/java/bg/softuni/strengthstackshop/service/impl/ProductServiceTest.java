package bg.softuni.strengthstackshop.service.impl;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import bg.softuni.strengthstackshop.model.dto.product.ProductViewDTO;
import bg.softuni.strengthstackshop.model.enums.Category;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import bg.softuni.strengthstackshop.repository.ProductRepository;
import bg.softuni.strengthstackshop.model.dto.product.ProductAddBindingModel;
import bg.softuni.strengthstackshop.model.entity.Product;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ModelMapper modelMapper;

    @Test
    void testFindById_Success() {
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
    void testFindById_NotFound() {
        // Arrange
        Long productId = 1L;
        when(productRepository.findById(productId)).thenReturn(Optional.empty());
        // Act & Assert
        assertThrows(EntityNotFoundException.class, () -> productService.findById(productId));
    }

    @Test
    void getAllSupplementsTest() {
        List<Product> mockSupplements = List.of(new Product()); // Create a mock list of products
        when(productRepository.findAllByCategory(Category.SUPPLEMENTS)).thenReturn(mockSupplements);

        ProductViewDTO mockDto = new ProductViewDTO(); // Mock ProductViewDTO
        when(modelMapper.map(any(Product.class), eq(ProductViewDTO.class))).thenReturn(mockDto);

        List<ProductViewDTO> supplements = productService.getAllSupplements();

        assertFalse(supplements.isEmpty());
        assertEquals(mockSupplements.size(), supplements.size());
        verify(productRepository).findAllByCategory(Category.SUPPLEMENTS);
    }

    @Test
    void getAllGearTest() {
        List<Product> mockGear = List.of(new Product());
        when(productRepository.findAllByCategory(Category.GEAR)).thenReturn(mockGear);

        ProductViewDTO mockDto = new ProductViewDTO();
        when(modelMapper.map(any(Product.class), eq(ProductViewDTO.class))).thenReturn(mockDto);

        List<ProductViewDTO> gear = productService.getAllGear();

        assertFalse(gear.isEmpty());
        assertEquals(mockGear.size(), gear.size());
        verify(productRepository).findAllByCategory(Category.GEAR);
    }

    @Test
    void getAllClothesTest() {
        List<Product> mockClothes = List.of(new Product());
        when(productRepository.findAllByCategory(Category.CLOTHES)).thenReturn(mockClothes);

        ProductViewDTO mockDto = new ProductViewDTO();
        when(modelMapper.map(any(Product.class), eq(ProductViewDTO.class))).thenReturn(mockDto);

        List<ProductViewDTO> clothes = productService.getAllClothes();

        assertFalse(clothes.isEmpty());
        assertEquals(mockClothes.size(), clothes.size());
        verify(productRepository).findAllByCategory(Category.CLOTHES);
    }

    private ProductAddBindingModel createProductAddBindingModel() {
        ProductAddBindingModel model = new ProductAddBindingModel();

        return model;
    }
}
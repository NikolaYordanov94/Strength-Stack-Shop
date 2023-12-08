package bg.softuni.strengthstackshop.service.impl;

import bg.softuni.strengthstackshop.model.dto.product.ProductViewDTO;
import bg.softuni.strengthstackshop.model.dto.product.ProductAddBindingModel;
import bg.softuni.strengthstackshop.model.entity.Product;
import bg.softuni.strengthstackshop.repository.ProductRepository;
import bg.softuni.strengthstackshop.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public void add(ProductAddBindingModel productAddBindingModel) {

        Product product = modelMapper.map(productAddBindingModel, Product.class);

        productRepository.save(product);

    }

    @Override
    public List<ProductViewDTO> getAllProducts() {
        List<Product> allProducts = productRepository.findAll();
        List<ProductViewDTO> allProductsListViewDTOS = new ArrayList<>();

        allProducts.forEach(product -> {
            ProductViewDTO productDTO = modelMapper.map(product, ProductViewDTO.class);

            allProductsListViewDTOS.add(productDTO);
        });
        return allProductsListViewDTOS;
    }

    @Override
    public Product findById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + productId));

    }

}

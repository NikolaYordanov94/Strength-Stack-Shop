package bg.softuni.strengthstackshop.service.impl;

import bg.softuni.strengthstackshop.model.dto.product.AllProductsListViewDTO;
import bg.softuni.strengthstackshop.model.dto.product.ProductAddBindingModel;
import bg.softuni.strengthstackshop.model.entity.Product;
import bg.softuni.strengthstackshop.repository.ProductRepository;
import bg.softuni.strengthstackshop.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public void add(ProductAddBindingModel productAddBindingModel) {

        Product product = new Product();

        product.setDescription(productAddBindingModel.getDescription());
        product.setBrand(productAddBindingModel.getBrand());
        product.setPrice(productAddBindingModel.getPrice());
        product.setPictureUrl(productAddBindingModel.getPictureUrl());
        product.setCreatedAt(productAddBindingModel.getCreatedAt());
        product.setCategory(productAddBindingModel.getCategory());

        productRepository.save(product);

    }

    @Override
    public List<AllProductsListViewDTO> getAllProducts() {
        List<Product> allProducts = productRepository.findAll();
        List<AllProductsListViewDTO> allProductsListViewDTOS = new ArrayList<>();

        allProducts.forEach(product -> {
            AllProductsListViewDTO productDTO = new AllProductsListViewDTO();
            productDTO.setBrand(product.getBrand());
            productDTO.setCategory(product.getCategory());
            productDTO.setDescription(product.getDescription());
            productDTO.setPrice(product.getPrice());
            productDTO.setPictureUrl(product.getPictureUrl());

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

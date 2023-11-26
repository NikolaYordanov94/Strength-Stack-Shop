package bg.softuni.strengthstackshop.service.impl;

import bg.softuni.strengthstackshop.model.dto.product.ProductAddBindingModel;
import bg.softuni.strengthstackshop.model.entity.Product;
import bg.softuni.strengthstackshop.repository.ProductRepository;
import bg.softuni.strengthstackshop.service.ProductService;
import org.springframework.stereotype.Service;

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
}

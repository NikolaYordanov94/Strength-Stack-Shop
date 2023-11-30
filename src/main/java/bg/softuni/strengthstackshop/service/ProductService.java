package bg.softuni.strengthstackshop.service;

import bg.softuni.strengthstackshop.model.dto.product.AllProductsListViewDTO;
import bg.softuni.strengthstackshop.model.dto.product.ProductAddBindingModel;
import bg.softuni.strengthstackshop.model.entity.Product;

import java.util.List;

public interface ProductService {

    void add(ProductAddBindingModel productAddBindingModel);

    List<AllProductsListViewDTO> getAllProducts();

    Product findById(Long productId);
}

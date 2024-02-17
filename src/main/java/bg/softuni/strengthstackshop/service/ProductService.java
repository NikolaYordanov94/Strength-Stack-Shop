package bg.softuni.strengthstackshop.service;

import bg.softuni.strengthstackshop.model.dto.product.ProductSearchBindingModel;
import bg.softuni.strengthstackshop.model.dto.product.ProductViewDTO;
import bg.softuni.strengthstackshop.model.dto.product.ProductAddBindingModel;
import bg.softuni.strengthstackshop.model.entity.Product;

import java.util.List;

public interface ProductService {

    void add(ProductAddBindingModel productAddBindingModel);

    List<ProductViewDTO> getAllProducts();

    List<ProductViewDTO> getAllSupplements();

    List<ProductViewDTO> getAllGear();

    List<ProductViewDTO> getAllClothes();

    Product findById(Long productId);

    List<ProductViewDTO> findByInput(ProductSearchBindingModel productSearchBindingModel);
}

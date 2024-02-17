package bg.softuni.strengthstackshop.repository;

import bg.softuni.strengthstackshop.model.entity.Product;
import bg.softuni.strengthstackshop.model.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByCategory(Category category);

    @Query("SELECT p FROM Product p WHERE (p.brand = :brand) " +
            "OR (p.description LIKE %:description%) " +
            "OR (p.price >= :minPrice) " +
            "OR (p.price <= :maxPrice)")
    List<Product> findByBrandAndDescriptionAndPriceRange(@Param("brand") String brand,
                                                         @Param("description") String description,
                                                         @Param("minPrice") BigDecimal minPrice,
                                                         @Param("maxPrice") BigDecimal maxPrice);

}

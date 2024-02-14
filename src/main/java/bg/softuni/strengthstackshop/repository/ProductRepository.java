package bg.softuni.strengthstackshop.repository;

import bg.softuni.strengthstackshop.model.entity.Product;
import bg.softuni.strengthstackshop.model.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByCategory(Category category);

    @Query("SELECT p FROM Product p WHERE (:brand IS NULL OR p.brand = :brand) " +
            "AND (:description IS NULL OR p.description LIKE %:description%) " +
            "AND (:minPrice IS NULL OR p.price >= :minPrice) " +
            "AND (:maxPrice IS NULL OR p.price <= :maxPrice)")
    List<Product> findByBrandAndDescriptionAndPriceRange(@Param("brand") Optional<String> brand,
                                                         @Param("description") Optional<String> description,
                                                         @Param("minPrice") Optional<Double> minPrice,
                                                         @Param("maxPrice") Optional<Double> maxPrice);

}

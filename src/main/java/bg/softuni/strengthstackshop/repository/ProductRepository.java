package bg.softuni.strengthstackshop.repository;

import bg.softuni.strengthstackshop.model.entity.Product;
import bg.softuni.strengthstackshop.model.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByCategory(Category category);

}

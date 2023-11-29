package bg.softuni.strengthstackshop.repository;

import bg.softuni.strengthstackshop.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    

}

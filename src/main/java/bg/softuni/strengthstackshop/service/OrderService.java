package bg.softuni.strengthstackshop.service;

import bg.softuni.strengthstackshop.model.entity.Order;
import bg.softuni.strengthstackshop.model.entity.Product;
import bg.softuni.strengthstackshop.model.entity.User;

import java.security.Principal;

public interface OrderService {

    Order findOrCreateActiveOrder(User user, Product product);

    Order createNewOrder(User user);

    void deactivateCurrentActiveOrder(User user);

    void saveOrderProduct(Order order, Product product);

    void removeProductFromOrder(Long productId, Principal principal);

    Order findActiveOrder(Principal principal);

    User findCurrentUserByUsername(Principal principal);

    Order findCurrentOrderById(Long orderId);
}

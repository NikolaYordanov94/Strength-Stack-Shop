package bg.softuni.strengthstackshop.service;

import bg.softuni.strengthstackshop.model.entity.Order;
import bg.softuni.strengthstackshop.model.entity.Product;
import bg.softuni.strengthstackshop.model.entity.User;

public interface OrderService {

    Order findOrCreateActiveOrder(User user, Product product);

    Order createNewOrder(User user);

    void deactivateCurrentActiveOrder(User user);

    void saveOrderProduct(Order order, Product product);

}

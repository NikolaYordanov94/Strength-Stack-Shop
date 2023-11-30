package bg.softuni.strengthstackshop.service;

import bg.softuni.strengthstackshop.model.entity.Order;
import bg.softuni.strengthstackshop.model.entity.User;

public interface OrderService {

    Order findOrCreateActiveOrder(User user);

    Order createNewOrder(User user);

    void deactivateCurrentActiveOrder(User user);

}

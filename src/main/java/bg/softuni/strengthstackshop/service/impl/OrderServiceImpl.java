package bg.softuni.strengthstackshop.service.impl;

import bg.softuni.strengthstackshop.model.entity.Order;
import bg.softuni.strengthstackshop.model.entity.User;
import bg.softuni.strengthstackshop.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    


    @Override
    public Order findOrCreateActiveOrder(User user) {
        return null;
    }

    @Override
    public Order createNewOrder(User user) {
        return null;
    }

    @Override
    public Order deactivateCurrentActiveOrder(User user) {
        return null;
    }
}

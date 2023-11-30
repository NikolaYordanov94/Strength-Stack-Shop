package bg.softuni.strengthstackshop.service.impl;

import bg.softuni.strengthstackshop.model.entity.Order;
import bg.softuni.strengthstackshop.model.entity.User;
import bg.softuni.strengthstackshop.repository.OrderRepository;
import bg.softuni.strengthstackshop.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    @Override
    public Order findOrCreateActiveOrder(User user) {
        Optional<Order> activeOrder = user.getOrders().stream()
                .filter(Order::isActive)
                .findFirst();

        return activeOrder.orElseGet(() -> createNewOrder(user));
    }

    @Override
    public Order createNewOrder(User user) {

        deactivateCurrentActiveOrder(user);

        Order newOrder = new Order();
        newOrder.setUser(user);
        newOrder.setActive(true);
        user.getOrders().add(newOrder);

        return orderRepository.save(newOrder);
    }

    @Override
    public void deactivateCurrentActiveOrder(User user) {

        user.getOrders().stream()
                .filter(Order::isActive)
                .forEach(order -> order.setActive(false));
    }
}

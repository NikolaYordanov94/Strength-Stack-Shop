package bg.softuni.strengthstackshop.service.impl;

import bg.softuni.strengthstackshop.model.entity.Order;
import bg.softuni.strengthstackshop.model.entity.Product;
import bg.softuni.strengthstackshop.model.entity.User;
import bg.softuni.strengthstackshop.repository.OrderRepository;
import bg.softuni.strengthstackshop.repository.ProductRepository;
import bg.softuni.strengthstackshop.service.OrderService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderServiceImpl(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }


    @Override
    public Order findOrCreateActiveOrder(User user, Product product) {
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
        newOrder.setOrderDate(LocalDate.now());
        newOrder.setTotalPrice(BigDecimal.valueOf(0.00));
        user.getOrders().add(newOrder);

        return orderRepository.save(newOrder);
    }

    @Override
    public void deactivateCurrentActiveOrder(User user) {

        user.getOrders().stream()
                .filter(Order::isActive)
                .forEach(order -> {
                    order.setActive(false);
                    orderRepository.save(order);
                });
    }

    @Override
    public void saveOrderProduct(Order order, Product product) {
        orderRepository.save(order);
        productRepository.save(product);
    }
}

package bg.softuni.strengthstackshop.service.impl;

import bg.softuni.strengthstackshop.model.dto.order.OrderHomeViewDTO;
import bg.softuni.strengthstackshop.model.entity.Order;
import bg.softuni.strengthstackshop.model.entity.Product;
import bg.softuni.strengthstackshop.model.entity.User;
import bg.softuni.strengthstackshop.repository.OrderRepository;
import bg.softuni.strengthstackshop.repository.ProductRepository;
import bg.softuni.strengthstackshop.repository.UserRepository;
import bg.softuni.strengthstackshop.service.OrderService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public OrderServiceImpl(OrderRepository orderRepository, ProductRepository productRepository, UserRepository userRepository, UserRepository userRepository1, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository1;
        this.modelMapper = modelMapper;
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

    @Override
    public void removeProductFromOrder(Long productId, Principal principal) {

        Order order = orderRepository.findByUserUsername(principal.getName())
                .stream().filter(Order::isActive)
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Active order not found for user: " + principal.getName()));

         Product productToRemove = order.getProducts().stream()
             .filter(product -> product.getId() == productId)
             .findFirst()
             .orElseThrow(() -> new RuntimeException("Product not found in active order"));

         order.setTotalPrice(order.getTotalPrice().subtract(productToRemove.getPrice()));

         order.getProducts().remove(productToRemove);
         orderRepository.save(order);
    }

    @Override
    public Order findActiveOrder(Principal principal) {
        return orderRepository.findByUserUsername(principal.getName())
                .stream().filter(Order::isActive)
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Active order not found for user: " + principal.getName()));

    }

    @Override
    public User findCurrentUserByUsername(Principal principal) {

        return userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + principal.getName()));
    }

    @Override
    public Order findCurrentOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id: " + orderId));
    }

    @Override
    public List<OrderHomeViewDTO> findOrdersByUsername(String username) {

        List<Order> orders = orderRepository.findByUserUsername(username);
        List<OrderHomeViewDTO> orderHomeViewDTOList = new ArrayList<>();

        orders.forEach(order -> {
            OrderHomeViewDTO orderHomeViewDTO = modelMapper.map(order, OrderHomeViewDTO.class);

            orderHomeViewDTOList.add(orderHomeViewDTO);
        });

        return orderHomeViewDTOList;
    }

    @Override
    public OrderHomeViewDTO findOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id: " + orderId));

        return modelMapper.map(order, OrderHomeViewDTO.class);

    }
}

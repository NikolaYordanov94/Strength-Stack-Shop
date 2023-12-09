package bg.softuni.strengthstackshop.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import bg.softuni.strengthstackshop.model.entity.Order;
import bg.softuni.strengthstackshop.model.entity.Product;
import bg.softuni.strengthstackshop.model.entity.User;
import bg.softuni.strengthstackshop.repository.OrderRepository;
import bg.softuni.strengthstackshop.repository.ProductRepository;
import bg.softuni.strengthstackshop.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    @Test
    public void testFindOrCreateActiveOrder_ExistingActive() {
        // Arrange
        User user = new User();
        Order activeOrder = new Order();
        activeOrder.setActive(true);
        user.setOrders(new ArrayList<>(Collections.singletonList(activeOrder)));

        // Act
        Order result = orderService.findOrCreateActiveOrder(user, new Product());

        // Assert
        assertEquals(activeOrder, result);
    }

    @Test
    public void testFindOrCreateActiveOrder_NoActiveCreateNew() {
        // Arrange
        User user = new User();
        user.setOrders(new ArrayList<>());
        when(orderRepository.save(any(Order.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Order result = orderService.findOrCreateActiveOrder(user, new Product());

        // Assert
        assertTrue(result.isActive());
        assertNotNull(result.getOrderDate());
        assertEquals(BigDecimal.valueOf(0.00), result.getTotalPrice());
        verify(orderRepository).save(any(Order.class));
    }
    @Test
    public void testDeactivateCurrentActiveOrder() {
        // Arrange
        User user = new User();
        Order activeOrder = new Order();
        activeOrder.setActive(true);
        user.setOrders(new ArrayList<>(Collections.singletonList(activeOrder)));
        // Assume that the save method does what it should
        doAnswer(invocation -> invocation.getArgument(0)).when(orderRepository).save(any(Order.class));

        // Act
        orderService.deactivateCurrentActiveOrder(user);

        // Assert
        assertFalse(activeOrder.isActive());
        verify(orderRepository).save(activeOrder);
    }

    @Test
    public void testSaveOrderProduct() {
        // Arrange
        Order order = new Order();
        Product product = new Product();

        // Act
        orderService.saveOrderProduct(order, product);

        // Assert
        verify(orderRepository).save(order);
        verify(productRepository).save(product);
    }

    @Test
    public void testRemoveProductFromOrder_ProductExists() {
        // Arrange
        Long productId = 1L;
        Order order = new Order();
        order.setActive(true);
        order.setTotalPrice(new BigDecimal("100"));
        Product product = new Product();
        product.setId(productId);
        product.setPrice(new BigDecimal("50"));
        order.setProducts(new ArrayList<>(Collections.singletonList(product)));

        Principal principal = mock(Principal.class);
        when(principal.getName()).thenReturn("username");
        when(orderRepository.findByUserUsername(principal.getName())).thenReturn(Collections.singletonList(order));

        // Act
        orderService.removeProductFromOrder(productId, principal);

        // Assert
        assertEquals(new BigDecimal("50"), order.getTotalPrice());
        assertFalse(order.getProducts().contains(product));
        verify(orderRepository).save(order);
    }

    @Test
    public void testFindActiveOrder_ActiveOrderExists() {
        // Arrange
        Principal principal = mock(Principal.class);
        when(principal.getName()).thenReturn("username");
        Order activeOrder = new Order();
        activeOrder.setActive(true);
        when(orderRepository.findByUserUsername(principal.getName())).thenReturn(Collections.singletonList(activeOrder));

        // Act
        Order result = orderService.findActiveOrder(principal);

        // Assert
        assertTrue(result.isActive());
        assertEquals(activeOrder, result);
    }

    @Test
    public void testFindActiveOrder_NoActiveOrderThrows() {
        // Arrange
        Principal principal = mock(Principal.class);
        when(principal.getName()).thenReturn("username");
        when(orderRepository.findByUserUsername(principal.getName())).thenReturn(Collections.emptyList());

        // Act & Assert
        assertThrows(EntityNotFoundException.class, () -> orderService.findActiveOrder(principal));
    }

    @Test
    public void testFindCurrentUserByUsername_UserFound() {
        // Arrange
        Principal principal = mock(Principal.class);
        when(principal.getName()).thenReturn("username");
        User user = new User();
        when(userRepository.findByUsername(principal.getName())).thenReturn(Optional.of(user));

        // Act
        User foundUser = orderService.findCurrentUserByUsername(principal);

        // Assert
        assertNotNull(foundUser);
        assertEquals(user, foundUser);
    }

    @Test
    public void testFindCurrentOrderById_OrderFound() {
        // Arrange
        Long orderId = 1L;
        Order order = new Order();
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

        // Act
        Order foundOrder = orderService.findCurrentOrderById(orderId);

        // Assert
        assertNotNull(foundOrder);
        assertEquals(order, foundOrder);
    }
}
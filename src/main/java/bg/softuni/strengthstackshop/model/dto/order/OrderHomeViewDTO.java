package bg.softuni.strengthstackshop.model.dto.order;

import bg.softuni.strengthstackshop.model.entity.Product;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class OrderHomeViewDTO {

    private Long id;

    private LocalDate orderDate;

    private BigDecimal totalPrice;

    private List<Product> products;

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

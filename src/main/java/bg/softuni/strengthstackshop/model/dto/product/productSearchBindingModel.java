package bg.softuni.strengthstackshop.model.dto.product;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class productSearchBindingModel {

    @Size(min = 10, max = 100, message = "The description length should be between 10 and 100 symbols")
    private String description;

    @Size(min = 3, max = 30, message = "The brand length should be between 3 and 30 symbols")
    private String brand;

    @NotNull(message = "Price cannot be null")
    @DecimalMin(value = "0.01", message = "The price must be higher or equal to 0.01")
    private BigDecimal maxPrice;

    @NotNull(message = "Price cannot be null")
    @DecimalMin(value = "0.01", message = "The price must be higher or equal to 0.01")
    private BigDecimal minPrice;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }
}

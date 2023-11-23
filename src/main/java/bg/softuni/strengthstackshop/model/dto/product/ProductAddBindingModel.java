package bg.softuni.strengthstackshop.model.dto.product;

import bg.softuni.strengthstackshop.model.enums.Category;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ProductAddBindingModel {

    @DecimalMin(value = "0.01", message = "The price must be higher or equal to 0.01")
    private BigDecimal price;

    @Size(min = 10, max = 100, message = "The description length should be between 10 and 100 symbols")
    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent(message = "The date could not be in the future")
    private LocalDate createdAt;

    @Size(min = 3, max = 30, message = "The brand length should be between 3 and 30 symbols")
    private String brand;

    @NotNull(message = "The picture url could not be empty")
    private String pictureUrl;

    @NotNull(message = "You must select a category")
    private Category category;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}

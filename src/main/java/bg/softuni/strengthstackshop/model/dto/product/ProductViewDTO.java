package bg.softuni.strengthstackshop.model.dto.product;

import bg.softuni.strengthstackshop.model.entity.Product;
import bg.softuni.strengthstackshop.model.enums.Category;
import java.math.BigDecimal;

public class ProductViewDTO {
    private Long id;

    private BigDecimal price;

    private String description;

    private String brand;

    private String pictureUrl;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void map(Product product) {
        if (product != null) {
            this.price = product.getPrice();
            this.description = product.getDescription();
            this.brand = product.getBrand();
            this.pictureUrl = product.getPictureUrl();
            this.category = product.getCategory();
        }
    }
}

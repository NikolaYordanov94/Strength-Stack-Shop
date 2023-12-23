package bg.softuni.strengthstackshop.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.PastOrPresent;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Entity
@Table(name = "comments")
public class Comment extends BaseEntity{

    @Column(nullable = false)
    @Length(min = 5, max = 50)
    private String description;

    @Column(nullable = false)
    @PastOrPresent
    private LocalDate commentDate;

    @ManyToOne
    private User user;

    @ManyToOne
    private Product product;

    public Comment() {
        //empty constructor
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(LocalDate commentDate) {
        this.commentDate = commentDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}

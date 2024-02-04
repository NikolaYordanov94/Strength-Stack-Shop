package bg.softuni.strengthstackshop.model.dto.comment;

import bg.softuni.strengthstackshop.model.entity.User;

import java.time.LocalDate;

public class CommentShowDTO {

    private String description;

    private User user;

    private LocalDate commentDate;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(LocalDate commentDate) {
        this.commentDate = commentDate;
    }
}

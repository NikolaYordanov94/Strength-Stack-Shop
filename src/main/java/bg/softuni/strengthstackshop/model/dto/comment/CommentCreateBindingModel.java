package bg.softuni.strengthstackshop.model.dto.comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CommentCreateBindingModel {

    @NotBlank(message = "Description cannot be empty")
    @Size(min = 10, max = 200, message = "Comment description should be between 10 and 200 characters")
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

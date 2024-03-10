package bg.softuni.strengthstackshop.model.dto.user;

import jakarta.validation.constraints.Size;

public class UserSearchBindingModel {

    private String username;

    @Size(min = 3, max = 30, message = "Username length must be between 3 and 30 characters")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

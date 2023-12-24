package bg.softuni.strengthstackshop.model.dto.user;

import bg.softuni.strengthstackshop.validation.email.UniqueEmail;
import bg.softuni.strengthstackshop.validation.phone_number.UniquePhoneNumber;
import bg.softuni.strengthstackshop.validation.username.UniqueUsername;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserRegisterBindingModel {

    @Size(min = 3, max = 30, message = "Username length must be between 3 and 30 characters")
    @UniqueUsername
    private String username;

    @Email
    @NotBlank(message = "Email cannot be empty")
    @UniqueEmail
    private String email;

    @Pattern(regexp = "^[+]?\\d{10,15}$", message = "Invalid phone number")
    @UniquePhoneNumber
    private String phoneNumber;

    @Size(min = 20, max = 100, message = "Address length must be between 20 and 100 characters")
    private String address;

    @Size(min = 6, max = 30, message = "Password length must be between 6 and 30 characters")
    private String password;

    private String confirmPassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}

package bg.softuni.strengthstackshop.validation.phoneNumber;

import bg.softuni.strengthstackshop.service.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniquePhoneNumberValidator implements ConstraintValidator<UniquePhoneNumber, String> {

    private final UserService userService;

    public UniquePhoneNumberValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {
        return !userService.existsByPhoneNumber(phoneNumber);
    }
}

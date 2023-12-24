package bg.softuni.strengthstackshop.validation.password;

import bg.softuni.strengthstackshop.model.dto.user.UserRegisterBindingModel;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatcherValidator implements ConstraintValidator<PasswordMatcher, UserRegisterBindingModel> {

    @Override
    public boolean isValid(UserRegisterBindingModel userRegisterBindingModel, ConstraintValidatorContext context) {
        return userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword());
    }
}

package bg.softuni.strengthstackshop.validation.password;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;

@Target(TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordMatcherValidator.class)
public @interface PasswordMatcher {
    String message() default "The passwords do not match";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}

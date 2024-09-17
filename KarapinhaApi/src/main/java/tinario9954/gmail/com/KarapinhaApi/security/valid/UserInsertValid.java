package tinario9954.gmail.com.KarapinhaApi.security.valid;

import jakarta.persistence.PostLoad;
import jakarta.validation.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UserInsertValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface UserInsertValid {
    String message() default "Validation error";

    Class<?>[] groups() default {};

    Class<? extends PostLoad>[] payload() default {};
}

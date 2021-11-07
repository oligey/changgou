package valid;

import javax.validation.*;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.regex.Pattern;

@Target({ElementType.METHOD,ElementType.FIELD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PageConstraint.PageValidator.class)
public @interface PageConstraint {

    //TODO

    String message();

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };


    class PageValidator implements ConstraintValidator<PageConstraint, Object> {

        @Override
        public boolean isValid(Object value, ConstraintValidatorContext context) {
            context.disableDefaultConstraintViolation();
            if (value == null){
                return true;
            }
            context.buildConstraintViolationWithTemplate("error msg").addConstraintViolation();

            return Pattern.matches("","");
        }

    }
}

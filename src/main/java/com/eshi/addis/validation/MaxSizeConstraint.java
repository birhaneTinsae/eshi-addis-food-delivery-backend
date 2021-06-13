package com.eshi.addis.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Constraint(validatedBy = MaxSizeConstraintValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface MaxSizeConstraint {
    String message() default "The candidates list cannot contain more than 5 .";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

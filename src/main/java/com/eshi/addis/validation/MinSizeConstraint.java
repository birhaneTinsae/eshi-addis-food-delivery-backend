package com.eshi.addis.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Constraint(validatedBy = MinSizeConstraintValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface MinSizeConstraint {
    String message() default "The input list should ne at least 1";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}


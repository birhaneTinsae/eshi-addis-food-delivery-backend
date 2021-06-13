package com.eshi.addis.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MaxSizeConstraintValidator implements ConstraintValidator<MaxSizeConstraint, long[]> {
    @Override
    public boolean isValid(long[] values, ConstraintValidatorContext context) {
        return values.length <= 11;
    }
}

package com.eshi.addis.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class MinSizeConstraintValidator implements ConstraintValidator<MinSizeConstraint, List<Object>> {
   @Override
   public void initialize(MinSizeConstraint constraint) {
      throw new UnsupportedOperationException();
   }

   public boolean isValid(List<Object> obj, ConstraintValidatorContext context) {
      return !obj.isEmpty();
   }
}

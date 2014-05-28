package com.aps.wicc.model;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidServiceGroupAlterationValidator implements ConstraintValidator<ValidServiceGroupAlteration, ServiceGroupAlteration> {
	
	@Override
	public void initialize(ValidServiceGroupAlteration constraintAnnotation) {
		
	}
	
	@Override
	public boolean isValid(ServiceGroupAlteration value, ConstraintValidatorContext context) {

		boolean startfinish = false;
		boolean finishstart = false;
		
		if (value.getDirection() == Direction.BOTH) {
			
			return true;
			
		}
		
		for (Alteration alteration : value.getAlterations()) {
			
			if (alteration.getAlterationType() == AlterationType.STARTFINISH) {
				
				if (!startfinish) {
				
					System.out.println("startfinish");
					context.disableDefaultConstraintViolation();
					context.buildConstraintViolationWithTemplate("{validation.startfinish}").addConstraintViolation();
					
				}
								
				startfinish = true;
				
			} else if (alteration.getAlterationType() == AlterationType.FINISHSTART) {
			
				if (!finishstart) {
				
					System.out.println("finishstart");
					context.disableDefaultConstraintViolation();
					context.buildConstraintViolationWithTemplate("{validation.finishstart}").addConstraintViolation();
					
				}
								
				finishstart = true;
			}
			
		}
		
		return false;
		
	}
		
}

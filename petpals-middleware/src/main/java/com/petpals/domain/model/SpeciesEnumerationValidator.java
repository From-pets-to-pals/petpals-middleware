package com.petpals.domain.model;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.EnumSet;
import java.util.Set;
import java.util.stream.Collectors;

public class SpeciesEnumerationValidator implements ConstraintValidator<SpeciesConstraint,String> {
	private Set<String> allowedValues;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void initialize(SpeciesConstraint constraintAnnotation) {
		Class<? extends Enum> enumSelected = constraintAnnotation.enumClass();
		allowedValues = (Set<String>) EnumSet.allOf(enumSelected).stream().map(e -> ((Enum<? extends Enum<?>>) e).name())
											  .collect(Collectors.toSet());
	}
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return value == null || allowedValues.contains(value);
	}
	
}

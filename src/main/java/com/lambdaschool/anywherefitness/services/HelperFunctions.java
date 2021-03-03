package com.lambdaschool.anywherefitness.services;

import com.lambdaschool.anywherefitness.models.ValidationError;

import java.util.List;

public interface HelperFunctions {
    List<ValidationError> getConstraintViolation(Throwable cause);
}
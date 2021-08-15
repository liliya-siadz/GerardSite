package com.gerard.site.validation.field;

@FunctionalInterface
public interface FieldValidator {
    boolean isValid(String field);
}

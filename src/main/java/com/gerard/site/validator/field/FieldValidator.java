package com.gerard.site.validator.field;

@FunctionalInterface
public interface FieldValidator<T> {
    boolean isValid(T field);
}

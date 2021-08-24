package com.gerard.site.validator;

@FunctionalInterface
public interface FieldValidator<T> {
    boolean isValid(T field);
}

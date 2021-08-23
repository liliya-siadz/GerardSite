package com.gerard.site.validation.field;

@FunctionalInterface
public interface FieldValidator<T> {
    boolean isValid(T field);
}

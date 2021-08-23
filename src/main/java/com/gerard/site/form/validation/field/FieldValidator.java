package com.gerard.site.form.validation.field;

@FunctionalInterface
public interface FieldValidator<T> {
    boolean isValid(T field);
}

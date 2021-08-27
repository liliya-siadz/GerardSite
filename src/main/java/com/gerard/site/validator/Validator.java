package com.gerard.site.validator;

@FunctionalInterface
public interface Validator<T> {
    boolean isValid(T field);
}

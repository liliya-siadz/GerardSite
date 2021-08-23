package com.gerard.site.form.validation.field.impl;

import com.gerard.site.form.validation.field.FieldValidator;
//        ЧИСЛОВЫЕ ЗНАЧЕНИЯ
//        0 длина поля: кол-во знаков
//        1 максимальное значение
//        2 минимальное значение
//        3 может ли быть  empty/blank/null


public class PhoneValidator implements FieldValidator<Integer> {
    @Override
    public boolean isValid(Integer field) {
        int minValue;
        int maxValue;
        String regex;
        //belarus code phones:
        //29 44 33 25
        return true;
    }
}

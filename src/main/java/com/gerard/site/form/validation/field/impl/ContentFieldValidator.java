package com.gerard.site.form.validation.field.impl;

import com.gerard.site.form.validation.field.FieldValidator;

public class ContentFieldValidator implements FieldValidator<String> {


    @Override
    public boolean isValid(String content) {
        int maxLength;
        int minLength;
        String regex;
        if(content==null || content.isBlank()){
            return false;
        }
        return true;
//        СТРОКОВЫЕ ЗНАЧЕНИЯ
//        1 макс длина
//        2 мин длина
//        3 regex
//
//        ЧИСЛОВЫЕ ЗНАЧЕНИЯ
//        0 длина поля
//        1 максимальное значение
//        2 минимальное значение
//        3 может ли быть  empty/blank/null
    }
}

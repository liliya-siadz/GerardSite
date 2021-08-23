package com.gerard.site.validator.field;

//        ЧИСЛОВЫЕ ЗНАЧЕНИЯ
//        0 длина поля: кол-во знаков
//        1 максимальное значение
//        2 минимальное значение
//        3 может ли быть  empty/blank/null


public class PhoneFieldValidator implements FieldValidator<String> {


    private static PhoneFieldValidator instance;

    private PhoneFieldValidator() {
    }

     static PhoneFieldValidator getInstance() {
        if (instance == null) {
            instance = new PhoneFieldValidator();
        }
        return instance;
    }

    @Override
    public boolean isValid(String field) {
        int minValue;
        int maxValue;
        String regex;
        //belarus code phones:
        //29 44 33 25
        return true;
    }
}

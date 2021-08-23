package com.gerard.site.validator.field;

public class ContentFieldValidator implements FieldValidator<String> {

    private static ContentFieldValidator instance;

    private ContentFieldValidator() {
    }

     static ContentFieldValidator getInstance() {
        if (instance == null) {
            instance = new ContentFieldValidator();
        }
        return instance;
    }


    @Override
    public boolean isValid(String content) {
        int maxLength;
        int minLength;
        String regex;
        if(content==null || content.isBlank()){
            return true;
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

package com.gerard.site.form;

import com.gerard.site.validation.form.FormValidator;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Map;

public abstract class AbstractForm {
    private final List<String> inputsName;
    protected FormValidator validator;


    public AbstractForm(List<String> inputsName){
        this.inputsName = inputsName;
    }

    protected final void setValidator(FormValidator formValidator){
        this.validator = formValidator;
    }

    public abstract Map<String, Boolean> getValidationMap();

    protected abstract void readInputs(HttpServletRequest request);

}

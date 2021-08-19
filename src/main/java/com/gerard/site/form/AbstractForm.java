package com.gerard.site.form;

import com.gerard.site.validation.form.FormValidator;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Map;

//todo ?: generics is ok
public abstract class AbstractForm<T extends FormValidator> {
    protected final HttpServletRequest request;
    protected final FormValidator validator;
    private final List<String> inputsName;

    AbstractForm(HttpServletRequest request, List<String> inputsName, T validator){
        this.request = request;
        this.inputsName = inputsName;
        this.validator = validator;
        readInputs(request);
    }

    public Map<String, Boolean> getValidationMap(){
        return validator.validateForm(this);
    }

    protected abstract void readInputs(HttpServletRequest request);

}

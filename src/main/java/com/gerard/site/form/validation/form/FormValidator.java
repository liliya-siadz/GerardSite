package com.gerard.site.form.validation.form;

import com.gerard.site.form.AbstractForm;

import java.util.Map;

public interface FormValidator<T extends AbstractForm> {

    Map<String, Boolean> validateForm(T abstractForm);

}

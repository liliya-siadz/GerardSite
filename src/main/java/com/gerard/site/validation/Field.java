package com.gerard.site.validation;

import com.gerard.site.validation.impl.LoginValidator;
import com.gerard.site.validation.impl.PasswordValidator;

import java.util.HashMap;
import java.util.Map;

public class Field {

    //PHASE: design
    private static Field instance;
    public static Map<String, FieldValidator> validators = new HashMap<>();

    static {
        validators.put("login", new LoginValidator());
        validators.put("password", new PasswordValidator());
    }

    public static void init(){
        if(instance != null){
            instance = new Field();
        }
    }
}


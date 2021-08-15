package com.gerard.site.controller;


import com.gerard.site.controller.action.Action;
import com.gerard.site.controller.action.impl.*;
import com.gerard.site.validation.field.FieldsValidators;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

import static com.gerard.site.controller.Command.*;

public enum ActionFactory {
    INSTANCE;

    private static final Logger LOGGER = LogManager.getLogger(ActionFactory.class);
    private final Map<Command, Action> actions = new HashMap<>();
    private final Command defaultCommand = SHOW_ERROR_404;
    private final Action defaultAction = TransferToError404PageAction.INSTANCE;

    ActionFactory(){
        actions.put(LOGIN, LoginAction.INSTANCE);
        actions.put(LOGOUT, LogoutAction.INSTANCE);
        actions.put(SWITCH_LOCALE_TO_EN, SwitchLocaleToEnAction.INSTANCE);
        actions.put(SWITCH_LOCALE_TO_BE, SwitchLocaleToBeAction.INSTANCE);
        actions.put(SWITCH_LOCALE_TO_RU, SwitchLocaleToRuAction.INSTANCE);
        actions.put(SHOW_ERROR_404, TransferToError404PageAction.INSTANCE);
        actions.put(GET_ALL_DOGS, GetAllDogsAction.INSTANCE);
    }

    public Action getAction(String command){
        Command targetCommand;
        try{
            targetCommand = Command.valueOf(command);
        }catch (NullPointerException | IllegalArgumentException exception){
            LOGGER.warn(command + "command not found");
            targetCommand = defaultCommand;
        }
        Action targetAction = actions.get(targetCommand);
        return targetAction==null?defaultAction:targetAction;
    }
}

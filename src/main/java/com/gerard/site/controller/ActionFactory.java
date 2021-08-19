package com.gerard.site.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

import static com.gerard.site.controller.ActionFactory.Command.*;

//todo: doc
enum ActionFactory {
    INSTANCE;
    enum Command {
        LOGIN,
        LOGOUT,
        ERROR,
        ERROR_404,
        CHANGE_LOCALE_TO_BE,
        CHANGE_LOCALE_TO_EN,
        CHANGE_LOCALE_TO_RU,
    }

    private static final Logger LOGGER = LogManager.getLogger(ActionFactory.class);
    private final Map<Command, Action> actions = new HashMap<>();
    private final Command defaultCommand = ERROR_404;
    private final Action defaultAction = Error404PageAction.INSTANCE;

   ActionFactory(){
        actions.put(LOGIN, SignInAction.INSTANCE);
        actions.put(LOGOUT, SignOutAction.INSTANCE);
        actions.put(CHANGE_LOCALE_TO_EN, SetLocaleToEnAction.INSTANCE);
        actions.put(CHANGE_LOCALE_TO_BE, SetLocaleToBeAction.INSTANCE);
        actions.put(CHANGE_LOCALE_TO_RU, SetLocaleToRuAction.INSTANCE);
        actions.put(ERROR, ErrorPageAction.INSTANCE);
        actions.put(ERROR_404, Error404PageAction.INSTANCE);
    }

    //todo: doc
     Action getAction(String command){
        Command targetCommand;
        try{
            targetCommand = Command.valueOf(command);
        }catch (NullPointerException | IllegalArgumentException exception){
            LOGGER.warn(command + " command not found");
            targetCommand = defaultCommand;
        }
        Action targetAction = actions.get(targetCommand);
        return targetAction==null?defaultAction:targetAction;
    }
}

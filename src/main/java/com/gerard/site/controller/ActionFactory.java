package com.gerard.site.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

import static com.gerard.site.controller.ActionFactory.Command.*;

/**
 * Factory for providing objects that implements {@code interface Action}
 * {@link Action} .
 * Works as command-key action-value map, singleton object, realizes
 * pattern factory .
 *
 * @author Liliya Siadzelnikava
 * @version 1.0
 */
enum ActionFactory {
    INSTANCE;

    /**
     * Presents possible app actions identifiers, works as key in actions map
     * {@link ActionFactory#actions} .
     */
    enum Command {
        LOGIN,
        LOGOUT,
        ERROR,
        ERROR_404,
        SET_LOCALE_TO_BE,
        SET_LOCALE_TO_EN,
        SET_LOCALE_TO_RU,
    }

    private static final Logger LOGGER = LogManager.getLogger(ActionFactory.class);
    /**
     * Stores actions mapped to their commands .
     */
    private final Map<Command, Action> actions = new HashMap<>();

    /**
     * Used as default command {@code instanceof enum Command} {@link Command} .
     */
    private final Command defaultCommand = ERROR_404;

    /**
     * Used as default action attached to default command
     * {@link ActionFactory#defaultCommand},
     * implements functional {@code interface Action} {@link Action} .
     */
    private final Action defaultAction = Error404PageAction.INSTANCE;

    ActionFactory() {
        actions.put(LOGIN, SignInAction.INSTANCE);
        actions.put(LOGOUT, SignOutAction.INSTANCE);
        actions.put(SET_LOCALE_TO_EN, SetLocaleToEnAction.INSTANCE);
        actions.put(SET_LOCALE_TO_BE, SetLocaleToBeAction.INSTANCE);
        actions.put(SET_LOCALE_TO_RU, SetLocaleToRuAction.INSTANCE);
        actions.put(ERROR, ErrorPageAction.INSTANCE);
        actions.put(ERROR_404, Error404PageAction.INSTANCE);
    }

    /**
     * Defines action by incoming command, if command is unknown,
     * sets default command {@link ActionFactory#defaultCommand}
     * and default action {@link ActionFactory#defaultAction} .
     *
     * @param command incoming command name,
     *                presents key from actions map {@link ActionFactory#actions},
     *                if command is unknown default command will be used
     * @return action specified to incoming command,
     * presents value from actions map {@link ActionFactory#actions},
     * for default command provide default action
     */
    Action getAction(String command) {
        Command targetCommand;
        try {
            targetCommand = Command.valueOf(command);
        } catch (NullPointerException | IllegalArgumentException exception) {
            LOGGER.warn("'" + command + "' command not found");
            targetCommand = defaultCommand;
        }
        Action targetAction = actions.get(targetCommand);
        return targetAction == null ? defaultAction : targetAction;
    }
}

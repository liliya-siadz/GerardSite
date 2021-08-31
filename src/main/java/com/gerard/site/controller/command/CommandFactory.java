package com.gerard.site.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

import static com.gerard.site.controller.command.CommandIdentifier.ACCEPT_REQUEST;
import static com.gerard.site.controller.command.CommandIdentifier.CHOSE_PUPPY;
import static com.gerard.site.controller.command.CommandIdentifier.DELETE_PHOTO;
import static com.gerard.site.controller.command.CommandIdentifier.ERROR;
import static com.gerard.site.controller.command.CommandIdentifier.ERROR_404;
import static com.gerard.site.controller.command.CommandIdentifier.GO_TO_ADMIN_DOGS_PAGE;
import static com.gerard.site.controller.command.CommandIdentifier.GO_TO_ADMIN_PHOTOS_PAGE;
import static com.gerard.site.controller.command.CommandIdentifier.GO_TO_ADMIN_REQUESTS_PAGE;
import static com.gerard.site.controller.command.CommandIdentifier.GO_TO_DOGS_PAGE;
import static com.gerard.site.controller.command.CommandIdentifier.GO_TO_LOGIN_PAGE;
import static com.gerard.site.controller.command.CommandIdentifier.GO_TO_MAKE_REQUEST_PAGE;
import static com.gerard.site.controller.command.CommandIdentifier.GO_TO_PHOTOS_PAGE;
import static com.gerard.site.controller.command.CommandIdentifier.GO_TO_PUPPIES_PAGE;
import static com.gerard.site.controller.command.CommandIdentifier.INVALIDATE_SESSION;
import static com.gerard.site.controller.command.CommandIdentifier.LOGIN;
import static com.gerard.site.controller.command.CommandIdentifier.LOGOUT;
import static com.gerard.site.controller.command.CommandIdentifier.MAKE_REQUEST;
import static com.gerard.site.controller.command.CommandIdentifier.REJECT_REQUEST;
import static com.gerard.site.controller.command.CommandIdentifier.SET_LOCALE_TO_BE;
import static com.gerard.site.controller.command.CommandIdentifier.SET_LOCALE_TO_EN;
import static com.gerard.site.controller.command.CommandIdentifier.SET_LOCALE_TO_RU;

/**
 * Factory for providing objects that implements {@code interface Command}
 * {@link Command} .
 * Works as command-key command-value map, realizes singleton factory .
 *
 * @author Liliya Siadzelnikava
 * @version 1.0
 */
public enum CommandFactory {
    INSTANCE;
    private static final Logger LOGGER = LogManager.getLogger(CommandFactory.class);

    /**
     * Stores commands identifiers mapped to their commands .
     */
    private final Map<CommandIdentifier, Command> commands = new HashMap<>();

    /**
     * Used as default command identifier
     * {@code instanceof enum CommandIdentifier} {@link CommandIdentifier} .
     */
    private final CommandIdentifier defaultCommandIdentifier = ERROR_404;

    CommandFactory() {
        commands.put(LOGIN, LoginCommand.INSTANCE);
        commands.put(LOGOUT, LogoutCommand.INSTANCE);
        commands.put(SET_LOCALE_TO_EN, SetLocaleToEnCommand.INSTANCE);
        commands.put(SET_LOCALE_TO_BE, SetLocaleToBeCommand.INSTANCE);
        commands.put(SET_LOCALE_TO_RU, SetLocaleToRuCommand.INSTANCE);
        commands.put(ERROR, ErrorCommand.INSTANCE);
        commands.put(ERROR_404, Error404Command.INSTANCE);
        commands.put(GO_TO_MAKE_REQUEST_PAGE, GoToMakeRequestPageCommand.INSTANCE);
        commands.put(GO_TO_DOGS_PAGE, GoToDogsPageCommand.INSTANCE);
        commands.put(GO_TO_PUPPIES_PAGE, GoToPuppiesPageCommand.INSTANCE);
        commands.put(GO_TO_PHOTOS_PAGE, GoToPhotosPageCommand.INSTANCE);
        commands.put(GO_TO_LOGIN_PAGE, GoToLoginPageCommand.INSTANCE);
        commands.put(CHOSE_PUPPY, ChosePuppyCommand.INSTANCE);
        commands.put(MAKE_REQUEST, MakeRequestCommand.INSTANCE);
        commands.put(GO_TO_ADMIN_PHOTOS_PAGE, GoToAdminPhotosPageCommand.INSTANCE);
        commands.put(GO_TO_ADMIN_DOGS_PAGE, GoToAdminDogsPageCommand.INSTANCE);
        commands.put(GO_TO_ADMIN_REQUESTS_PAGE, GoToAdminRequestsPageCommand.INSTANCE);
        commands.put(ACCEPT_REQUEST, AcceptRequestCommand.INSTANCE);
        commands.put(REJECT_REQUEST, RejectRequestCommand.INSTANCE);
        commands.put(DELETE_PHOTO, DeletePhotoCommand.INSTANCE);
        commands.put(INVALIDATE_SESSION, InvalidateSessionCommand.INSTANCE);
    }

    /**
     * Defines command by it's identifier,
     * identifier is unknown, sets default command's identifier
     * {@link CommandFactory#defaultCommandIdentifier} .
     *
     * @param  command command's identifier,
     *         presents key from commands map
     *         {@link CommandFactory#commands},
     *         if identifier is unknown
     *         will be used default command's identifier
     * @return command specified to command's identifier
     *         {@link Command},
     *         presents value from commands map
     *         {@link CommandFactory#commands},
     */
    public Command getCommand(String command) {
        CommandIdentifier targetCommandIdentifier;
        if (command != null) {
            try {
                targetCommandIdentifier = CommandIdentifier.valueOf(command);
            } catch (IllegalArgumentException exception) {
                LOGGER.error("'" + command + "' command not found");
                targetCommandIdentifier = defaultCommandIdentifier;
            }
        } else {
            targetCommandIdentifier = defaultCommandIdentifier;
        }
        Command targetCommand = commands.get(targetCommandIdentifier);
        return targetCommand;
    }
}

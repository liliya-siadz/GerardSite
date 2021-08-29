package com.gerard.site.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

import static com.gerard.site.controller.command.CommandIdentifier.*;

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

        commands.put(CHOSE_PUPPY, ChosePuppyCommand.INSTANCE);
        commands.put(MAKE_REQUEST, MakeRequestCommand.INSTANCE);

        commands.put(GO_TO_ADMIN_PHOTOS_PAGE, GoToAdminPhotosPageCommand.INSTANCE);
        commands.put(GO_TO_ADMIN_DOGS_PAGE, GoToAdminDogsPageCommand.INSTANCE);
        commands.put(GO_TO_ADMIN_REQUESTS_PAGE, GoToAdminRequestsPageCommand.INSTANCE);

        commands.put(ACCEPT_REQUEST, AcceptRequestCommand.INSTANCE);
        commands.put(REJECT_REQUEST, RejectRequestCommand.INSTANCE);

        commands.put(INVALIDATE_SESSION, InvalidateSessionCommand.INSTANCE);
    }

    /**
     * Defines command by it's identifier,
     * identifier is unknown, sets default command's identifier
     * {@link CommandFactory#defaultCommandIdentifier} .
     *
     * @param command command's identifier,
     *                presents key from commands map
     *                {@link CommandFactory#commands},
     *                if identifier is unknown
     *                will be used default command's identifier
     * @return command specified to command's identifier
     *         {@link Command},
     *         presents value from commands map
     * {@link CommandFactory#commands},
     */
    public Command getCommand(String command) {
        CommandIdentifier targetCommandIdentifier;
        try {
            targetCommandIdentifier = CommandIdentifier.valueOf(command);
        } catch (IllegalArgumentException exception) {
            LOGGER.error("'" + command + "' command not found");
            targetCommandIdentifier = defaultCommandIdentifier;
        }
        Command targetCommand = commands.get(targetCommandIdentifier);
        return targetCommand;
    }
}

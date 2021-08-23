package com.gerard.site.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;


import static com.gerard.site.controller.command.CommandIdentifier.*;

/**
 * Factory for providing objects that implements {@code interface Command}
 * {@link Command} .
 * Works as command-key command-value map, singleton object, realizes
 * pattern factory .
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

    /**
     * Used as default command attached to default command identifier
     * {@link CommandFactory#defaultCommandIdentifier},
     * implements functional {@code interface Command} {@link Command} .
     */
    private final Command defaultCommand = Error404PageCommand.INSTANCE;

    CommandFactory() {
        commands.put(LOGIN, LoginCommand.INSTANCE);
        commands.put(LOGOUT, LogoutCommand.INSTANCE);
        commands.put(SET_LOCALE_TO_EN, SetLocaleToEnCommand.INSTANCE);
        commands.put(SET_LOCALE_TO_BE, SetLocaleToBeCommand.INSTANCE);
        commands.put(SET_LOCALE_TO_RU, SetLocaleToRuCommand.INSTANCE);
        commands.put(ERROR, ErrorPageCommand.INSTANCE);
        commands.put(ERROR_404, Error404PageCommand.INSTANCE);

        commands.put(GO_TO_MAKE_REQUEST_PAGE, GoToMakeRequestPageCommand.INSTANCE);
        commands.put(GO_TO_DOGS_PAGE, GoToDogsPageCommand.INSTANCE);
        commands.put(GO_TO_PUPPIES_PAGE, GoToPuppiesPageCommand.INSTANCE);
        commands.put(GO_TO_PHOTOS_PAGE, GoToPhotosPageCommand.INSTANCE);
        commands.put(GO_TO_ADMIN_PHOTOS_PAGE, GoToAdminPhotosPageCommand.INSTANCE);

        commands.put(GO_TO_ADMIN_DOGS_PAGE, GoToAdminDogsPageCommand.INSTANCE);
        commands.put(GO_TO_ADMIN_REQUESTS_PAGE, GoToAdminRequestsPageCommand.INSTANCE);
        commands.put(ADD_DOG, AddPhotoCommand.INSTANCE);
        commands.put(DELETE_PHOTO, DeletePhotoCommand.INSTANCE);



        commands.put(DISPLAY_CHOSEN_PUPPY, DisplayChosenPuppyCommand.INSTANCE);
        commands.put(MAKE_REQUEST, MakeRequestCommand.INSTANCE);
        commands.put(INVALIDATE_SESSION, InvalidateSessionCommand.INSTANCE);
    }

    /**
     * Defines command by incoming it's identifier,
     * if command's identifier is unknown,
     * sets default command's identifier
     * {@link CommandFactory#defaultCommandIdentifier}
     * and default command {@link CommandFactory#defaultCommand} .
     *
     * @param command incoming command's identifier,
     *                presents key from commands map
     *                {@link CommandFactory#commands},
     *                if command's identifier is unknown
     *                default command's identifier will be used
     * @return command specified to command's identifier,
     *         presents value from commands map
     *         {@link CommandFactory#commands},
     *          for default command's identifier provides default command
     */
    public Command getCommand(String command) {
        CommandIdentifier targetCommandIdentifier;
        try {
            targetCommandIdentifier = CommandIdentifier.valueOf(command);
        } catch (NullPointerException | IllegalArgumentException exception) {
            LOGGER.warn("'" + command + "' command not found");
            targetCommandIdentifier = defaultCommandIdentifier;
        }
        Command targetCommand = commands.get(targetCommandIdentifier);
        return targetCommand == null ? defaultCommand : targetCommand;
    }
}

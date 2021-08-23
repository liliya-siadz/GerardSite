package com.gerard.site.controller.command;

/**
 * Presents possible app commands identifiers,
 * works as key in commands map in CommandFactory class.
 *
 * @author Liliya Siadzelnikava
 * @version 1.0
 * @see CommandFactory
 */
public enum CommandIdentifier {
    ERROR,
    ERROR_404,
    SET_LOCALE_TO_BE,
    SET_LOCALE_TO_EN,
    SET_LOCALE_TO_RU,

    GO_TO_PHOTOS_PAGE,
    GO_TO_PUPPIES_PAGE,
    GO_TO_DOGS_PAGE,
    GO_TO_MAKE_REQUEST_PAGE,

    GO_TO_ADMIN_DOGS_PAGE,
    GO_TO_ADMIN_REQUESTS_PAGE,

    MAKE_REQUEST,
    DISPLAY_CHOSEN_PUPPY,
    LOGIN,

    LOGOUT,
    INVALIDATE_SESSION,

    RENEW_PASSWORD,
    PROCESS_REQUEST,
    ADD_DOG,
    EDIT_DOG,
    DELETE_DOG;
}

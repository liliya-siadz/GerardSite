package com.gerard.site.controller.command;

/**
 * Presents possible app commands identifiers,
 * works as key in commands map in CommandFactory {@link CommandFactory}.
 *
 * @author Liliya Siadzelnikava
 * @version 1.0
 * @see CommandFactory
 */
public enum CommandIdentifier {
    LOGIN,
    LOGOUT,
    SET_LOCALE_TO_BE,
    SET_LOCALE_TO_EN,
    SET_LOCALE_TO_RU,

    ERROR,
    ERROR_404,

    GO_TO_MAKE_REQUEST_PAGE,
    GO_TO_DOGS_PAGE,
    GO_TO_PHOTOS_PAGE,
    GO_TO_PUPPIES_PAGE,

    CHOSE_PUPPY,
    MAKE_REQUEST,

    GO_TO_ADMIN_DOGS_PAGE,
    GO_TO_ADMIN_REQUESTS_PAGE,
    GO_TO_ADMIN_PHOTOS_PAGE,

    UPLOAD_DOG_PHOTO,
    DELETE_PHOTO,
    PROCESS_REQUEST,

    INVALIDATE_SESSION,
    RENEW_PASSWORD,
}

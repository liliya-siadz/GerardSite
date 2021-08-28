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
    LOGIN(false),
    LOGOUT(true),
    SET_LOCALE_TO_BE(false),
    SET_LOCALE_TO_EN(false),
    SET_LOCALE_TO_RU(false),

    ERROR(false),
    ERROR_404(false),

    GO_TO_MAKE_REQUEST_PAGE(false),
    GO_TO_DOGS_PAGE(false),
    GO_TO_PHOTOS_PAGE(false),
    GO_TO_PUPPIES_PAGE(false),

    CHOSE_PUPPY(false),
    MAKE_REQUEST(false),

    GO_TO_ADMIN_DOGS_PAGE(true),
    GO_TO_ADMIN_REQUESTS_PAGE(true),
    GO_TO_ADMIN_PHOTOS_PAGE(true),

    UPLOAD_DOG_PHOTO(true),
    DELETE_PHOTO(true),
    PROCESS_REQUEST(true),

    INVALIDATE_SESSION(false),
    RENEW_PASSWORD(false);

    private final boolean isAuthAccessedOnly;

    CommandIdentifier(boolean isAuthAccessedOnly) {
        this.isAuthAccessedOnly = isAuthAccessedOnly;
    }

    public boolean isAuthAccessedOnly() {
        return isAuthAccessedOnly;
    }
}

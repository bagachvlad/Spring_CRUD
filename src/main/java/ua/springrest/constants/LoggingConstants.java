package ua.springrest.constants;

public enum LoggingConstants {

    LOG_DEBUG_EMPTY_PATTERN("Method was called."),
    LOG_DEBUG_ONE_ARG_PATTERN("Method was called with argument: '{}'."),
    LOG_DEBUG_TWO_ARG_PATTERN("Method was called with argument(s): User: '{}', id: '{}'."),

    GENERAL_USER_NOT_FOUND_PATTERN("User with %s: '%s' not found!"),

    VALIDATION_FAILED_PATTERN("Validation stage is failed. Message: '%s'"),
    SAVE_FAILED_PATTERN("User save process is failed. Exception: '%s'"),
    REGISTRATION_SUCCESS_PATTERN("User with username '%s' was successfully registered!");

    private final String message;

    LoggingConstants(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

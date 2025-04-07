package src.domain;

/**
 * Exception class for Plan15 application.
 * This class represents exceptions specific to the Plan15 domain logic.
 */
public class Plan15Exception extends Exception {

    /**
     * Constant representing the message when the credits value is unknown.
     */
    public static final String CREDITS_UNKNOWN = "Credits value is unknown.";

    /**
     * Constant representing the message when the credit value is invalid.
     */
    public static final String CREDITS_ERROR = "Invalid credit value.";

    /**
     * Constant representing the message when in-person hours are unknown.
     */
    public static final String IN_PERSON_UNKNOWN = "In-person hours are unknown.";

    /**
     * Constant representing the message when in-person hours are invalid.
     */
    public static final String IN_PERSON_ERROR = "Invalid in-person hours.";

    /**
     * Constant representing an impossible state or operation.
     */
    public static final String IMPOSSIBLE = "IMPOSSIBLE";

    /**
     * Constructs a new Plan15Exception with the specified detail message.
     *
     * @param message The detail message. The detail message is saved for later retrieval by the getMessage() method.
     */
    public Plan15Exception(String message) {
        super(message);
    }
}
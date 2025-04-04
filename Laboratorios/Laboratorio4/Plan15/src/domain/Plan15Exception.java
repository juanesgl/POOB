package src.domain;

/**
 * 
 * 
 */

public class Plan15Exception extends Exception {
    public static final String CREDITS_UNKNOWN = "Credits value is unknown.";

    public static final String CREDITS_ERROR = "Invalid credit value.";
    
    public static final String IN_PERSON_UNKNOWN = "In-person hours are unknown.";

    public static final String IN_PERSON_ERROR = "Invalid in-person hours.";
    
    public static final String IMPOSSIBLE = "IMPOSSIBLE"; 
    
    public Plan15Exception(String message) {
        super(message);
    }
}

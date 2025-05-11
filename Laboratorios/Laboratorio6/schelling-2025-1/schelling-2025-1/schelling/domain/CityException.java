package domain;

public class CityException extends Exception {
    public static final String INVALID_FILE = "Archivo inválido o corrupto";
    public static final String PERMISSION_DENIED = "Permisos insuficientes";
    public static final String FORMAT_ERROR = "Error de formato en línea ";
    public static final String DATA_CORRUPTION = "Datos inconsistentes";
    public static final String FILE_NOT_FOUND = "FILE_NOT_FOUND";
    public static final String INVALID_FORMAT = "INVALID_FORMAT";
    public static final String IO_ERROR = "IO_ERROR";
    public static final String DATA_CORRUPTED = "DATA_CORRUPTED";
    public static final String ERROR_AT_LOADING = "ERROR_AT_LOADING";
   
    
    private final String errorCode;
    private final String details;  

    public CityException(String message) {
        this(message, "UNKNOWN", "");
    }

    public CityException(String message, String errorCode) {
        this(message, errorCode, "");
    }


    public CityException(String message, String errorCode, String details) {
        super(formatMessage(message, details));
        this.errorCode = errorCode;
        this.details = details;
    }


    public CityException(String message, Throwable cause) {
        this(message, resolveErrorCode(cause), "", cause);
    }

  
    public CityException(String message, String errorCode, Throwable cause) {
        this(message, errorCode, "", cause);
    }

    public CityException(String message, String errorCode, String details, Throwable cause) {
        super(formatMessage(message, details), cause);
        this.errorCode = errorCode;
        this.details = details;
    }

    private static String formatMessage(String message, String details) {
        return details.isEmpty() ? message : message + " | Detalles: " + details;
    }

    private static String resolveErrorCode(Throwable cause) {
        if (cause instanceof java.io.InvalidClassException) {
            return "VERSION_MISMATCH";
        } else if (cause instanceof java.io.StreamCorruptedException) {
            return DATA_CORRUPTED;
        } else if (cause instanceof java.io.IOException) {
            return IO_ERROR;
        }
        return "UNKNOWN";
    }


    public String getErrorCode() {
        return errorCode;
    }

    public String getDetails() {
        return details;
    }

    public String getFullError() {
        return String.format("[%s] %s%s",
            errorCode,
            getMessage(),
            getCause() != null ? " (Causa: " + getCause().getMessage() + ")" : "");
    }
}
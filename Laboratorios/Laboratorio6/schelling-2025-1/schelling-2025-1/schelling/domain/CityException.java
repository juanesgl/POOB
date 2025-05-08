package domain;

public class CityException extends Exception {

    public static final String OPEN_IN_CONSTRUCTION = "Opción open en construcción. Archivo ";
    public static final String SAVE_IN_CONSTRUCTION = "Opción save en construcción. Archivo ";
    public static final String IMPORT_IN_CONSTRUCTION = "Opción import en construcción. Archivo ";
    public static final String EXPORT_IN_CONSTRUCTION = "Opción export en construcción. Archivo ";

    public CityException(String message) {
        super(message);
    }

    public CityException(String message, String filename) {
        super(message + filename);
    }
}
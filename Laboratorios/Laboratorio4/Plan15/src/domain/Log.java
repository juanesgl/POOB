package src.domain;

import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;
import java.util.logging.Level;

/**
 * Class for logging exceptions and errors in the Plan15 application.
 * It uses the Java logging system to write logs to a file.
 */
public class Log {

    /**
     * Name of the logger and the log file.
     */
    public static String nombre = "Plan15";

    /**
     * Logs an exception to the log file.
     *
     * @param e The exception to be logged.
     */
    public static void record(Exception e) {
        try {
            Logger logger = Logger.getLogger(nombre);
            logger.setUseParentHandlers(false);
            FileHandler file = new FileHandler(nombre + ".log", true);
            file.setFormatter(new SimpleFormatter());
            logger.addHandler(file);
            logger.log(Level.SEVERE, e.toString(), e);
            file.close();
        } catch (Exception oe) {
            oe.printStackTrace();
            System.exit(0);
        }
    }
}
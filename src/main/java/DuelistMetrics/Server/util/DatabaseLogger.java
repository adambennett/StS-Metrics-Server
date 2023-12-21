package DuelistMetrics.Server.util;

import DuelistMetrics.Server.controllers.ExceptionController;

public class DatabaseLogger {

    public static void log(Exception ex) {
        log(null, ex);
    }

    public static void log(String message) {
        log(message, null);
    }

    public static void log(String message, Exception ex) {
        ExceptionController.getService().logServerException(message, ex);
    }

}

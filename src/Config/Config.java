package Config;

/**
 * Created by jesperbruun on 09/11/14.
 *
 * Pre-defined config values for system-wide usage
 *
 */
public abstract class Config {

    private static String host = "localhost";
    private static String port = "54804";
    private static String dbUsername = "root";
    private static String dbPassword = "root";
    private static String dbCalendar = "cbscalendar";

    public static String getHost() {
        return host;
    }

    public static String getPort() {
        return port;
    }

    public static String getDbUsername() {
        return dbUsername;
    }

    public static String getDbPassword() {
        return dbPassword;
    }

    public static String getDbCalendar() {
        return dbCalendar;
    }

    public static void setHost(String host) {
        Config.host = host;
    }

    public static void setPort(String port) {
        Config.port = port;
    }

    public static void setDbUsername(String dbUsername) {
        Config.dbUsername = dbUsername;
    }

    public static void setDbPassword(String dbPassword) {
        Config.dbPassword = dbPassword;
    }

    public static void setDbCalendar(String dbCalendar) {
        Config.dbCalendar = dbCalendar;
    }


}

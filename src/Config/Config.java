package Config;

/**
 * Created by jesperbruun on 09/11/14.
 *
 * Pre-defined config values for system-wide usage
 *
 */
public class Config {

    private static String dbHost = "localhost";
    private static String dbPort = "3306";
    private static String dbUsername = "root";
    private static String dbPassword = "ukamm19";
    private static final String dbCalendar = "cbscalendar";

    private static String serverAddress = "127.0.0.1";
    private static int serverPort = 52400;

    private static String resPath = "res/";

    public static String getHost() {
        return dbHost;
    }
    public static String getPort() {
        return dbPort;
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
    public static String getResPath() {
        return resPath;
    }
    public static String getServerAddress() {return serverAddress;}
    public static void setServerAddress(String serverAddress) {Config.serverAddress = serverAddress;}
    public static int getServerPort() {return serverPort;}
}

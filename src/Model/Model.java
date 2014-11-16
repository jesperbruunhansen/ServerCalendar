package Model;

import Config.Config;
import com.ibatis.common.jdbc.ScriptRunner;
import java.io.*;
import java.sql.*;


/**
 * Model.Model superclass, never instansiated.
 * All child model classes inherits its properties, classes and methods
 **/
public abstract class Model {

    private static String sqlUrl = "jdbc:mysql://" + Config.getHost() + ":" + Config.getPort();
    private static String sqlUser = Config.getDbUsername();
    private static String sqlPasswd = Config.getDbPassword();
    private static String dbName = Config.getDbCalendar();
    
    private Statement stmt;
    protected PreparedStatement sqlStatement;
    protected static Connection conn = null;
    protected ResultSet resultSet;

    /**
     * Overwrite default database url
     * @param
     */
    public static void setSelectedDatabase(String db) {
        if (db != null && db.length() > 0) { //Overwrite default
            sqlUrl += db;
        }
    }

    /**
     * Checks if the databse exists or not
     * @return bool
     * @throws SQLException
     */
    public static boolean doesDatabaseExist() throws SQLException {
        getConnection();
        ResultSet resultSet = getConn().getMetaData().getCatalogs();
        while (resultSet.next()) {
            String databaseName = resultSet.getString(1);
            if(databaseName.equals(dbName)){
                return true;
            }
        }
        resultSet.close();
        return false;
    }

    /**
     * Reads and executes SQL from File.
     *
     * @param filepath
     * @throws java.io.IOException
     * @throws java.sql.SQLException
     */
    protected static void readfromSqlFile(String filepath) throws IOException, SQLException {
        getConnection();
        ScriptRunner runner = new ScriptRunner(getConn(), false, false);
        InputStreamReader reader = new InputStreamReader(new FileInputStream(filepath));
        runner.runScript(reader);
        reader.close();
        conn.close();
    }

    public boolean testConnection() {
        try {
            getConnection();

            if (getConn().isValid(5)) //5 seconds
                return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    public String readFromFile(String path) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            br.close();
        }
        return "";
    }

    /**
     * Getter-method for Connection-class
     *
     * @throws java.sql.SQLException
     */
    public static void getConnection() throws SQLException {
    	if(sqlUrl.contains(Config.getDbCalendar())) {
    		setConn(DriverManager.getConnection(sqlUrl, sqlUser, sqlPasswd));
    	}else{
    		setConn(DriverManager.getConnection(sqlUrl+"/"+dbName, sqlUser, sqlPasswd));
    	}
    }

    /**
     * Getter-method for Connection class
     *
     * @return Connection class
     */
    public static Connection getConn() {
        return conn;
    }

    /**
     * Setter-method for Connection class
     *
     * @param conn
     */
    private static void setConn(Connection conn) {
        Model.conn = conn;
    }



}

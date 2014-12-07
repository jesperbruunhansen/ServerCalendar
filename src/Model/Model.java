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
    
    protected static Connection conn = null;
    protected static PreparedStatement sqlStatement;

    /**
     * Checks if the databse exists or not
     * @return bool
     * @throws SQLException
     */
    public static boolean doesDatabaseExist() throws SQLException {
        getConnection(true);
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
        ScriptRunner runner = new ScriptRunner(getConn(), false, false);
        InputStreamReader reader = new InputStreamReader(new FileInputStream(filepath));
        runner.runScript(reader);
        reader.close();
        conn.close();
    }

    /**
     * Use a preparedstatment to run SQL on the database
     *
     * @param sql
     * @return PreparedStatement
     */
    public static PreparedStatement doQuery(String sql) {
        try {
            getConnection(false);
            getConn();
            sqlStatement = getConn().prepareStatement(sql);


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sqlStatement;
    }

    /**
     * Getter-method for Connection-class
     *
     * @throws java.sql.SQLException
     */
    public static void getConnection(boolean b) throws SQLException {
        if(b){
            setConn(DriverManager.getConnection(sqlUrl, sqlUser, sqlPasswd));
        }
        else {
            setConn(DriverManager.getConnection(sqlUrl + "/" + dbName, sqlUser, sqlPasswd));
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

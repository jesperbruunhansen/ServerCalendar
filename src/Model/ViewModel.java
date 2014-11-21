package Model;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import Model.QueryBuild.QueryBuilder;

/**
 * Created by Casper on 16/11/14.
 */
public class ViewModel {

	private ResultSet resultSet;
    private QueryBuilder queryBuilder = new QueryBuilder();

    //LOG IN
    private String userid;
    private String mail;
    private String pw;
    private String roleid;
    private String type;
    private boolean active;


    /**
     * LOGIN
     */
    //ADMIN AUTHENTICATOR - Check if username and password are correct
    public boolean auth(String email, String password) {

        try {
            resultSet = queryBuilder.selectFrom("users").all().ExecuteQuery();
            while (resultSet.next()) {
                mail = resultSet.getString("email");
                pw = resultSet.getString("password");
                active = resultSet.getBoolean("active");

                userid = resultSet.getString("userid");

                if(active && mail.equals(email) && pw.equals(password)){
                    return true;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

     //ADMIN AUTHENTICATOR - Check if user has admin role
    public boolean authAdm(){

        try {
            resultSet = queryBuilder.selectFrom("roles").all().ExecuteQuery();
            while (resultSet.next()) {
                roleid = resultSet.getString("userid");
                type = resultSet.getString("type");

                if(userid.equals(roleid) && type.equals("admin")){
                    return true;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    //CHECK EMAIL FOR EXISTENCE
    public boolean emailCheck(String email) {

        try {
            resultSet = queryBuilder.selectFrom("users").all().ExecuteQuery();
            while (resultSet.next()) {
                mail = resultSet.getString("email");

                if (mail.equals(email)) {
                    return true;
                }

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    /**
     * Creates new user in database
     */
    public void addUser(String email, String password, String role) {

        String active = "1";

        try {
            queryBuilder
                    .insertInto("users", new String[]{"email", "password", "active"})
                    .values(new String[]{email, password, active})
                    .Execute();
        } catch (SQLException e) {

            e.printStackTrace();
        }

        try {
            resultSet = queryBuilder.selectFrom("users").where("email", "=", email).ExecuteQuery();
            while (resultSet.next()) {
                userid = resultSet.getString("userid");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        try {
            queryBuilder
                    .insertInto("roles", new String[]{"userid", "type"})
                    .values(new String[]{userid, role})
                    .Execute();
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    /**
     * Delete from database
     */
    public void delete(String tablename, String key,  String value){

        try {
            queryBuilder.deleteFrom(tablename).where(key, "=", value).Execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Create list of a table from the database
     * SELECT FROM ALL
     */
    //RECEIVE TABLEDATA FROM DATABASE TO USE ON JTABLES
    public Vector<Vector<Object>> tableData(String table){

        //Create new Vector Object which contains Vector-objects
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();

        try{

            resultSet = queryBuilder.selectFrom(table).where("active", "=", "1").ExecuteQuery();
            //resultSet = queryBuilder.selectFrom(table).all().ExecuteQuery();

            //Get Metadata from ResultSet
            ResultSetMetaData metaData = resultSet.getMetaData();

            //Get number of columns from ResultSet
            int columns = metaData.getColumnCount();

            while (resultSet.next()) {

                //Create a new Vector-object to store data from each column
                Vector<Object> row = new Vector<Object>(columns);

                //Run a for-loop for every column
                for (int i = 1; i <= columns; i++) {

                    //Add an object to the row-Vector
                    row.addElement(resultSet.getObject(i));
                }

                //Add the row-Vector to the data-Vector object
                data.addElement(row);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return data;
    }

    //RECEIVE A TABLE WITH SPECIFIC ID
    public Vector<Vector<Object>> tableEvent(String table, String key, String id){

        //Create new Vector Object which contains Vector-objects
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();

        try{
            resultSet = queryBuilder.selectFrom(table).where(key, "=", id).ExecuteQuery();

            //Get Metadata from ResultSet
            ResultSetMetaData metaData = resultSet.getMetaData();

            //Get number of columns from ResultSet
            int columns = metaData.getColumnCount();

            while (resultSet.next()) {

                //Create a new Vector-object to store data from each column
                Vector<Object> row = new Vector<Object>(columns);

                //Run a for-loop for every column
                for (int i = 1; i <= columns; i++) {

                    //Add an object to the row-Vector
                    row.addElement(resultSet.getObject(i));
                }

                //Add the row-Vector to the data-Vector object
                data.addElement(row);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return data;
    }

    //RECEIVE COLUMN NAMES FROM DATABASE TO USE ON JTABLES
    public Vector<String> columnNames(String table){

        //Create Vector object for columns
        Vector<String> columnNames = new Vector<String>();
        try{
            resultSet = queryBuilder.selectFrom(table).all().ExecuteQuery();

            //Get Meta Data from table
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columns = metaData.getColumnCount();
            for (int i = 1; i <= columns; i++) {
                columnNames.addElement(metaData.getColumnName(i));
            }
        }
        catch(Exception e){
            System.err.println("Users - Columns: "+e.getMessage());
        }
        return columnNames;
    }

}



	
	
	

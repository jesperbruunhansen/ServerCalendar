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

    private String mail;
    private String pw;
    private String role;

    /**
     * LOGIN
     */
    //Admin authenticator - Check if username and password are correct
    public boolean auth(String email, String password) {

        try {
            resultSet = queryBuilder.selectFrom("users").all().ExecuteQuery();
            while (resultSet.next()) {
                mail = resultSet.getString("email");
                pw = resultSet.getString("password");
                role = resultSet.getString("role");

                if(mail.equals(email) && pw.equals(password)){
                    return true;
                }

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;

    }

    //Admin authenticator - Check if user has admin role
    public boolean authAdm(){

        if(role.equals("1")){
            return true;
        }

        return false;

    }

    /**
     * Create list of a table from the database
     * SELECT FROM ALL
     */
    //Creating an object to contain user list
    public Vector<Vector<Object>> userData(String table){
		
		//Create new Vector Object which contains Vector-objects
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();

		try{

            resultSet = queryBuilder.selectFrom(table).all().ExecuteQuery();

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

    //Creating the headers for the columns to user list
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

    /**
     * Creates new user in database
     */
	public void addUser(String email, String password, String role) {

        String active = "1";

		try {
			queryBuilder
			    .insertInto("users", new String[]{"email", "password", "active", "role"})
			    .values(new String[]{email, password, active, role})
			    .Execute();
		} catch (SQLException e) {
		
		    e.printStackTrace();
		}
	}

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
     * Delete user in database
     */
    public void deleteUser(String userID){

        try {
            queryBuilder.
                deleteFrom("users").
                where("userid", "=", userID)
                .Execute();

        } catch (SQLException e) {

            e.printStackTrace();
        }

    }

/*    //HELENA RODER
    private ResultSet rs1;

    public Vector<Vector<Object>> eventData(){

        //Create new Vector Object which contains Vector-objects
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();

        try{

            rs1 = queryBuilder1.selectFrom("events").all().ExecuteQuery();

            //Get Metadata from ResultSet
            ResultSetMetaData metaData = rs1.getMetaData();

            //Get number of columns from ResultSet
            int columns = metaData.getColumnCount();

            while (rs1.next()) {

                //Create a new Vector-object to store data from each column
                Vector<Object> row = new Vector<Object>(columns);

                //Run a for-loop for every column
                for (int i = 1; i <= columns; i++) {

                    //Add an object to the row-Vector
                    row.addElement(rs1.getObject(i));
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
    public Vector<String> columnNames1(){

        //Create Vector object for columns
        Vector<String> columnNames = new Vector<String>();
        try{
            rs1 = queryBuilder1.selectFrom("events").all().ExecuteQuery();

            //Get Meta Data from table
            ResultSetMetaData metaData = rs1.getMetaData();
            int columns = metaData.getColumnCount();
            for (int i = 1; i <= columns; i++) {
                columnNames.addElement(metaData.getColumnName(i));
            }
        }
        catch(Exception e){
            System.err.println("Transfers - Columns: "+e.getMessage());
        }

        return columnNames;
    }

    public void addEvents(String id, String activity_id, String event_id , String location , String start) {

        try {
            queryBuilder1
                    .insertInto("events", new String[]{"id", "activity_id" , "event_id" , "location" , "start"})
                    .values(new String[]{id, activity_id , event_id , location , start})
                    .Execute();
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }*/
}



	
	
	

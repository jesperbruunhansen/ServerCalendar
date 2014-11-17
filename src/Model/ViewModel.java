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

	private ResultSet rs;
    private QueryBuilder queryBuilder = new QueryBuilder();

	public Vector<Vector<Object>> userData(){
		
		//Create new Vector Object which contains Vector-objects
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();

		try{

			rs = queryBuilder.selectFrom("users").all().ExecuteQuery();

			//Get Metadata from ResultSet
			ResultSetMetaData metaData = rs.getMetaData();

			//Get number of columns from ResultSet
			int columns = metaData.getColumnCount();

			while (rs.next()) {

				//Create a new Vector-object to store data from each column
				Vector<Object> row = new Vector<Object>(columns);

				//Run a for-loop for every column
				for (int i = 1; i <= columns; i++) {

					//Add an object to the row-Vector
					row.addElement(rs.getObject(i));
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
	public Vector<String> columnNames(){

		//Create Vector object for columns
		Vector<String> columnNames = new Vector<String>();
		try{
			rs = queryBuilder.selectFrom("users").all().ExecuteQuery();

			//Get Meta Data from table
			ResultSetMetaData metaData = rs.getMetaData();
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

	//HELENA RODER
	private ResultSet rs1; 

protected QueryBuilder queryBuilder1 = new QueryBuilder();

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
}
}



	
	
	

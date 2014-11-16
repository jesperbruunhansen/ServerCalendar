package Model;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;

import com.google.gson.Gson;

import Model.QueryBuild.QueryBuilder;

/**
 * Created by Casper on 16/11/14.
 */
public class ViewModel {

	private Gson gson;
	private QueryBuilder queryBuilder;
	private ResultSet rs;
	
	public Vector<Vector<Object>> userData(){
		
		//Create new Vector Object which contains Vector-objects
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();

		try{
			
			queryBuilder = new QueryBuilder();
			gson = new Gson();

			rs = queryBuilder.selectFrom("users").all().ExecuteQuery();

			//  List<Users> userList = new ArrayList<>();

			//Get Metadata from ResultSet
			ResultSetMetaData metaData = rs.getMetaData();

			//Get numbet of columns from ResultSet
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

			//Get Meta Deta from table
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


}

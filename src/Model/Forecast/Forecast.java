package Model.Forecast;

import Model.QueryBuild.QueryBuilder;
import com.sun.rowset.CachedRowSetImpl;

import java.sql.SQLException;
import java.util.ArrayList;

public class Forecast {

	// Main metode til at koere en test af vejrudsigt funktionen
    public void setForecastToDb(){

        try{
            ForecastModel fm = new ForecastModel();
            QueryBuilder queryBuilder = new QueryBuilder();
            ArrayList<ForecastClass> forecastList = fm.requestForecast();

            for(int i = 0; i < forecastList.size(); i++) {
                String date = forecastList.get(i).getDate();
                String desc = forecastList.get(i).getDesc();
                String celsius = forecastList.get(i).getCelsius();

                //System.out.println(date);
                queryBuilder.insertInto("forecast",new String[]{"date", "description", "celsius"}).values(new String[]{date, desc, celsius}).Execute();

            }

            System.out.println("Forecast done");
        }
        catch (Exception e){
          //  e.printStackTrace();
        }



    }


}

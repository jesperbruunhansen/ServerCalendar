package Model.Forecast;

import Model.Model;
import Model.QueryBuild.QueryBuilder;
import com.sun.rowset.CachedRowSetImpl;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class Forecast extends Model{

	// Main metode til at koere en test af vejrudsigt funktionen
    public static void setForecastToDb(){

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

            System.out.println("Forecast has been inserted to db");
        }
        catch (Exception e){
          //  e.printStackTrace();
        }
    }

    public static boolean isForecastUpToDate(){

        QueryBuilder queryBuilder = new QueryBuilder();
        LocalDate firstDay = null;
        Date now = new Date();
        LocalDate dateNow = new LocalDate(now);

        try{
            CachedRowSetImpl rs = queryBuilder.selectFrom(new String[]{"date"}, "forecast").where("id", "=", "1").ExecuteQuery();
            while (rs.next()){
                firstDay = new DateTime(rs.getDate("date")).toLocalDate();
            }

            if(firstDay != null && dateNow.equals(firstDay)){
                //System.out.println("Forecast er up to date");
                return true;
            }


        }catch (SQLException e){
            e.printStackTrace();
        }

        //System.out.println("Forecast er IKKE uptodate");
        return false;

    }

    public static void refreshForecast(){
        try{
            PreparedStatement ps = doQuery("TRUNCATE TABLE forecast;");
            ps.execute();

            Forecast.setForecastToDb();

        } catch (SQLException e){
            e.printStackTrace();
        }

    }



}

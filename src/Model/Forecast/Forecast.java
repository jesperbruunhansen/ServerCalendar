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
            long createdon = System.currentTimeMillis() / 1000L;

            for(int i = 0; i < forecastList.size(); i++) {
                String date = forecastList.get(i).getDate();
                String desc = forecastList.get(i).getDesc();
                String celsius = forecastList.get(i).getCelsius();

                //System.out.println(date);
                queryBuilder
                        .insertInto("forecast",new String[]{"date", "description", "celsius", "createdon"})
                        .values(new String[]{date, desc, celsius, String.valueOf(createdon)})
                        .Execute();
            }

            System.out.println("Forecast has been inserted to db");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static boolean isForecastUpToDate(){

        QueryBuilder queryBuilder = new QueryBuilder();
        Long createdon = 0L;
        Long timeNow = System.currentTimeMillis() / 1000L;

        try{
            CachedRowSetImpl rs = queryBuilder.selectFrom(new String[]{"createdon"}, "forecast").where("id", "=", "1").ExecuteQuery();
            while (rs.next()){
                createdon = rs.getLong("createdon");
            }
            if((timeNow - createdon) > 3600){
                return false;
            }
            else {
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

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

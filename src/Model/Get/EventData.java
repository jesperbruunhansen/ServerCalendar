package Model.Get;

import Model.Forecast.ForecastClass;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by jesperbruun on 29/11/14.
 */
public class EventData {




}
class Event {
    private String activityid;
    private String eventid;
    private String type;
    private String title;
    private String description;
    private String location;
    private int createdby;
    private ArrayList<String> start;
    private ArrayList<String> end;
    private ForecastClass forecast;
    private int calendarid;


    private transient Date dateStart;
    private transient Date dateEnd;

    private String strDateStart;
    private String strDateEnd;

    private String text;
    private ArrayList<Note> noter;
    private boolean customevent;


    public void setForecastClass(ForecastClass forecastClass) {
        this.forecast = forecastClass;
    }

    public void setStrDateStart(String strDateStart) {
        this.strDateStart = strDateStart;
    }

    public void setStrDateEnd(String strDateEnd) {
        this.strDateEnd = strDateEnd;
    }

    public void setNoter(ArrayList<Note> noter) {
        this.noter = noter;
    }
    public void setCalendarid(int calendarid) {
        this.calendarid = calendarid;
    }

    public void setCustomevent(boolean customevent) {
        this.customevent = customevent;
    }

    // Settere og gettere for Event objektet
    public void setActivityid(String activityid){
        this.activityid = activityid;
    }
    public String getActivityid(){
        return activityid;
    }

    public void setEventid(String eventid){
        this.eventid = eventid;
    }
    public String getEventid(){
        return eventid;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getDescription(){
        return description;
    }

    public void setLocation(String location){
        this.location = location;
    }
    public String getLocation(){
        return location;
    }

    public void setCreatedby(int createdby){
        this.createdby = createdby;
    }

    public ArrayList<String> getStart(){return start;
    }

    public void setDateStart(Date start){this.dateStart = start;}
    public Date getDateStart(){return dateStart;}

    public void setDateEnd(Date end){this.dateEnd = end;}


    public void setText(String text){this.text = text;}

    public ArrayList<String> getEnd(){
        return end;
    }

    public Event(){}

}

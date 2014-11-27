package Model.Calendar;

import Model.Forecast.ForecastClass;
import com.google.gson.annotations.Expose;
import org.joda.time.DateTime;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by jesperbruun on 10/10/14.
 * Til hver specifik event bliver de defineret saaledes.
 */
public class Event {
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


    private transient Date dateStart;
    private transient Date dateEnd;

    private String strDateStart;
    private String strDateEnd;

    private String text;
    private ArrayList<Note> noter;
    private boolean customevent;


    public ForecastClass getForecastClass() {
        return forecast;
    }

    public void setForecastClass(ForecastClass forecastClass) {
        this.forecast = forecastClass;
    }

    public String getStrDateStart() {
        return strDateStart;
    }

    public void setStrDateStart(String strDateStart) {
        this.strDateStart = strDateStart;
    }

    public String getStrDateEnd() {
        return strDateEnd;
    }

    public void setStrDateEnd(String strDateEnd) {
        this.strDateEnd = strDateEnd;
    }


    public int getCalendarid() {
        return calendarid;
    }
    public ArrayList<Note> getNoter() {
        return noter;
    }

    public void setNoter(ArrayList<Note> noter) {
        this.noter = noter;
    }
    public void setCalendarid(int calendarid) {
        this.calendarid = calendarid;
    }

    public boolean getCustomevent() {
        return customevent;
    }

    public void setCustomevent(boolean customevent) {
        this.customevent = customevent;
    }

    private int calendarid;

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

    public void setType(String type){
        this.type = type;
    }
    public String getType(){
        return type;
    }

    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return title;
    }

    public void setDescription(String description){
        this.description = description;
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
    public int getCreatedby(){
        return createdby;
    }
    
    public void setStart(ArrayList<String> start){
        this.start = start;
    }
    public ArrayList<String> getStart(){return start;
    }

    public void setDateStart(Date start){this.dateStart = start;}
    public Date getDateStart(){return dateStart;}

    public void setDateEnd(Date end){this.dateEnd = end;}
    public Date getDateEnd(){return dateEnd;}

    public void setText(String text){this.text = text;}
    public String getText(){return text;}

    public void setEnd(ArrayList<String> end){
        this.end = end;
    }

    public ArrayList<String> getEnd(){
        return end;
    }
	public Event(String activityid, String eventid, String type, String title,
			String description, String location, int createdby, ArrayList<String> start,
			ArrayList<String> end) {
		super();
		this.activityid = activityid;
		this.eventid = eventid;
		this.type = type;
		this.title = title;
		this.description = description;
		this.location = location;
		this.createdby = createdby;
		this.start = start;
		this.end = end;
	}
    public Event(){}

}

package Model.Calendar;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by jesperbruun on 10/10/14.
 */
public class Events {
    ArrayList<Event> events = new ArrayList<Event>();

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Event> event) {
        this.events = event;
    }

    @Override
    public String toString() {
        return Arrays.toString(events.toArray());
   
    }
//HELENA RODER HER 
    
    private int id;
    private String activity_id;
    private String event_id;
    private String location;
    private String start;
    private boolean active;

    public Events(int Id, String activity_id, String location , String start , boolean active){
        this.id = Id;
        this.activity_id = activity_id;
        this.location = location;
        this.start = start;
        this.active = active;
    }
    public Events(){}


    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getActivity_id() {
		return activity_id;
	}

	public void setActivity_id(String activity_id) {
		this.activity_id = activity_id;
	}

	public String getEvent_id() {
		return event_id;
	}

	public void setEvent_id(String event_id) {
		this.event_id = event_id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}


    public boolean getActiveStatus(){return active;}

}